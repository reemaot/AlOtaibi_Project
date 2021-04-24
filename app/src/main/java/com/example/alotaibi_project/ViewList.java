package com.example.alotaibi_project;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.ArrayList;

//class to show firebase records in a list
public class ViewList extends ListActivity {

    Firebase firebase;
    ArrayList<String> arraylist = new ArrayList<>();
    FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
    DatabaseReference usersdB;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try{
        setContentView(R.layout.activity_view);
        } catch(Exception e){
            Log.e("Reema", "Error in ViewFB "+ e);
        }

        firebase= new Firebase();
        usersdB = mDatabase .getReference("users");
        firebase.onDataChange();



            usersdB.addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String s) {


                        String id = snapshot.child("userId").getValue().toString();
                        Log.d("Reema", "userId: " + id);


                        String fname = snapshot.child("firstName").getValue().toString();
                        Log.d("Reema", "firstName: " + fname);


                        String lname = snapshot.child("lastName").getValue().toString();
                        Log.d("Reema", "lastName: " + lname);

                        String email = snapshot.child("emailAddress").getValue().toString();
                        Log.d("Reema", "emailAddress: " + email);


                        String phoneNum = snapshot.child("phoneNumber").getValue().toString();
                        Log.d("Reema", "phoneNum: " + phoneNum);

                        arraylist.add("\n-User ID: " +id +"\n-First Name: "+fname+"\n-Last Name: "+lname+"\n-Email Address: "
                                +email+"\n-Phone Number: "+phoneNum+"\n");

                        setListAdapter(new ArrayAdapter<String>(ViewList.this, R.layout.activity_view_list, R.id.userList, arraylist));

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
                    Log.d("Reema", "Error List in ViewList "+ error.toString());
                }
            });




    }




}

