package com.example.camelcasetestt;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.io.IOException;

public class BookResources extends AppCompatActivity {

    DataClass mydb;
    @RequiresApi(api = Build.VERSION_CODES.O)


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_resources);
        Intent intent = getIntent();

         mydb = new DataClass(this);
        AutoCompleteTextView reso = (AutoCompleteTextView) findViewById(R.id.resource);
        Button sb = (Button)findViewById(R.id.sdate);
        Cursor res = mydb.getresourceNames();
        Button add = (Button)findViewById(R.id.add);
        user u1 = new user(getApplicationContext());


        String s[] = new String[res.getCount()];
        int i=0;

        while(res.moveToNext())
        {
            s[i]=res.getString(0);
            i++;
        }
        ArrayAdapter<String> ar= new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,s);
        reso.setAdapter(ar);

        sb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int rid = mydb.getResID(reso.getText().toString());


                long id=mydb.insertBookingRid(rid,u1.getUid());
                Intent intent = new Intent(getApplicationContext(),selectStartDate.class);
                intent.putExtra("id",Long.toString(id));
                intent.putExtra("rid",rid);
                startActivity(intent);
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),AddResource.class);
                startActivity(intent);

            }
        });


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mydb.dbClose();

    }
}