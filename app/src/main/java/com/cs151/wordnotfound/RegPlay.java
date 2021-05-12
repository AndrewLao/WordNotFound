package com.cs151.wordnotfound;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Chronometer;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class RegPlay extends AppCompatActivity {
    ArrayList<TextView> foundLetterViewHolder = new ArrayList<TextView>();
    ArrayList<TextView> letterViewHolder = new ArrayList<TextView>();
    ArrayList<String> list = new ArrayList<String>();
    ArrayList<String> bankList = new ArrayList<String>();
    boolean directionChecked = false;
    boolean directionChecked2 = false;
    int startingPos, direction, nextPos, level;
    int scoreSum = 0;
    GridView grid, bankGrid;
    WordBank bank, wordBankList;
    String ans = "";

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg_play);

        //Color chooser
        TypedValue typedValue = new TypedValue();
        getTheme().resolveAttribute(android.R.attr.colorBackground, typedValue, true);
        int primaryTextColor = typedValue.data;

        //Start timer
        Chronometer simpleChronometer = (Chronometer) findViewById(R.id.simpleChronometer); // initiate a chronometer
        simpleChronometer.start();

        ImageButton regPlayLevel = (ImageButton) findViewById(R.id.regPlayToRegLevel);
        regPlayLevel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(RegPlay.this, RegLevel.class));
            }
        });
        
        //This will be used to push score to result screen
        Intent intent = new Intent(this, Result.class);
        Bundle bundle = getIntent().getExtras();
        level = bundle.getInt("level");

        Intent restart = new Intent(this, RegPlay.class);
        ImageButton regPlayRetry = (ImageButton) findViewById(R.id.regPlayToRetry);
        regPlayRetry.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt("level", level);
                restart.putExtras(bundle);
                startActivity(restart);
            }
        });

        //Create the wordbank here
        try {
            InputStream raw = getResources().openRawResource(R.raw.level_select);
            BufferedReader is = new BufferedReader(new InputStreamReader(raw, "UTF8"));
            wordBankList = new WordBank(is);
            raw.close();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        //Get the level of the game here
        bank = wordBankList.getLevel(level);

        //Create grid of letters here
        grid = (GridView) findViewById(R.id.regGame);
        bankGrid = (GridView) findViewById(R.id.regGameBank);

        //add the words to String arraylist bankList
        for (Word a: bank.getBank()) {
            bankList.add(a.getWord());
        }
        //Create the word bank grid
        GridAdapter bankAdapter = new GridAdapter(this, bankList);
        bankGrid.setAdapter(bankAdapter);


        //Add all of the letters in str to the grid
        String str = bank.getFormat();
        for (int i = 0; i < str.length(); i++)
        {
            list.add(str.substring(i,i+1));
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

               if(action == MotionEvent.ACTION_MOVE){
                   if (pos != -1) {
                       // First grid square selected
                       // Initial position set
                       if (!directionChecked) {
                            startingPos = pos;
                            directionChecked = true;
                            a = (TextView) grid.getChildAt(pos).findViewById(R.id.let);
                            if (!letterViewHolder.contains(a)) {
                                a.setBackgroundColor(Color.parseColor("#696969"));
                                letterViewHolder.add(a);
                                ans += a.getText();
                            }
                        }
                       // For the second grid square selected
                       // Made to get the direction the player's moving
                       else if (!directionChecked2 && pos != startingPos) {
                           direction = pos - startingPos;
                           nextPos = pos;
                           directionChecked2 = true;
                           boolean b = false;
                           ArrayList<Integer> cs = new ArrayList<>();
                           int cols = 7;
                           int rows = 7;

                           int length = cols * rows;
                           boolean l = startingPos %  cols > 0;        //has left
                           boolean u = startingPos >= cols;            //has upper
                           boolean r = startingPos %  cols < cols - 1; //has right
                           boolean d = startingPos <  length - cols;   //has lower
                           //collect all existing adjacent cells
                           if (l)      cs.add(startingPos - 1       ); // left
                           if (l && u) cs.add(startingPos - 1 - cols); // upper left
                           if (u)      cs.add(startingPos     - cols); // upper
                           if (u && r) cs.add(startingPos + 1 - cols); // upper right
                           if (r)      cs.add(startingPos + 1       ); // right
                           if (r && d) cs.add(startingPos + 1 + cols); // bottom right
                           if (d)      cs.add(startingPos     + cols); // down
                           if (d && l) cs.add(startingPos - 1 + cols); // bottom left

                           for(Integer i : cs){
                               if(i.intValue() == nextPos){
                                   b = true;
                               }
                           }
                           if(b) {
                               a = (TextView) grid.getChildAt(pos).findViewById(R.id.let);
                               if (!letterViewHolder.contains(a)) {
                                   a.setBackgroundColor(Color.parseColor("#696969"));
                                   letterViewHolder.add(a);
                                   ans += a.getText();
                               }
                           }else{
                               directionChecked2 = false;
                           }
                       }
                       // For ever other grid square selected
                       else if (nextPos + direction == pos) {
                           nextPos += direction;
                           // pos at this stage of the program is equal to nextPos
                           // if the direction is towards the right corner
                           // checks must be done due to 1d array grid implementation
                           if(direction == 8) {
                               // if the previous position isn't on the right edge then proceed
                               if((pos - 8) % 7 != 6) {
                                   a = (TextView) grid.getChildAt(pos).findViewById(R.id.let);
                                   if (!letterViewHolder.contains(a)) {
                                       a.setBackgroundColor(Color.parseColor("#696969"));
                                       letterViewHolder.add(a);
                                       ans += a.getText();
                                   }
                               }else{
                                   nextPos -= direction;  // reset the next position variable
                               }
                           }else if(direction == -8){  // same as before except with respect to the top left corner
                               if((pos + 8) % 7 != 0) {
                                   a = (TextView) grid.getChildAt(pos).findViewById(R.id.let);
                                   if (!letterViewHolder.contains(a)) {
                                       a.setBackgroundColor(Color.parseColor("#696969"));
                                       letterViewHolder.add(a);
                                       ans += a.getText();
                                   }
                               }else{
                                   nextPos -= direction;
                               }
                           }
                           else if(direction == 6) {
                               // if the previous position isn't on the right edge then proceed
                               if((pos - 6) % 7 != 0) {
                                   a = (TextView) grid.getChildAt(pos).findViewById(R.id.let);
                                   if (!letterViewHolder.contains(a)) {
                                       a.setBackgroundColor(Color.parseColor("#696969"));
                                       letterViewHolder.add(a);
                                       ans += a.getText();
                                   }
                               }else{
                                   nextPos -= direction;  // reset the next position variable
                               }
                           }else if(direction == -6){  // same as before except with respect to the top left corner
                               if((pos + 6) % 7 != 6) {
                                   a = (TextView) grid.getChildAt(pos).findViewById(R.id.let);
                                   if (!letterViewHolder.contains(a)) {
                                       a.setBackgroundColor(Color.parseColor("#696969"));
                                       letterViewHolder.add(a);
                                       ans += a.getText();
                                   }
                               }else{
                                   nextPos -= direction;
                               }
                           }
                           else if(direction == 1) {
                               // if the previous position isn't on the right edge then proceed
                               if((pos - 1) % 7 != 6) {
                                   a = (TextView) grid.getChildAt(pos).findViewById(R.id.let);
                                   if (!letterViewHolder.contains(a)) {
                                       a.setBackgroundColor(Color.parseColor("#696969"));
                                       letterViewHolder.add(a);
                                       ans += a.getText();
                                   }
                               }else{
                                   nextPos -= direction;  // reset the next position variable
                               }
                           }else if(direction == -1){  // same as before except with respect to the top left corner
                               if((pos + 1) % 7 != 0) {
                                   a = (TextView) grid.getChildAt(pos).findViewById(R.id.let);
                                   if (!letterViewHolder.contains(a)) {
                                       a.setBackgroundColor(Color.parseColor("#696969"));
                                       letterViewHolder.add(a);
                                       ans += a.getText();
                                   }
                               }else{
                                   nextPos -= direction;
                               }
                           }
                           else{
                               a = (TextView) grid.getChildAt(pos).findViewById(R.id.let);
                               if (!letterViewHolder.contains(a)) {
                                   a.setBackgroundColor(Color.parseColor("#696969"));
                                   letterViewHolder.add(a);
                                   ans += a.getText();
                               }
                           }
                       }
                   }
               }
                //This submits the currently highlighted letters for verification
                if(action == MotionEvent.ACTION_UP){
                    boolean found = false;

                    //Check if the word exists in the wordbank. If so, highlight the letters yellow.
                    for (Word word: bank.getBank()) {
                        if (ans.equals(word.getWord())) {
                            for(TextView b: letterViewHolder) {
                                foundLetterViewHolder.add(b);
                                b.setBackgroundColor(Color.parseColor("#FFFF00"));
                                b.setTextColor(Color.parseColor("#000000"));
                            }
                            //Remove the word from the bank of words and sum up the score
                            bank.getBank().remove(word);
                            scoreSum += word.getWordScore().getScore();
                            TextView scoreText = findViewById(R.id.regPlayScoreText);
                            scoreText.setText("Score: " + Integer.toString(scoreSum));

                            //Cross out word from wordbank
                            for (int i = 0; i < bankGrid.getChildCount(); i++) {
                                TextView w = (TextView) bankGrid.getChildAt(i).findViewById(R.id.let);
                                if (w.getText().equals(word.getWord())) {
                                    w.setPaintFlags(w.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                                }
                            }
                            found = true;
                            break;
                        }
                    }

                    //If all words in the word bank have been found, go to results screen
                    if (bank.getBank().size() == 0) {
                        //Bundle stuff passes the score onto the results screen
                        Bundle bundle = new Bundle();
                        bundle.putString("scoreSum", Integer.toString(scoreSum));
                        bundle.putString("time", simpleChronometer.getText().toString());
                        intent.putExtras(bundle);
                        startActivity(intent);

                    }

                    //If the word is not found in wordbank, reset the color of the letters
                    if (!found) {
                        for(TextView b: letterViewHolder) {
                            if (!foundLetterViewHolder.contains(b))
                            b.setBackgroundColor(primaryTextColor);
                            //Keeps found words highlighted
                            else {
                                b.setBackgroundColor(Color.parseColor("#FFFF00"));
                                b.setTextColor(Color.parseColor("#000000"));
                            }
                        }
                    }
                    //Reset the letter holder, answer string, and directionChecked variables
                    letterViewHolder.clear();
                    ans = "";
                    directionChecked = false;
                    directionChecked2 = false;
                }
                return true;
            }
        });
    }

}