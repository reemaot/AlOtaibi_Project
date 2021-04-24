package com.example.alotaibi_project;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

//sql class to add, update, delete
public class SQLActivity extends AppCompatActivity {


    String ID;
    String fName;
    String lName;
    String email;
    String phone;

    DatabaseHelper MyDB;

    FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
    DatabaseReference usersdB = mDatabase .getReference("users");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_s_q_l);

        MyDB =  new DatabaseHelper(this);

        Button addBttn2 = (Button) findViewById(R.id.addBttn2);
        Button updateBttn2 = (Button) findViewById(R.id.updateBttn2);
        Button viewBttn2 = (Button) findViewById(R.id.viewBttn2);
        Button deleteBttn2 = (Button) findViewById(R.id.deleteBttn2);
        Button homeBttn4 = (Button) findViewById(R.id.homeBttn4);

        EditText userId = (EditText)findViewById(R.id.idEdit4);
        EditText firstNameEdit2 = (EditText)findViewById(R.id.firstNameEdit2);
        EditText lastNameEdit2 = (EditText)findViewById(R.id.lastNameEdit2);
        EditText emailEdit2 = (EditText)findViewById(R.id.emailEdit2);
        EditText phoneEdit2 = (EditText)findViewById(R.id.phoneEdit2);

        addBttn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(userId.getText().toString().isEmpty()){

                    Toast.makeText(SQLActivity.this, "Please Enter User ID.", Toast.LENGTH_LONG).show();

                } else{

                    String ID1 = userId.getText().toString();
                    //get specific data
                    Query query = usersdB.orderByChild("userId").equalTo(ID1);
                    query.addChildEventListener(new ChildEventListener() {
                        @Override
                        public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                            try {
                                String s = snapshot.toString();
                                Log.d("Reema", "SQL query dataChange" + s);

                                String id= snapshot.child("userId").getValue().toString();
                                Log.d("Reema", "userId: " + id);


                                String fname = snapshot.child("firstName").getValue().toString();
                                Log.d("Reema", "firstName: " + fname);


                                String lname = snapshot.child("lastName").getValue().toString();
                                Log.d("Reema", "lastName: " + lname);

                                String Email = snapshot.child("emailAddress").getValue().toString();
                                Log.d("Reema", "emailAddress: " + Email);


                                String Phone = snapshot.child("phoneNumber").getValue().toString();
                                Log.d("Reema", "phoneNum: " + Phone);

                                MyDB.addData(id, fname, lname, Email,Phone );


                            } catch(Exception e){
                                Log.e("Reema", "Error in adding SQL Activity "+ e);
                            }

                        }

                        @Override
                        public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                        }

                        @Override
                        public void onChildRemoved(@NonNull DataSnapshot snapshot) {

                        }

                        @Override
                        public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            Log.e("Reema", "SQL ChildEventListener Error "+ error);

                        }
                    });



                    Toast.makeText(SQLActivity.this, " Successful Add.", Toast.LENGTH_LONG).show();
                    Log.d("Reema","SQL Added User successfully");

                }
            }
        });



        updateBttn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(userId.getText().toString().isEmpty() ||
                        firstNameEdit2.getText().toString().isEmpty() || lastNameEdit2.getText().toString().isEmpty()
                        || emailEdit2.getText().toString().isEmpty() || phoneEdit2.getText().toString().isEmpty()){

                    Toast.makeText(SQLActivity.this, "Empty fields are needed to update.", Toast.LENGTH_LONG).show();

                }
                else{


                    ID = userId.getText().toString();
                    fName = firstNameEdit2.getText().toString();
                    lName = lastNameEdit2.getText().toString();
                    email = emailEdit2.getText().toString();
                    phone= phoneEdit2.getText().toString();
                    MyDB.updateUser(ID, fName, lName, email, phone);

                    Log.d("Reema", "userId: " + ID);
                    Log.d("Reema", "firstName: " + fName);
                    Log.d("Reema", "lastName: " + lName);
                    Log.d("Reema", "emailAddress: " + email);
                    Log.d("Reema", "phoneNum: " + phone);
                    Log.d("Reema","SQL Update successful");

                    Toast.makeText(SQLActivity.this, " Successful Update.", Toast.LENGTH_LONG).show();

                    }
                }
        });


        viewBttn2.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SQLActivity.this, SQLView.class));
            }
        });


        deleteBttn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(userId.getText().toString().isEmpty()){
                    Toast.makeText(SQLActivity.this, "Please Enter User ID.", Toast.LENGTH_LONG).show();
                } else{

                    ID = userId.getText().toString();
                    MyDB.DeleteUser(ID);
                    Log.d("Reema","SQL Delete successful");

                    Toast.makeText(SQLActivity.this, "Successful Delete", Toast.LENGTH_LONG).show();

                }
            }
        });

        homeBttn4.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SQLActivity.this, MainActivity.class));
            }
        });
    }
}