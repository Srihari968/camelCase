package com.example.camelcasetestt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;

import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferObserver;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferUtility;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;

import java.io.File;

public class s3 extends AppCompatActivity {
    TransferUtility tu;
    File db;
    AmazonS3 s3;





    s3(Context context)
    {
        final AmazonS3 s3 = new AmazonS3Client(new BasicAWSCredentials(getString(R.string.S3AcsessCode), getString(R.string.S3SecretCode)));

        TransferUtility tu = new TransferUtility(s3,getApplicationContext());


    }

    void upload()
    {
         TransferObserver to = tu.upload("camelcasetest","test2",db);
    }






}