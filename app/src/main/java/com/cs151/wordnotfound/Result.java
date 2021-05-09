package com.cs151.wordnotfound;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class Result extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        ImageButton settingsMain = (ImageButton) findViewById(R.id.results_main);
        settingsMain.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(Result.this, MainActivity.class));
            }
        });

        Button retry = (Button) findViewById(R.id.resultRetryButton);
        retry.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(Result.this, RegPlay.class));
            }
        });

        Button toLevel = (Button) findViewById(R.id.resultLevelButton);
        toLevel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(Result.this, RegLevel.class));
            }
        });
    }
}