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


        Cursor cur = mydb.getAllBookings();
        int len = cur.getCount();
        String s[] = new String[cur.getCount()];
        Cursor usr = mydb.getUser(u1.getUid());
        String usrbranch = usr.getString(2);

        int oplen=0;

        while (cur.moveToNext() ) {
            String targs = cur.getString(4);
            String[] args = targs.split(";");
            int arglen = args.length;
            for (int i = 0; i < arglen; i++) {
                if (args[i].charAt(0) == usrbranch.charAt(0) && args[i].charAt(1) == usrbranch.charAt(1)) {
                    oplen++;
                }
            }
        }
        int index = 1;
        String op[] = new String[oplen+1];
        op[0] = "name | sdate | edate";





        while (cur.moveToNext()) {
            String targs = cur.getString(4);
            String[] args = targs.split(" ");
            int arglen = args.length;
            for(int i=0;i<arglen;i++)
            {
                if(args[i].charAt(0)== usrbranch.charAt(0) && args[i].charAt(1) == usrbranch.charAt(1) )
                {
                    op[index] = mydb.getUser(cur.getInt(0)).getString(0) + " | "+mydb.getResourceName(cur.getInt(1)) + " | "+ cur.getString(2)+" | "+cur.getString(3);
                    index++;
                }
            }




        }

        ArrayAdapter<String> ar =new ArrayAdapter<>(timetable.this, android.R.layout.simple_list_item_1,op);
        l.setAdapter(ar);
    }
}