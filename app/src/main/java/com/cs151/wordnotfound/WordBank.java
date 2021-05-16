package com.cs151.wordnotfound;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.Serializable;
import java.util.*;

/**
 * Handles the reading and management of wordbanks provided by a text file
 */
public class WordBank extends AppCompatActivity {
    private ArrayList<Word> bank;
    private int level;
    private String format;

    private String levelFile;
    private boolean levelSelect;//FALSE FOR REG, TRUE FOR LEVEL
    private ArrayList<WordBank> wordBankList;

    /**
     * Functional WordBank object that gets the level of the bank that's called after WordBank(BufferedReader b)
     * @param level
     * level of the wordbank on file
     * @param bank
     * array of all wordbanks
     * @param format
     * string of characters that initializes the wordsearch
     */
    public WordBank(int level, ArrayList<Word> bank, String format) {
        this.level = level;
        this.bank = bank;
        this.format = format;
    }

    /**
     * Creates a new WordBank from a text file that's non-functional
     * @param b
     * Buffered reader that holds the text file
     */
    public WordBank(BufferedReader b) {
        wordBankList = new ArrayList<>();

        try {
            BufferedReader is = b;
            String s = "";

            boolean endOfFile = true;

            while (endOfFile) {
                /*
                line 1 = level
                line 2 = word1, word2, word3
                line 3 = format of grid
                 */


                String str = is.readLine();				//level in line 1


                if(str == null) {						//checks if line read is null, break;
                    endOfFile = false;
                    break;
                }

                level = Integer.parseInt(str);          //reads level of word in line 1

                bank = new ArrayList<Word>();               //new arraylist
                str = is.readLine();					//line 2
                String[] comma = str.split(", ");       //splits line 2

                for(String k: comma) {                  //fills up the bank in our Wordbank object
                    Word word = new Word(k);
                    bank.add(word);
                }

                str = is.readLine();
                format = str;					//line 3

                wordBankList.add(new WordBank(level, bank, format));
            }

//            //close readers
            is.close();

        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * method returns WordBank Object based on level chosen
     * @param pLevel
     * level of the desired wordserach
     * @return Wordbank obj
     */
    public WordBank getLevel(int pLevel) {
        return wordBankList.get(pLevel);
    }

    /**
     * gets format of characters for gridview
     * @return String
     */
    public String getFormat(){
        return format;
    }

    /**
     * returns list of words in the wordbank
     * @return Arraylist
     */
    public ArrayList<Word> getBank(){
        return bank;
    }

    /**
     * returns all levels/wordBanks
     * @return ArrayList
     */
    public ArrayList<WordBank> getWordBank(){
        return wordBankList;
    }

    /**
     * toString
     * @return String
     */
    public String toString() {
        String str = "level = " + level + "\nwordBank = ";
        for(Word k: bank) {
            str += k.getWord() + ", ";
        }
        return str += "\nformat = " + format;
    }
}
