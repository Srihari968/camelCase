package com.example.camelcasetestt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TimePicker;

public class selectEDate extends AppCompatActivity {

    DataClass mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_edate);

        mydb = new DataClass(this);


        Intent cintent = getIntent();
        int id = cintent.getIntExtra("id",0);
        CalendarView cv = (CalendarView) findViewById(R.id.calendarView);
        TimePicker tp = (TimePicker)findViewById(R.id.time);
        Button b = (Button)findViewById(R.id.button);
        EditText rem = (EditText)findViewById(R.id.remarks);
        b.setText("NEXT");

        String date[] =new String[1];

        String hr = Integer.toString(tp.getHour());
        String min = Integer.toString(tp.getMinute());
        if(hr.length() == 1)
            hr = "0"+hr;
        if(min.length()==1)
            min = "0"+min;

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
                String cdt;

                String hr = Integer.toString(tp.getHour());
                String min = Integer.toString(tp.getMinute());
                if(hr.length() == 1)
                    hr = "0"+hr;
                if(min.length()==1)
                    min = "0"+min;

                 cdt = date[0]+" "+hr+":"+min;



                mydb.insertEDate(id,cdt);
                mydb.insertRemarks(id,rem.getText().toString());
                Intent intent = new Intent(getApplicationContext(),targetUsers.class);
                intent.putExtra("id",id);

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