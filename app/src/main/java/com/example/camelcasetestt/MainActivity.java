package com.example.camelcasetestt;

import android.content.Intent;
import android.os.Bundle;

import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferListener;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferObserver;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferState;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferUtility;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;

import androidx.appcompat.app.AppCompatActivity;

import android.os.StrictMode;
import android.view.View;

import androidx.navigation.ui.AppBarConfiguration;

import com.example.camelcasetestt.databinding.ActivityMainBinding;

import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;


public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;

    DataClass mydb;
private ActivityMainBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        TextView tv = (TextView)findViewById(R.id.textView);
        Button viewBookings = (Button)findViewById(R.id.viewResBookings);
        user u1 = new user(getApplicationContext());
        Button tt = (Button)findViewById(R.id.tt);



        mydb = new DataClass(this);


        File database = new File("/data/data/com.example.camelcasetestt/databases/IITDHResourcesApp.db");
        final AmazonS3 s3 = new AmazonS3Client(new BasicAWSCredentials(getString(R.string.S3AcsessCode), getString(R.string.S3SecretCode)));
        TransferUtility tu = new TransferUtility(s3,getApplicationContext());
        //TransferObserver to = tu.download("camlecasetest","IITDHResources.db",database);
        Button up = (Button)findViewById(R.id.upload);
        Button down = (Button)findViewById(R.id.download);

        Button book = (Button) findViewById(R.id.book);
        ImageButton prof=(ImageButton)findViewById(R.id.prof);

        tv.setText("Hello "+mydb.userName(u1.getUid()));

        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),BookResources.class);
                startActivity(intent);
            }
        });

        up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TransferObserver to = tu.upload("camelcasetest","IITDHResourcesApp.db",database);
                TransferListener tranl = new TransferListener() {
                    @Override
                    public void onStateChanged(int id, TransferState state) {
                        if(state.equals(TransferState.COMPLETED))
                        {
                            Toast.makeText(MainActivity.this, "Upload Completed", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onProgressChanged(int id, long bytesCurrent, long bytesTotal) {

                    }

                    @Override
                    public void onError(int id, Exception ex) {

                    }
                };

                to.setTransferListener(tranl);
            }
        });

        down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TransferObserver to = tu.download("camelcasetest","IITDHResourcesApp.db",database);
                TransferListener tranl = new TransferListener() {
                    @Override
                    public void onStateChanged(int id, TransferState state) {
                        if(state.equals(TransferState.COMPLETED))
                        {
                            Toast.makeText(MainActivity.this, "Download Completed", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onProgressChanged(int id, long bytesCurrent, long bytesTotal) {

                    }

                    @Override
                    public void onError(int id, Exception ex) {

                    }
                };

                to.setTransferListener(tranl);
            }
        });

        prof.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),viewProfile.class);
                startActivity(intent);
            }
        });

        viewBookings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),viewBookings.class);
                startActivity(intent);

            }
        });


        tt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getApplicationContext(),timetable.class);
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