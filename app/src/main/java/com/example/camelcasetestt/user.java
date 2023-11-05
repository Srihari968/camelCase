package com.example.camelcasetestt;

import android.app.Application;
import android.app.Presentation;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class user extends Application {

    String name;
    int uid=1;

     public user(Context context)
    {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.apply();

        SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(context);
        uid = sh.getInt("uid", 1);

    }
    public user()
    {

    }




    int getUid()
    {
        return uid;
    }

    void setUId(int id)
    {
        uid = id;
    }


}
