package com.cs151.wordnotfound;
import java.io.IOException;
import java.io.*;
import java.util.*;

public class WordBank {
    private ArrayList<Word> bank;
    private int level;
    private String format;

    private String levelFile;
    private boolean levelSelect;//FALSE FOR REG, TRUE FOR LEVEL
    private File f;
    private FileReader fr;
    private BufferedReader br;
    private ArrayList<WordBank> wordBankList;

    public WordBank(int level, ArrayList<Word> bank, String format) {
        this.level = level;
        this.bank = bank;
        this.format = format;
    }


    public WordBank(boolean levelSelect){
        this.levelSelect = levelSelect;
        wordBankList = new ArrayList<>();

        if(levelSelect) {
            levelFile = "src/gravLevelSelect.txt";
        }else{
            levelFile = "src/regLevelSelect.txt";
        }
        try {

            f = new File(levelFile);
            fr = new FileReader(f);
            br = new BufferedReader(fr);

            boolean endOfFile = true;

            while (endOfFile) {
                /*
                line 1 = level
                line 2 = word1, word2, word3
                line 3 = format of grid
                 */

                String str = br.readLine();				//level in line 1


                if(str == null) {						//checks if line read is null, break;
                    endOfFile = false;
                    break;
                }

                level = Integer.parseInt(str);          //reads level of word in line 1

                bank = new ArrayList<Word>();               //new arraylist
                str = br.readLine();					//line 2
                String[] comma = str.split(", ");       //splits line 2

                for(String k: comma) {                  //fills up the bank in our Wordbank object
                    Word word = new Word(k);
                    bank.add(word);

                }
                str = br.readLine();
                format = str;					//line 3

                System.out.println(toString());

                wordBankList.add(new WordBank(level, bank, format));
            }

            //close readers
            br.close();
            fr.close();

        }catch(IOException e){
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
