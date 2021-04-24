package com.example.alotaibi_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class DeleteUser extends AppCompatActivity {

    Firebase firebase = new Firebase();
    String ID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_user);

        EditText userId2 = (EditText)findViewById(R.id.idEdit2);
        Button homeBttn2 = (Button) findViewById(R.id.homeBttn2);
        Button deleteBttn = (Button) findViewById(R.id.deleteBttn);

        TextView temperature = (TextView) findViewById(R.id.TempTxtView4);
        TextView humidity = (TextView) findViewById(R.id.humTxtView4);
        TextView city = (TextView) findViewById(R.id.CityTxtView4);
        TextView weather = (TextView) findViewById(R.id.weatherTxtView4);

         //display weather in activity
        SharedPreferences sp1= PreferenceManager.getDefaultSharedPreferences(this);
        float Temp4 = sp1.getFloat("temp",0);
        float Humid4 = sp1.getFloat("humidity",0);
        String City4 = sp1.getString("city", "0");
        String Weather4 = sp1.getString("weather", "0");

        temperature.setText(String.valueOf(Temp4)+"°C");
        humidity.setText(String.valueOf(Humid4));
        weather.setText(Weather4);
        city.setText(City4);

        deleteBttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(userId2.getText().toString().isEmpty()){
                    Toast.makeText(DeleteUser.this, "Please Enter User ID", Toast.LENGTH_LONG).show();
                } else{

                    ID = userId2.getText().toString();
                    firebase.deleteUser(ID);
                    firebase.onDataChange();

                    Toast.makeText(getBaseContext(), "User deleted successfully.", Toast.LENGTH_LONG).show();


                }
            }
        });

        homeBttn2.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DeleteUser.this, MainActivity.class));
            }
        });
    }
}