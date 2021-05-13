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

import java.util.ArrayList;

public class GravPlay extends AppCompatActivity {
    ArrayList<TextView> foundLetterViewHolder = new ArrayList<TextView>();
    ArrayList<TextView> letterViewHolder = new ArrayList<TextView>();
    ArrayList<String> list = new ArrayList<String>();
    ArrayList<TextView> bankTexts;
    ArrayList<Word> wordList;
    boolean firstLetterSelected = false;
    int nextPos;
    int scoreSum = 0;
    GridView grid;
    WordBank bank;
    String ans = "";

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grav_play);

        //This will be used to push score to result screen
        Intent intent = new Intent(this, Result.class);
        //Color chooser
        TypedValue typedValue = new TypedValue();
        getTheme().resolveAttribute(android.R.attr.colorBackground, typedValue, true);
        int primaryTextColor = typedValue.data;

        //Start timer
        Chronometer simpleChronometer = (Chronometer) findViewById(R.id.simpleChronometer); // initiate a chronometer
        simpleChronometer.start();

        //Back to Level Select Buton
        ImageButton gravPlayLevel = (ImageButton) findViewById(R.id.gravPlayToGravLevel);
        gravPlayLevel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(GravPlay.this, GravLevel.class));
            }
        });

        //Retry button
        ImageButton gravPlayRetry = (ImageButton) findViewById(R.id.gravPlayToRetry);
        gravPlayRetry.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(GravPlay.this, GravPlay.class));
            }
        });

        grid = (GridView) findViewById(R.id.gravGame);
        //Create grid of letters here
        wordList = new ArrayList<Word>();
        //For now, manually add words
        wordList.add(new Word("cloud"));
        wordList.add(new Word("example"));
        wordList.add(new Word("gold"));
        wordList.add(new Word("take"));
        wordList.add(new Word("word"));
        String str = "                 u      md  clogpleexawoldtakeord";
        bank = new WordBank(1, wordList, str);
        //Manually set the wordbank textviews and add them to bankTexts
        TextView test1 = (TextView) findViewById(R.id.test1);
        TextView test2 = (TextView) findViewById(R.id.test2);
        TextView test3 = (TextView) findViewById(R.id.test3);
        TextView test4 = (TextView) findViewById(R.id.test4);
        test1.setText(wordList.get(0).getWord());
        test2.setText(wordList.get(1).getWord());
        test3.setText(wordList.get(2).getWord());
        test4.setText(wordList.get(3).getWord());
        bankTexts = new ArrayList<TextView>();
        bankTexts.add(test1);
        bankTexts.add(test2);
        bankTexts.add(test3);
        bankTexts.add(test4);

        //Add all of the letters in str to the grid
        for (int i = 0; i < str.length(); i++)
        {
                list.add(str.substring(i, i + 1));
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
                        if (!(a.getText().equals(" ")) && !firstLetterSelected) {
                            firstLetterSelected = true;//our first letter has been selected
                            nextPos = (pos % 7) + 1;                                        //set the next position to be one position to the right of our current position
                            a.setBackgroundColor(Color.parseColor("#696969"));   //set the background of our current coordinate to gray
                            letterViewHolder.add(a);                                        //add our current position letter to positions we've already selected
                            ans += a.getText();


                            // For every other grid square selected
                        }else{
                            if(!a.getText().equals(" ") && (pos % 7) == nextPos) {
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
                            for (TextView w : bankTexts) {
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