package com.cs151.wordnotfound;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import android.widget.ImageButton;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button regButton = (Button) findViewById(R.id.mainRegularLevel);
        regButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(MainActivity.this, RegLevel.class));
            }
        });

        Button gravButton = (Button) findViewById(R.id.mainGravLevel);
        gravButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(MainActivity.this, GravLevel.class));
            }
        });


        ImageButton settingButton = (ImageButton) findViewById(R.id.main_title_setting);
        settingButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Settings.class));
            }
        });


    }



}