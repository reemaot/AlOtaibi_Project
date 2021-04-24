package com.example.alotaibi_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class InsertUser extends AppCompatActivity {

    String ID;
    String fName;
    String lName;
    String email;
    String phone;
    Firebase firebase = new Firebase();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_user);

        EditText userId2 = (EditText)findViewById(R.id.idEdit3);
        EditText firstNameEdit = (EditText)findViewById(R.id.firstNameEdit);
        EditText lastNameEdit = (EditText)findViewById(R.id.lastNameEdit);
        EditText emailEdit = (EditText)findViewById(R.id.emailEdit);
        EditText phoneEdit = (EditText)findViewById(R.id.phoneEdit);
        Button addBttn = (Button)findViewById(R.id.addBttn);
        Button homeBttn3 = (Button) findViewById(R.id.homeBttn3);

        addBttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(userId2.getText().toString().isEmpty() ||
                        firstNameEdit.getText().toString().isEmpty() || lastNameEdit.getText().toString().isEmpty()
                        || emailEdit.getText().toString().isEmpty() || phoneEdit.getText().toString().isEmpty()){

                    Toast.makeText(InsertUser.this, "Empty fields are needed to register.", Toast.LENGTH_LONG).show();

                }
                else{


                    ID = userId2.getText().toString();
                    fName = firstNameEdit.getText().toString();
                    lName = lastNameEdit.getText().toString();
                    email = emailEdit.getText().toString();
                    phone= phoneEdit.getText().toString();

                    firebase.writeWithSuccess(ID,fName,lName,email,phone);
                    firebase.onDataChange();

                    Toast.makeText(getBaseContext(), "User added successfully.", Toast.LENGTH_LONG).show();

                }
            }
        });


        homeBttn3.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(InsertUser.this, MainActivity.class));
            }
        });
    }
}