package com.cs151.wordnotfound;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class Result extends AppCompatActivity {
    Boolean isRegPlay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Bundle bundle = getIntent().getExtras();
        String playTime = bundle.getString("time");
        TextView time = findViewById(R.id.resultTimerText);
        time.setText("Time: " + playTime);
        String score = bundle.getString("scoreSum");
        TextView resultScore = findViewById(R.id.resultScoreText);
        resultScore.setText("Score: " + score);
        isRegPlay = bundle.getBoolean("isRegPlay");

        //These will be used to carry level information over to the play classes
        Intent regIntent = new Intent(this, RegPlay.class);
        Intent gravIntent = new Intent(this, GravPlay.class);
        Bundle playBundle = new Bundle();
        playBundle.putInt("level", bundle.getInt("level"));

        ImageButton settingsMain = (ImageButton) findViewById(R.id.results_main);
        settingsMain.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(Result.this, MainActivity.class));
            }
        });

        Button retry = (Button) findViewById(R.id.resultRetryButton);
        retry.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (isRegPlay) {
                    regIntent.putExtras(playBundle);
                    startActivity(regIntent);
                }
                else {
                    gravIntent.putExtras(playBundle);
                    startActivity(gravIntent);
                }
            }
        });

        Button toLevel = (Button) findViewById(R.id.resultLevelButton);
        toLevel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (isRegPlay) {
                    startActivity(new Intent(Result.this, RegLevel.class));
                }
                else {
                    startActivity(new Intent(Result.this, GravLevel.class));
                }
            }
        });
    }
}