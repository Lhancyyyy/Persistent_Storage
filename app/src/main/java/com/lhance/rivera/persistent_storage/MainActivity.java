package com.lhance.rivera.persistent_storage;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    TextView tName, tPwd;
    String name;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tName = findViewById(R.id.etName);
        tPwd = findViewById(R.id.etPwd);

    }
    public void nextActivity(View v){
        Intent i = new Intent( this, Activity2.class);
        startActivity(i);
    }
    public void saveData(View v){
       name = tName.getText().toString();
        String password = tPwd.getText().toString();
        SharedPreferences sp = getSharedPreferences("data", MODE_PRIVATE);
        SharedPreferences.Editor writer =sp.edit();
        writer.putString("uname", name);
        writer.putString("pass", password);
        writer.commit();
        Toast.makeText(this, "Saved Successfully...", Toast.LENGTH_LONG).show();
    }

   public void saveInternal(View v){
        FileNotFoundException fos = null;
        try {
            fos = openFileOutput("internal.txt", Context.MODE_PRIVATE);
            name = findViewById(R.id.etName).toString();
            fos.write(name.getBytes() );
        } catch(Exception e) {
            Toast.makeText(this, "Error writing to internal file...", Toast.LENGTH_LONG).show();
       } finally {
            try {
                fos.close();
            } catch (Exception e) {
                e.printStackTrace();
            }


        }