package com.cs151.wordnotfound;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class RegPlay extends AppCompatActivity {
    String ans = "";
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

        grid = (GridView) findViewById(R.id.regGame);
        ArrayList<Word> list = new ArrayList<Word>();
        boolean[] b = new boolean[49];
        //Create grid of letters here
        for (int i = 0; i < 49; i++) {
            list.add(new Word(Integer.toString(i)));
        }

        //Grid adapter, add the list to the grid
        GridAdapter adapter = new GridAdapter(this, list);
        grid.setAdapter(adapter);

        //Manage item clicks
        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView a = view.findViewById(R.id.let);
                if(b[position] == true) {
                    a.setBackgroundColor(Color.parseColor("#ADDDFF"));
                    b[position] = false;
                }
                else {
                    ans += (a.getText());
                    a.setBackgroundColor(Color.parseColor("#696969"));
                    b[position] = true;
                }
                System.out.println(ans);
                //Add some check to see if the position is already selected

            }
        });

    }

}