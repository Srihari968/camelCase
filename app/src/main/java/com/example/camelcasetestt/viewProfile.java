package com.example.camelcasetestt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;

public class viewProfile extends AppCompatActivity {
    DataClass mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profile);

        mydb  = new DataClass(this);
        user u1= new user(getApplicationContext());


        TextView name = (TextView) findViewById(R.id.profilename);
        TextView batch = (TextView) findViewById(R.id.Batch);
        TextView branch = (TextView)findViewById(R.id.branch);
        TextView roll = (TextView)findViewById(R.id.roll);
        Button addUser = (Button)findViewById(R.id.addUser);
        Button chuser = (Button)findViewById(R.id.changeUser);
        batch.setText(mydb.userBatch(u1.getUid()));
        Button back = (Button)findViewById(R.id.back);


        name.setText(mydb.userName(u1.getUid()));
        branch.setText(mydb.userBranch(u1.getUid()));
        roll.setText(mydb.userRoll(u1.getUid()));



        addUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),newUser.class);
                startActivity(intent);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });

       // chuser.setOnClickListener();
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        mydb.dbClose();
    }
}