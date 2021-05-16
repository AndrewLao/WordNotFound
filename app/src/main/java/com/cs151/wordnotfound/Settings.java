package com.cs151.wordnotfound;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.Switch;

/**
 * Settings screen
 */
public class Settings extends AppCompatActivity implements View.OnClickListener{
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    private static String DARK_MODE = "Dark_mode_prefs";
    private static String NIGHT = "night_on";
    private static String SWITCH_STATUS = "switch status";
    private static String MUSIC_MODE = "Music_prefs";
    private static String MUSIC_SWITCH_STATUS = "music switch status";
    private static String MUSIC = "music_on";



    boolean switchStatus;
    boolean darkStatus;
    boolean musicSwitchStatus;
    boolean musicStatus;

    /**
     * Initializes Settings
     * @param savedInstanceState
     * Standard Android Bundle
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        sharedPreferences = getSharedPreferences(DARK_MODE, MODE_PRIVATE);
        editor = getSharedPreferences(DARK_MODE, MODE_PRIVATE).edit();

        ImageButton homeButton = (ImageButton) findViewById(R.id.settings_main);
        homeButton.setOnClickListener(this);

        Switch darkSwitch = (Switch) findViewById(R.id.setting_dark);
        Switch MusicSwitch = (Switch) findViewById(R.id.setting_music);

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
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    editor.putBoolean(SWITCH_STATUS, true);
                    editor.putBoolean(NIGHT, true);
                    darkSwitch.setChecked(true);
                    editor.apply();
                }else{
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    editor.putBoolean(SWITCH_STATUS, false);
                    editor.putBoolean(NIGHT, false);
                    darkSwitch.setChecked(false);
                    editor.apply();
                }
            }
        });

//        sharedPreferences = getSharedPreferences(MUSIC_MODE, MODE_PRIVATE);
//        editor = getSharedPreferences(MUSIC_MODE, MODE_PRIVATE).edit();
//        musicSwitchStatus = sharedPreferences.getBoolean(MUSIC_SWITCH_STATUS, true); // light mode default
//        musicStatus = sharedPreferences.getBoolean(MUSIC, true);
//        MusicSwitch.setChecked(musicSwitchStatus);
//
//        if(musicStatus){
//
//        }

    }

    /**
     * Overridden method to switch activities
     * @param v
     * Current view instance
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case (R.id.settings_main):
                openMain();
                break;
        }
    }

    /**
     * Method to start MainActitiy
     */
    public void openMain(){
        startActivity(new Intent(Settings.this, MainActivity.class));
    }
}