package com.example.alotaibi_project;

import androidx.annotation.NonNull;

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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

//class to view specific record from firebase
public class ViewFB extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        TextView temperature = (TextView) findViewById(R.id.TempTxtView5);
        TextView humidity = (TextView) findViewById(R.id.humTxtView5);
        TextView city = (TextView) findViewById(R.id.CityTxtView5);
        TextView weather = (TextView) findViewById(R.id.weatherTxtView5);

        //display weather in activity
        SharedPreferences sp1= PreferenceManager.getDefaultSharedPreferences(this);
        float Temp5 = sp1.getFloat("temp",0);
        float Humid5 = sp1.getFloat("humidity",0);
        String City5 = sp1.getString("city", "0");
        String Weather5 = sp1.getString("weather", "0");

        temperature.setText(String.valueOf(Temp5)+"°C");
        humidity.setText(String.valueOf(Humid5));
        weather.setText(Weather5);
        city.setText(City5);

        EditText userid = (EditText)findViewById(R.id.ViewIdEdit);
        Button homeBttn5 = (Button) findViewById(R.id.homeBttn5);
        Button viewUserBttn2 = (Button) findViewById(R.id.viewUserBttn2);
        Button viewAllUsersBttn = (Button) findViewById(R.id.viewAllUsersBttn);
        TextView result = (TextView) findViewById(R.id.userResult);

        FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
        DatabaseReference usersdB = mDatabase .getReference("users");
        viewUserBttn2.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {

                if(userid.getText().toString().isEmpty()){
                    Toast.makeText(ViewFB.this, "Please Enter User ID", Toast.LENGTH_LONG).show();
                } else{

                    String ID = userid.getText().toString();
                    //get specific data
                    Query query = usersdB.orderByChild("userId").equalTo(ID);

                    query.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                            try {

                                if (snapshot.exists()) {
                                    for (DataSnapshot snap : snapshot.getChildren()) {
                                        String s = snapshot.toString();
                                        String key = snap.getKey();
                                        String fn = snap.child("firstName").getValue().toString();
                                        String ln = snap.child("lastName").getValue().toString();
                                        String email = snap.child("emailAddress").getValue().toString();
                                        String phone = snap.child("phoneNumber").getValue().toString();
                                        result.setText("Result:\nID: " + key + "\nName: " + fn + " " + ln + "\nEmail: " + email + "\nPhone: " + phone);

                                        Log.d("Reema", "User info: " + s);
                                    }
                                }

                            } catch (Exception e){
                                Log.e("Reema", "Error in ViewFB value listener "+e);
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            Log.d("Reema", "query error" + error );
                        }
                    });


                    Toast.makeText(getBaseContext(), "User viewed successfully.", Toast.LENGTH_LONG).show();


                }
            }
        });

        viewAllUsersBttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    startActivity(new Intent(ViewFB.this, ViewList.class));
                }catch (Exception e){
                    Log.e("Reema", "Error"+ e);
                }
            }

        });



        homeBttn5.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                startActivity(new Intent(ViewFB.this, MainActivity.class));
            }
        });

    }

}
