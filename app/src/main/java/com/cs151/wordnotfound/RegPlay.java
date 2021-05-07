package com.cs151.wordnotfound;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.List;

public class RegPlay extends AppCompatActivity {

    GridView grid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg_play);

        ImageButton regPlayLevel = (ImageButton) findViewById(R.id.regPlayToRegLevel);
        regPlayLevel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(RegPlay.this, RegLevel.class));
            }
        });

        grid = findViewById(R.id.regGame);
        ArrayList<Word> list = new ArrayList<Word>();
        for (int i = 0; i < 49; i++) {
            list.add(new Word(Integer.toString(i)));
        }
        GridAdapter adapter = new GridAdapter(this, list);
        grid.setAdapter(adapter);
    }
}