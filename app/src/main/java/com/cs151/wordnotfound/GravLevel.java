package com.cs151.wordnotfound;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

/**
 * Gravitational Wordsearch List
 */
public class GravLevel extends AppCompatActivity {
    int level;

    /**
     * Creates the gravitational wordsearch list
     * @param savedInstanceState
     * Standard Android Bundle
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grav_level);

        //This will be used to push the level to play
        Intent intent = new Intent(this, GravPlay.class);
        Bundle bundle = new Bundle();

        ImageButton gravButton = (ImageButton) findViewById(R.id.gravHome);
        gravButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(GravLevel.this, MainActivity.class));
            }
        });

        Button gravLevelOne = (Button) findViewById(R.id.gravSelect1);
        gravLevelOne.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                bundle.putInt("level", 0);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        Button gravLevelTwo = (Button) findViewById(R.id.gravSelect2);
        gravLevelTwo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                bundle.putInt("level", 1);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        Button gravLevelThree = (Button) findViewById(R.id.gravSelect3);
        gravLevelThree.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                bundle.putInt("level", 2);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        Button gravLevelFour = (Button) findViewById(R.id.gravSelect4);
        gravLevelFour.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                bundle.putInt("level", 3);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        Button gravLevelFive = (Button) findViewById(R.id.gravSelect5);
        gravLevelFive.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                bundle.putInt("level", 4);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
}