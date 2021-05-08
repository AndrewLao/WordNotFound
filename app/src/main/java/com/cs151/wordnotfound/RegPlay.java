package com.cs151.wordnotfound;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class RegPlay extends AppCompatActivity {
    String ans = "";
    GridView grid;
    ArrayList<Word> list = new ArrayList<Word>();
    boolean[] b = new boolean[49];
    @SuppressLint("ClickableViewAccessibility")
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
        //Create grid of letters here
        for (int i = 0; i < 49; i++) {
            list.add(new Word(Integer.toString(i)));
        }

        //Grid adapter, add the list to the grid
        GridAdapter adapter = new GridAdapter(this, list);
        grid.setAdapter(adapter);
        ArrayList<TextView> tmp = new ArrayList<TextView>();
        //Manage item clicks
        grid.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                TextView a;
                int action = event.getActionMasked();
                float currentPosX = event.getX();
                float currentPosY = event.getY();
                int pos = grid.pointToPosition((int) currentPosX, (int) currentPosY);

//                if(action == MotionEvent.ACTION_DOWN){
//                    System.out.println(pos + "action down");
//                }
                if(action == MotionEvent.ACTION_MOVE){
                    System.out.println(pos);
                    if (pos != -1) {
                        a = (TextView) grid.getChildAt(pos).findViewById(R.id.let);
                        a.setBackgroundColor(Color.parseColor("#696969"));
                        tmp.add(a);
                    }
                }

                //Submit here
                if(action == MotionEvent.ACTION_UP){
                    System.out.println(pos+"up");
                    for(TextView b: tmp) {
                        b.setBackgroundColor(Color.parseColor("#ADDDFF"));
                    }
                }

                return true;
            }
        });
    }

}