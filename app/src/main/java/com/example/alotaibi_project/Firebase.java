package com.example.alotaibi_project;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.IgnoreExtraProperties;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

import java.util.Map;

public class Firebase  {

     FirebaseDatabase mDatabase;
     DatabaseReference myRef;

    public Firebase(){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference();
    }


    public void onDataChange(){
        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = null;
                for(DataSnapshot postSnapshot : dataSnapshot.getChildren()){
                    //value = postSnapshot.getValue(String.class);
                    Map<String, Object> map =
                            (Map<String, Object>) dataSnapshot.getValue();
                    Log.d("Reema", "Value is: " + map);
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.d("Reema", "Failed to read value.", error.toException());
            }
        });
    }



    public void writeNewUser(String userId, String firstName, String lastName,String emailAddress, String phoneNumber) {
        User user = new User(userId, firstName, lastName, emailAddress, phoneNumber);
        myRef.child("users").child(userId).setValue(user);
    }

    public void writeWithSuccess(String userId, String firstName, String lastName,String emailAddress, String phoneNumber) {
        User user = new User(userId, firstName, lastName, emailAddress, phoneNumber);
        myRef.child("users").child(userId).setValue(user).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Log.d("Reema", "Added User Successfully");
            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e("Reema", "Error: "+e);
                    }
                });
    }

    //method to update the first name of specific user using userId
    public void updateFirstName(String userId, String firstName){
        myRef.child("users").child(userId).child("firstName").setValue(firstName);
        Log.d("Reema", "First Name Updated successfully");

    }

    //method to update the last name of specific user using userId
    public void updateLastName(String userId, String lastName){
        myRef.child("users").child(userId).child("lastName").setValue(lastName);
        Log.d("Reema", "Last Name Updated successfully");

    }

    //method to update the phone number of specific user using userId
    public void updatePhoneNumber(String userId, String phoneNumber){
        myRef.child("users").child(userId).child("phoneNumber").setValue(phoneNumber);
        Log.d("Reema", "Phone Number Updated successfully");

    }
    //method to update the email address of specific user using userId
    public void updateEmail(String userId, String emailAddress){
        myRef.child("users").child(userId).child("emailAddress").setValue(emailAddress);
        Log.d("Reema", "Email Updated successfully");

    }

    //method to delete the user
    public void deleteUser(String userId){
        myRef.child("users").child(userId).removeValue();
        Log.d("Reema", "User Deleted successfully");
    }





}
