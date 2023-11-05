package com.example.camelcasetestt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class selectStartDate extends AppCompatActivity {

    DataClass mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_start_date);
        final Date[] currdate = new Date[1];

        final String[] date = new String[1];
        CalendarView cv = (CalendarView) findViewById(R.id.calendarView);
        TimePicker tp = (TimePicker) findViewById(R.id.time);
        Button b = (Button)findViewById(R.id.button);
        mydb =new DataClass(this);
        cv.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                date[0] = dayOfMonth+"-" +(month+1)+"-"+year;

            }
        });

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cintent = getIntent();
                String srid = cintent.getStringExtra("id");
                int resourceid = cintent.getIntExtra("rid",1);
                int rid = Integer.parseInt(srid);
                mydb.insertSDate(Integer.parseInt(srid),date[0]+" "+tp.getHour()+":"+tp.getMinute());


                Intent intent = new Intent(getApplicationContext(),selectEDate.class);
                intent.putExtra("id",rid);


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