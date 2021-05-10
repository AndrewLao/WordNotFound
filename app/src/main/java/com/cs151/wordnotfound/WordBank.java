package com.cs151.wordnotfound;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class WordBank extends AppCompatActivity{
    // unused class due to issues in regarding android
    private ArrayList<Word> bank;
    private int level;
    private String format;

    private String levelFile;
    private boolean levelSelect;//FALSE FOR REG, TRUE FOR LEVEL
    private ArrayList<WordBank> wordBankList;

    public WordBank(int level, ArrayList<Word> bank, String format) {
        this.level = level;
        this.bank = bank;
        this.format = format;
    }

    public WordBank(InputStream is, BufferedReader br){
        wordBankList = new ArrayList<>();
        is = this.getResources().openRawResource(R.raw.level_select);
        br = new BufferedReader(new InputStreamReader(is));
//        if(levelSelect) {
//            levelFile = "src/gravLevelSelect.txt";
//        }else{
//        }
        try {
//            f = new File(levelFile);
//            System.out.println(f.exists());
//            fr = new FileReader(f);
//            br = new BufferedReader(fr);

            String s = "";
            if(is != null){
                while((s = br.readLine()) != null){
                    System.out.println(s);
                }
            }else{
                System.out.println("NULL");
            }

//            boolean endOfFile = true;
//
//            while (endOfFile) {
//                /*
//                line 1 = level
//                line 2 = word1, word2, word3
//                line 3 = format of grid
//                 */
//
//
//                String str = br.readLine();				//level in line 1
//
//
//                if(str == null) {						//checks if line read is null, break;
//                    endOfFile = false;
//                    break;
//                }
//
//                level = Integer.parseInt(str);          //reads level of word in line 1
//
//                bank = new ArrayList<Word>();               //new arraylist
//                str = br.readLine();					//line 2
//                String[] comma = str.split(", ");       //splits line 2
//
//                for(String k: comma) {                  //fills up the bank in our Wordbank object
//                    Word word = new Word(k);
//                    bank.add(word);
//                }
//
//                str = br.readLine();
//                format = str;					//line 3
//
//                System.out.println(toString());
//
//                wordBankList.add(new WordBank(level, bank, format));
//            }

            //close readers
            br.close();
            is.close();

        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * method returns WordBank Object based on level chosen
     * @param pLevel
     * @return Wordbank obj
     */
    public WordBank getLevel(int pLevel) {
        return wordBankList.get(pLevel);
    }

    /**
     * gets format of characters for gridview
     * @return
     */
    public String getFormat(){
        return format;
    }

    /**
     * returns list of words in the wordbank
     * @return
     */
    public ArrayList<Word> getBank(){
        return bank;
    }

    /**
     * returns all levels/wordBanks
     * @return
     */
    public ArrayList<WordBank> getWordBank(){
        return wordBankList;
    }

    /**
     * toString
     * @return
     */
    public String toString() {
        String str = "level = " + level + "\nwordBank = ";
        for(Word k: bank) {
            str += k.getWord() + ", ";
        }
        return str += "\nformat = " + format;
    }
}
