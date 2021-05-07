package com.cs151.wordnotfound;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import android.widget.ImageButton;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button regButton = (Button) findViewById(R.id.mainRegularLevel);
        regButton.setOnClickListener(this);

        Button gravButton = (Button) findViewById(R.id.mainGravLevel);
        gravButton.setOnClickListener(this);

        ImageButton settingButton = (ImageButton) findViewById(R.id.main_title_setting);
        settingButton.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case (R.id.mainRegularLevel):
                openRegLevel();
                break;
            case (R.id.mainGravLevel):
                openGravLevel();
                break;
            case (R.id.main_title_setting):
                openSettings();
                break;
        }
    }

    private void openRegLevel(){
        startActivity(new Intent(this, RegLevel.class));
    }

    private void openGravLevel(){
        startActivity(new Intent(this, GravLevel.class));
    }

    private void openSettings(){
        startActivity(new Intent(this, Settings.class));
    }

}