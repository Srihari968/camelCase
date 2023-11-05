package com.example.camelcasetestt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class AddResource extends AppCompatActivity {
    DataClass mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_resource);

        mydb = new DataClass(this);
        Button sub = (Button) findViewById(R.id.submit);
        RadioGroup rg = (RadioGroup) findViewById(R.id.type);
        EditText name = (EditText) findViewById(R.id.name);
        EditText loc = (EditText)findViewById(R.id.loc);
        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedId = rg.getCheckedRadioButtonId();
                ContentValues cont = new ContentValues();
                cont.put("Name",name.getText().toString());
                cont.put("Location",loc.getText().toString());
                RadioButton selButton = (RadioButton)findViewById(selectedId);
                cont.put("type",selButton.getText().toString());
                mydb.insertToResources(cont);

                Intent intent = new Intent(getApplicationContext(),BookResources.class);
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