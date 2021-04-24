package com.example.alotaibi_project;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

//class to display sql data in list
public class SQLView extends ListActivity {

    DatabaseHelper MyDB;
    Cursor cur;
    ArrayList<String> arraylist = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_s_q_l_view);

        try {

            MyDB = new DatabaseHelper(this);

             cur = MyDB.viewUsers();

                while (cur.moveToNext()) {
                           arraylist.add("-ID: " + cur.getString(0)
                                   + "\n-First Name: " + cur.getString(1)
                                   + "\n-Last Name: " + cur.getString(2)
                                   + "\n-Email: " + cur.getString(3)
                                   + "\n-Phone: " + cur.getString(4));

                }



            setListAdapter(new ArrayAdapter<String>(SQLView.this, R.layout.activity_s_q_l_view, R.id.SQLuserList, arraylist));

        }catch(Exception e){
            Log.e("Reema", "SQL List error "+ e);
        }


    }

    protected void onListItemClick(ListView l, View v, int position, long id)
    {
        super.onListItemClick(l, v, position, id);

        try {
            int rows = 0;
            cur = MyDB.viewUsers();

            while(rows<=position){
                cur.moveToNext();
                rows++;
            }

            Toast.makeText(this,  "Full Name: " + cur.getString(1) +" "+ cur.getString(2) , Toast.LENGTH_SHORT).show();

                

            } catch (Exception e){
            Log.e("Reema", "SQL List error 2 "+ e);
             }

        }
   }