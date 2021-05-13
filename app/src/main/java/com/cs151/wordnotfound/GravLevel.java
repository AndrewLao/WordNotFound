package com.cs151.wordnotfound;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class GravLevel extends AppCompatActivity {
    int level;
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
    }
}