package com.example.alotaibi_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class UpdateUser extends AppCompatActivity {

    String choice;
    Firebase firebase = new Firebase();
    String newValue;
    String ID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_user);

        EditText userId = (EditText)findViewById(R.id.IdEdit);
        Spinner options = (Spinner) findViewById(R.id.spinner);
        EditText updatedValue = (EditText)findViewById(R.id.updatedValue);
        Button updateBttn = (Button) findViewById(R.id.updateBttn);
        Button homeBttn = (Button) findViewById(R.id.homeBttn7);

        TextView temperature = (TextView) findViewById(R.id.TempTxtView3);
        TextView humidity = (TextView) findViewById(R.id.humTxtView3);
        TextView city = (TextView) findViewById(R.id.CityTxtView3);
        TextView weather = (TextView) findViewById(R.id.weatherTxtView3);

        //display weather in activity
        SharedPreferences sp1= PreferenceManager.getDefaultSharedPreferences(this);
        float Temp3 = sp1.getFloat("temp",0);
        float Humid3 = sp1.getFloat("humidity",0);
        String City3 = sp1.getString("city", "0");
        String Weather3 = sp1.getString("weather", "0");

        temperature.setText(String.valueOf(Temp3)+"°C");
        humidity.setText(String.valueOf(Humid3));
        weather.setText(Weather3);
        city.setText(City3);

        updateBttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(userId.getText().toString().isEmpty() || updatedValue.getText().toString().isEmpty()){
                    Toast.makeText(UpdateUser.this, "Please Enter User ID / Updated Value.", Toast.LENGTH_LONG).show();
                } else {

                    choice = options.getSelectedItem().toString();
                    newValue= updatedValue.getText().toString();
                    ID = userId.getText().toString();

                    switch(choice){
                        case "First Name": firebase.updateFirstName(ID,newValue);  firebase.onDataChange();break;
                        case "Last Name": firebase.updateLastName(ID,newValue); firebase.onDataChange();break;
                        case "Email Address": firebase.updateEmail(ID,newValue); firebase.onDataChange();break;
                        case "Phone Number": firebase.updatePhoneNumber(ID,newValue); firebase.onDataChange(); break;
                    }
                }

                Toast.makeText(getBaseContext(), "User information updated successfully.", Toast.LENGTH_LONG).show();


            }
        });


        homeBttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UpdateUser.this, MainActivity.class));
            }
        });
    }
}