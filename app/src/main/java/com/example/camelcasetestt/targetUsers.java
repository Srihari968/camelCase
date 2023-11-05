package com.example.camelcasetestt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class targetUsers extends AppCompatActivity {

    DataClass mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_target_users);
        mydb = new DataClass(this);
        int id = getIntent().getIntExtra("id",1);

        EditText targ = (EditText) findViewById(R.id.target);
        Button sub = (Button) findViewById(R.id.finsubmit);

        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                mydb.insertTarget(id,targ.getText().toString());

                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);

            }
        });





    }
}