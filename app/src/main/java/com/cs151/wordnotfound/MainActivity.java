package com.cs151.wordnotfound;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import android.widget.ImageButton;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.ExecutionException;

/**
 * Launch screen of the app
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    SharedPreferences sharedPreferences;
    boolean darkStatus;
    private static String DARK_MODE = "Dark_mode_prefs";
    private static String NIGHT = "night_on";

    /**
     * Main Activity that starts the application
     * @param savedInstanceState
     * Standard Android Bundle
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = getSharedPreferences(DARK_MODE, MODE_PRIVATE);
        darkStatus = sharedPreferences.getBoolean(NIGHT, false);

        if(darkStatus){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }else{
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }

        if(Build.VERSION.SDK_INT >= 21){
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.BLACK);
        }
//        MediaPlayer player = MediaPlayer.create(MainActivity.this, R.raw.background_music);
//        player.start();
//        player.setLooping(true);

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