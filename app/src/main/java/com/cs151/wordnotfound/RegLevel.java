package com.cs151.wordnotfound;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class RegLevel extends AppCompatActivity {
    int level;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg_level);

        //This will be used to push the level to play
        Intent intent = new Intent(this, RegPlay.class);
        Bundle bundle = new Bundle();

        ImageButton regButton = (ImageButton) findViewById(R.id.regularHome);
        regButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(RegLevel.this, MainActivity.class));
            }
        });

        Button regLevelOne = (Button) findViewById(R.id.regSelect1);
        regLevelOne.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                bundle.putInt("level", 0);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        Button regLevelTwo = (Button) findViewById(R.id.regSelect2);
        regLevelTwo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                bundle.putInt("level", 1);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        Button regLevelThree = (Button) findViewById(R.id.regSelect3);
        regLevelThree.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                bundle.putInt("level", 2);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        Button regLevelFour = (Button) findViewById(R.id.regSelect4);
        regLevelFour.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                bundle.putInt("level", 3);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        Button regLevelFive = (Button) findViewById(R.id.regSelect5);
        regLevelFive.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                bundle.putInt("level", 4);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
}