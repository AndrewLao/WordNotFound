package com.cs151.wordnotfound;
import java.util.ArrayList;
import java.io.FileReader;

public class WordBank {
    private ArrayList<Word> bank;

    public WordBank(String level) {
        readFile(level);
    }

    //FileReader will be used to read a file containing the words being used for each level.
    //New Word object constructed for each word read from the file will be placed into ArrayList bank.
    //Parameter String level is the name of the level notepad
    public void readFile(String level) {

    }

    public ArrayList<Word> getbank() {
        return (ArrayList)bank.clone();
    }
}
