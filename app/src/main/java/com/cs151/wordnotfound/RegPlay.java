package com.cs151.wordnotfound;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

public class RegPlay extends AppCompatActivity {
    String ans = "";
    GridView grid;
    ArrayList<Word> list = new ArrayList<Word>();
    boolean directionChecked = false;
    boolean directionChecked2 = false;
    boolean[] b = new boolean[49];
    ArrayList<TextView> letterViewHolder = new ArrayList<TextView>();
    ArrayList<TextView> foundLetterViewHolder = new ArrayList<TextView>();
    int startingPos, direction, nextPos;
    WordBank bank;
    ArrayList<Word> wordList;
    int scoreSum = 0;
    ArrayList<TextView> bankTexts;

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

        ImageButton regPlayRetry = (ImageButton) findViewById(R.id.regPlayToRetry);
        regPlayRetry.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(RegPlay.this, RegPlay.class));
            }
        });

        grid = (GridView) findViewById(R.id.regGame);
        //Create grid of letters here
        wordList = new ArrayList<Word>();
        wordList.add(new Word("cloud"));
        wordList.add(new Word("example"));
        wordList.add(new Word("test"));
        wordList.add(new Word("mercy"));
        String str = "mhtesteeeeybjlrswldtpcraupumymoiqeabllxhfxcrwpgne";
        bank = new WordBank(1, wordList, str);
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

        for (int i = 0; i < str.length(); i++)
        {
            list.add(new Word(str.substring(i,i+1)));
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
                       //First grid selected
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
                       //Every other grid
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
                               System.out.println(i.intValue());
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
                                   System.out.println(ans);
                               }
                           }else{
                               directionChecked2 = false;
                           }
                       }
                       else if (nextPos + direction == pos) {
                           nextPos += direction;
                           a = (TextView) grid.getChildAt(pos).findViewById(R.id.let);
                           if (!letterViewHolder.contains(a)) {
                               a.setBackgroundColor(Color.parseColor("#696969"));
                               letterViewHolder.add(a);
                               ans += a.getText();
                           }
                       }
                   }
               }
                //Submit here
                if(action == MotionEvent.ACTION_UP){
                    boolean found = false;

                    //Check if the word exists in the wordbank
                    for (Word word: bank.getBank()) {
                        if (ans.equals(word.getWord())) {
                            for(TextView b: letterViewHolder) {
                                foundLetterViewHolder.add(b);
                                b.setBackgroundColor(Color.parseColor("#FFFF00"));
                            }
                            bank.getBank().remove(word);
                            scoreSum += word.getWordScore().getScore();

                            //Sums up the score
                            TextView scoreText = findViewById(R.id.regPlayScoreText);
                            scoreText.setText("Score: " + Integer.toString(scoreSum));

                            //Cross out word from wordbank
                            for(TextView w: bankTexts) {
                                if (w.getText().equals(word.getWord())) {
                                    w.setPaintFlags(w.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                                }
                            }

                            found = true;
                            break;
                        }
                    }
                    if (bank.getBank().size() == 0) {
                        startActivity(new Intent(RegPlay.this, Result.class));
                    }
                    //Reset the letters on the grid
                    if (!found) {
                        for(TextView b: letterViewHolder) {
                            if (!foundLetterViewHolder.contains(b))
                            b.setBackgroundColor(Color.parseColor("#ADDDFF"));
                            else {
                                b.setBackgroundColor(Color.parseColor("#FFFF00"));
                            }
                        }
                    }
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