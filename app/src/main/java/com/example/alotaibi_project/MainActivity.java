package com.example.alotaibi_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

   // Firebase firebase = new Firebase();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button viewUserBttn = (Button) findViewById(R.id.viewUserBttn);
        Button updateUserBttn = (Button) findViewById(R.id.updateUserBttn);
        Button deleteUserBttn = (Button) findViewById(R.id.deleteUserBttn);
        Button insertUserBttn = (Button) findViewById(R.id.insertUserBttn);
        Button sqlHomeBttn = (Button) findViewById(R.id.sqlHomeBttn);
        Button weatherBttn = (Button) findViewById(R.id.weatherBttn);

        TextView temperature = (TextView) findViewById(R.id.TempTxtView);
        TextView humidity = (TextView) findViewById(R.id.humTxtView);
        TextView city = (TextView) findViewById(R.id.CityTxtView);
        TextView weather = (TextView) findViewById(R.id.weatherTxtView);

        SharedPreferences sp1= PreferenceManager.getDefaultSharedPreferences(this);
        float Temp1 = sp1.getFloat("temp",0);
        float Humid1 = sp1.getFloat("humidity",0);
        String City1 = sp1.getString("city", "0");
        String Weather1 = sp1.getString("weather", "0");

        temperature.setText(String.valueOf(Temp1)+"°C");
        humidity.setText(String.valueOf(Humid1));
        weather.setText(Weather1);
        city.setText(City1);

        viewUserBttn.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(MainActivity.this, ViewFB.class));

            }
        });

        updateUserBttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, UpdateUser.class));
            }
        });

        deleteUserBttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, DeleteUser.class));
            }
        });

        insertUserBttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, InsertUser.class));
            }
        });


        sqlHomeBttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SQLActivity.class));
            }
        });


        weatherBttn.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(MainActivity.this, Weather.class));

            }
        });


    }


}