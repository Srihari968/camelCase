package com.example.camelcasetestt;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class viewBookings extends AppCompatActivity {
    DataClass mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_bookings);
        mydb = new DataClass(this);

        AutoCompleteTextView reso = (AutoCompleteTextView) findViewById(R.id.resources);
        ListView list = (ListView)findViewById(R.id.bookings) ;
        Button show = (Button)findViewById(R.id.show);
        Cursor res = mydb.getresourceNames();
        String s[] =new String[res.getCount()];

        int i=0;

        while(res.moveToNext())
        {
            s[i] = res.getString(0);
            i++;
        }

        ArrayAdapter<String> ar = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,s);
        reso.setAdapter(ar);


        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String resname = reso.getText().toString();

                int rid = mydb.getResID(resname);
                Cursor res = mydb.viewBookings(rid);
                res.moveToFirst();
                String s1[] = new String[1+res.getCount()];


                s1[0] = "user | sdate | edate";
                int count =res.getCount();
                int i=1;
                while(i<=count)
                {
                   s1[i] = mydb.userName(res.getInt(0))+ " | "+res.getString(1)+" | "+res.getString(2);
                   res.moveToPosition(i);
                    i++;
                }

                ArrayAdapter<String> ar = new ArrayAdapter<>(viewBookings.this, android.R.layout.simple_list_item_1,s1);
                list.setAdapter(ar);


            }
        });




    }
}