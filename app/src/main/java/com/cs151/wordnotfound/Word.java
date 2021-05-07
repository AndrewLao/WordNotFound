package com.cs151.wordnotfound;

public class Word {
    private String word;
    private Score wordScore;

    public Word (String word) {
        this.word = word;
    }

    public String getWord() {
        return word;
    }

    public Score getScore() {
        return wordScore;
    }
}
