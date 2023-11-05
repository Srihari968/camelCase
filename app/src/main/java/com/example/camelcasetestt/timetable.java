package com.example.camelcasetestt;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class timetable extends AppCompatActivity {
    DataClass mydb;
    user u1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timetable);
        mydb = new DataClass(this);
        u1 = new user(getApplicationContext());

        ListView l = (ListView) findViewById(R.id.list);

        Cursor ucur = mydb.getUser(u1.getUid());
        String sstring = ucur.getString(2)+ucur.getInt(3);
        Cursor res = mydb.targetSearch(sstring);
        res.moveToFirst();
        String as[] = new String[res.getCount()];
        if(as.length>=1) {
            as[0] = "Name | Resource | sdate | edate";
        }
        int index=1;

        while(res.moveToNext() && index<=res.getCount())
        {
            as[index] = mydb.userName(res.getInt(0))+ " | "+mydb.getResourceName(res.getInt(1))+ " | "+res.getString(2)+ " | "+ res.getString(3);
            index++;

        }

        ArrayAdapter<String> ar= new ArrayAdapter<>(timetable.this, android.R.layout.simple_list_item_1,as);
        l.setAdapter(ar);



    }
}