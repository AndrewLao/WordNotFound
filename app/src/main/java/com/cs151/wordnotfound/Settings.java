package com.cs151.wordnotfound;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.Switch;

public class Settings extends AppCompatActivity implements View.OnClickListener{
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    private static String DARK_MODE = "Dark_mode_prefs";
    private static String NIGHT = "night_on";
    private static String SWITCH_STATUS = "switch status";

    boolean switchStatus;
    boolean darkStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        sharedPreferences = getSharedPreferences(DARK_MODE, MODE_PRIVATE);
        editor = getSharedPreferences(DARK_MODE, MODE_PRIVATE).edit();

        ImageButton homeButton = (ImageButton) findViewById(R.id.settings_main);
        homeButton.setOnClickListener(this);

        Switch darkSwitch = (Switch) findViewById(R.id.setting_dark);

        switchStatus = sharedPreferences.getBoolean(SWITCH_STATUS, false); // light mode default
        darkStatus = sharedPreferences.getBoolean(NIGHT, false);

        darkSwitch.setChecked(switchStatus);

        if(darkStatus){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }else{
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }

        darkSwitch.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(darkSwitch.isChecked()){
                    editor.putBoolean(SWITCH_STATUS, true);
                    editor.putBoolean(NIGHT, true);
                    darkSwitch.setChecked(true);
                    editor.apply();
                    recreate();
                }else{
                    editor.putBoolean(SWITCH_STATUS, false);
                    editor.putBoolean(NIGHT, false);
                    darkSwitch.setChecked(false);
                    editor.apply();
                    recreate();
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case (R.id.settings_main):
                openMain();
                break;
        }
    }




    public void openMain(){
        startActivity(new Intent(Settings.this, MainActivity.class));
    }
}