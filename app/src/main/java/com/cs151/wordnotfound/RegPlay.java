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
    boolean directionChecked = false;
    boolean directionChecked2 = false;
    boolean[] b = new boolean[49];
    ArrayList<TextView> tmp = new ArrayList<TextView>();
    int startingPos, trackPos, trackPos2;

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
//        WordBank bank = new WordBank(false).getLevel(1);
//        String str = bank.getFormat();
//        System.out.println(bank.toString());
        for (int i = 0; i < 49; i++)
        {
            list.add(new Word(Integer.toString(i)));
        }

        //Grid adapter, add the list to the grid
        GridAdapter adapter = new GridAdapter(this, list);
        grid.setAdapter(adapter);

        //Manage item clicks
        grid.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                TextView a;
                int action = event.getActionMasked();
                float currentPosX = event.getX();
                float currentPosY = event.getY();
                int pos = grid.pointToPosition((int) currentPosX, (int) currentPosY);

//               if(action == MotionEvent.ACTION_DOWN){
//                    System.out.println(pos + "action down");
//                   if (pos != -1) {
//                       startingPos = pos;
//                       a = (TextView) grid.getChildAt(pos).findViewById(R.id.let);
//                       if (!tmp.contains(a)) {
//                           a.setBackgroundColor(Color.parseColor("#696969"));
//                           tmp.add(a);
//                           ans += a.getText();
//                       }
//                   }
//               }
               if(action == MotionEvent.ACTION_MOVE){
                   if (pos != -1) {
                       //First grid selected
                       if (!directionChecked) {
                            startingPos = pos;
                            directionChecked = true;
                            System.out.print("startingPos: " + startingPos);
                            System.out.println("pos: " + pos);
                            a = (TextView) grid.getChildAt(pos).findViewById(R.id.let);
                            if (!tmp.contains(a)) {
                                a.setBackgroundColor(Color.parseColor("#696969"));
                                tmp.add(a);
                                ans += a.getText();
                                System.out.println(ans);
                            }
                        }
                       //Every other grid
                       else if (!directionChecked2 && pos != startingPos) {
                           trackPos = pos - startingPos;
                           trackPos2 = pos;
                           directionChecked2 = true;
                           System.out.print("startingPos: " + startingPos);
                           System.out.println("pos: " + pos);
                           System.out.println("trackPos: " + trackPos);
                            a = (TextView) grid.getChildAt(pos).findViewById(R.id.let);
                            if (!tmp.contains(a)) {
                                a.setBackgroundColor(Color.parseColor("#696969"));
                                tmp.add(a);
                                ans += a.getText();
                                System.out.println(ans);
                            }
                       }
                       else if (trackPos2 + trackPos == pos) {
                           trackPos2 += trackPos;
                           a = (TextView) grid.getChildAt(pos).findViewById(R.id.let);
                           System.out.print("startingPos: " + startingPos);
                           System.out.println("pos: " + pos);
                           System.out.println("trackPos: " + trackPos);
                           if (!tmp.contains(a)) {
                               a.setBackgroundColor(Color.parseColor("#696969"));
                               tmp.add(a);
                               ans += a.getText();
                               System.out.println(ans);
                           }
                       }
                   }
               }
                //Submit here
                if(action == MotionEvent.ACTION_UP){
                    //Check if the word exists in the wordbank
                    for(TextView b: tmp) {
                        b.setBackgroundColor(Color.parseColor("#ADDDFF"));
                    }
                    tmp.clear();
                    ans = "";
                    directionChecked = false;
                    directionChecked2 = false;
                }
                return true;
            }
        });
    }

}