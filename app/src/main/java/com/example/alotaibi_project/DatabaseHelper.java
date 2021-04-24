package com.example.alotaibi_project;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;


    public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "users.db";
    public static final String TABLE_NAME = "users_data";
    private static final String COLUMN_ID = "ID";
    private static final String COLUMN_FNAME = "FirstName";
    private static final String COLUMN_LNAME = "LastName";
    private static final String COLUMN_EMAIL = "Email";
    private static final String COLUMN_PHONE = "Phone";

    /* Constructor */
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    /* Code runs automatically when the dB is created */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(" CREATE TABLE " + TABLE_NAME + "(" +COLUMN_ID
                + " INTEGER PRIMARY KEY ," + COLUMN_FNAME + " TEXT NOT NULL,"+ COLUMN_LNAME + " TEXT NOT NULL,"
                + COLUMN_EMAIL +" TEXT NOT NULL, " + COLUMN_PHONE + " TEXT NOT NULL )");
    }

    /* Every time the dB is updated (or upgraded) */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

 //method to add data
    public boolean addData(String userId, String firstName, String lastName,String emailAddress, String phoneNumber) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_ID, userId);
        contentValues.put(COLUMN_FNAME, firstName);
        contentValues.put(COLUMN_LNAME, lastName);
        contentValues.put(COLUMN_EMAIL, emailAddress);
        contentValues.put(COLUMN_PHONE, phoneNumber);

        long result = db.insert(TABLE_NAME, null, contentValues);


        //if data are inserted incorrectly, it will return -1
        if(result == -1) {return false;} else {return true;}
    }



    // Return everything inside a specific table
    public Cursor viewUsers() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        return data;
    }

//delete method
    public Integer DeleteUser(String id){

        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, COLUMN_ID + " = ? ", new String[] {id});

    }



//update method
    public boolean updateUser(String userId, String firstName, String lastName,String emailAddress, String phoneNumber){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_FNAME,firstName);
        contentValues.put(COLUMN_LNAME,lastName);
        contentValues.put(COLUMN_EMAIL,emailAddress);
        contentValues.put(COLUMN_PHONE,phoneNumber);
        db.update(TABLE_NAME, contentValues, COLUMN_ID + " = ? " ,
                new String[]{String.valueOf(userId)});

        return true;
    }



}

