package com.cs151.wordnotfound;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
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

public class GravPlay extends AppCompatActivity {
    ArrayList<TextView> foundLetterViewHolder = new ArrayList<TextView>();
    ArrayList<TextView> letterViewHolder = new ArrayList<TextView>();
    ArrayList<String> bankList = new ArrayList<String>();
    ArrayList<String> list = new ArrayList<String>();
    ArrayList<TextView> bankTexts;
    ArrayList<Word> wordList;
    boolean firstLetterSelected = false;
    int nextPos, level;
    int scoreSum = 0;
    GridView grid, bankGrid;
    WordBank bank, wordBankList;
    String ans = "";

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grav_play);

        //Color chooser
        TypedValue typedValue = new TypedValue();
        getTheme().resolveAttribute(android.R.attr.colorBackground, typedValue, true);
        int primaryTextColor = typedValue.data;

        //Start timer
        Chronometer simpleChronometer = (Chronometer) findViewById(R.id.simpleChronometer); // initiate a chronometer
        simpleChronometer.start();

        ImageButton gravPlayLevel = (ImageButton) findViewById(R.id.gravPlayToGravLevel);
        gravPlayLevel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(GravPlay.this, GravLevel.class));
            }
        });

        //This will be used to push score to result screen
        Intent intent = new Intent(this, Result.class);
        Bundle bundle = getIntent().getExtras();
        level = bundle.getInt("level");

        Intent restart = new Intent(this, GravPlay.class);
        ImageButton gravPlayRetry = (ImageButton) findViewById(R.id.gravPlayToRetry);
        gravPlayRetry.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt("level", level);
                restart.putExtras(bundle);
                startActivity(restart);
            }
        });

        //Create the wordbank here
        try {
            InputStream raw = getResources().openRawResource(R.raw.grav_level_select);
            BufferedReader is = new BufferedReader(new InputStreamReader(raw, "UTF8"));
            wordBankList = new WordBank(is);
            raw.close();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        //Get the level of the game here
        bank = wordBankList.getLevel(level);

        //Create grid of letters here
        grid = (GridView) findViewById(R.id.gravGame);
        bankGrid = (GridView) findViewById(R.id.gravGameBank);

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

        firstLetterSelected = false;

        //Manage item clicks
        grid.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                TextView a;
                int action = event.getActionMasked();
                float currentPosX = event.getX();
                float currentPosY = event.getY();
                int pos = grid.pointToPosition((int) currentPosX, (int) currentPosY);

                if (action == MotionEvent.ACTION_MOVE) {
                    if(pos != -1) {//if it's a square with a character, not a space between them
                        a = (TextView) grid.getChildAt(pos).findViewById(R.id.let);             //set a (textview) as the letter on the current position
                        if (!(a.getText().equals(" ")) && !firstLetterSelected && !foundLetterViewHolder.contains(a)) {
                            firstLetterSelected = true;//our first letter has been selected
                            nextPos = (pos % 7) + 1;                                        //set the next position to be one position to the right of our current position
                            a.setBackgroundColor(Color.parseColor("#696969"));   //set the background of our current coordinate to gray
                            letterViewHolder.add(a);                                        //add our current position letter to positions we've already selected
                            ans += a.getText();


                            // For every other grid square selected
                        }else{
                            if(!a.getText().equals(" ") && (pos % 7) == nextPos && !foundLetterViewHolder.contains(a)) {
                                nextPos = (pos % 7) + 1;

                                // pos at this stage of the program is equal to nextPos

                                if ((pos - 8) % 7 != 6) {
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
                }


                //This submits the currently highlighted letters for verification
                if (action == MotionEvent.ACTION_UP) {
                    boolean found = false;

                    //Check if the word exists in the wordbank. If so, highlight the letters yellow.
                    for (Word word : bank.getBank()) {
                        if (ans.equals(word.getWord())) {
                            for (TextView b : letterViewHolder) {
                                foundLetterViewHolder.add(b);
                                b.setBackgroundColor(Color.parseColor("#FFFF00"));
                                b.setTextColor(Color.parseColor("#000000"));
                            }
                            //Remove the word from the bank of words and sum up the score
                            bank.getBank().remove(word);
                            scoreSum += word.getWordScore().getScore();
                            TextView scoreText = findViewById(R.id.gravPlayScoreText);
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
                        bundle.putBoolean("isRegPlay", false);
                        bundle.putInt("level", level);
                        intent.putExtras(bundle);
                        startActivity(intent);

                    }

                    //If the word is not found in wordbank, reset the color of the letters
                    if (!found) {
                        for (TextView b : letterViewHolder) {
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
                    firstLetterSelected = false;
                }
                return true;
            }
        });
        }
}