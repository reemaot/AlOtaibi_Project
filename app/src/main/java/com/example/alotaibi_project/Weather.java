package com.example.alotaibi_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Weather extends AppCompatActivity {

    // we"ll make HTTP request to this URL to retrieve weather conditions
    String weatherWebserviceURL = "http://api.openweathermap.org/data/2.5/weather?q=athens&appid=9cd972a74fe78aee66340251b845bb83&units=metric";
    String cityname;
    String weatherConditions;

    // JSON object that contains weather information
     JSONObject jsonObj;
     SharedPreferences sp;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        Button goBttn = (Button)findViewById(R.id.goBttn);
        Button homeBttn7 = (Button)findViewById(R.id.homeBttn7);
        EditText cityEdit = (EditText) findViewById(R.id.cityEdit);

         sp = PreferenceManager.getDefaultSharedPreferences(this);

        //weather(weatherWebserviceURL);

        goBttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                cityname= cityEdit.getText().toString();


                editor= sp.edit();
                editor.putString("city",cityname);
                editor.commit();

                weather(url(cityname));

                Toast.makeText(Weather.this, "City Changed Successfully.", Toast.LENGTH_SHORT).show();

            }
        });


        homeBttn7.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Weather.this, MainActivity.class));
            }
        });

    }


    public String url(String city){

        String updatedURL="http://api.openweathermap.org/data/2.5/weather?q="+city+"&appid=9cd972a74fe78aee66340251b845bb83&units=metric";

        return updatedURL;

    }


    public void weather(String url){
        JsonObjectRequest jsonObj = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
               //get the results
                Log.d("Reema", "Response: " + response.toString());
                try {

                    JSONObject jsonMain = response.getJSONObject("main");
                    Log.d("Reema","subObject: "+ jsonMain.toString());

                    double temp = jsonMain.getDouble("temp");
                    double humid = jsonMain.getDouble("humidity");

                    Log.d("Reema","Temp: "+String.valueOf(temp));
                    Log.d("Reema","Humidity: "+String.valueOf(humid));

                    JSONArray weatherArray = response.getJSONArray("weather");
                    String wthr=  weatherInfo(weatherArray);

                    editor= sp.edit();
                    editor.putFloat("temp",(float)temp);
                    editor.putFloat("humidity",(float)humid);
                    editor.putString("weather",wthr);
                    editor.commit();



                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Reema", "Error in URL: " + error);


            }
        });

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(jsonObj);
    }


    public String weatherInfo(JSONArray jArray){
        for (int i=0; i < jArray.length(); i++)
        {
            try {
                JSONObject oneObject = jArray.getJSONObject(i);
                Log.d("Reema", "jArray(i) "+ oneObject.toString());

                  // Pulling items from the array
                 weatherConditions = oneObject.getString("main");
                Log.d("Reema", "Weather Conditions: "+weatherConditions);


            } catch (JSONException e) {
                Log.d("Reema", "Error JSONArray: "+e.toString());
            }
        }
          return weatherConditions;
    }


    }