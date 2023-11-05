package com.example.camelcasetestt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class newUser extends AppCompatActivity {

    DataClass mydb;
    user u1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);

        mydb = new DataClass(this);
        EditText name = (EditText) findViewById(R.id.name);
        EditText roll = (EditText) findViewById(R.id.rno);
        EditText email = (EditText) findViewById(R.id.email);
        AutoCompleteTextView batchesTV=(AutoCompleteTextView) findViewById(R.id.batch);
        String batch[]=new String[]{"2023","2024","2025","2026","2027","2028"};
        ArrayAdapter<String> batches = new ArrayAdapter<>(newUser.this, android.R.layout.simple_list_item_1,batch);
        batchesTV.setAdapter(batches);
        AutoCompleteTextView branchesTV = (AutoCompleteTextView)findViewById(R.id.branch);
        EditText pass=(EditText)findViewById(R.id.pass);
        String branch[] = new String[]{"CS","MC","EE","ME","CE","CH","IS"};
        ArrayAdapter<String> branches = new ArrayAdapter<>(newUser.this, android.R.layout.simple_list_item_1,branch);
        branchesTV.setAdapter(branches);
        Button sub = (Button) findViewById(R.id.submitUser);
        u1=new user(getApplicationContext());

        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues cont =new ContentValues();

                cont.put("Name",name.getText().toString());
                cont.put("Branch",branchesTV.getText().toString());
                cont.put("Batch",batchesTV.getText().toString());
                cont.put("type",1);
                cont.put("RollNO",roll.getText().toString().toUpperCase());
                cont.put("email",email.getText().toString());
                cont.put("pw",pass.getText().toString());

                boolean b=mydb.insertToUserTable(cont);
                if(b)
                {
                    Toast.makeText(newUser.this, "User Added", Toast.LENGTH_SHORT).show();
                    SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(newUser.this);
                    SharedPreferences.Editor edit = sharedPreferences.edit();
                    edit.putInt("uid",mydb.IDfromRNO(roll.getText().toString()));
                    edit.apply();
                    Intent intent=new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);

                }
                else {
                    Toast.makeText(newUser.this, "User Already Exists", Toast.LENGTH_SHORT).show();
                    int id = mydb.IDfromRNO(roll.getText().toString());
                    SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(newUser.this);
                    SharedPreferences.Editor edit = sharedPreferences.edit();
                    edit.putInt("uid",id);
                    edit.apply();

                }

            }
        });



    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mydb.dbClose();
    }
}