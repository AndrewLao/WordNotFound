package com.cs151.wordnotfound;

public class Word {
    private String word;
    private Score score;

    public Word (String word) {
        this.word = word;
        score = new Score();
        score.setScore(word.length() * 100);

    }

    public String getWord() {
        return word;
    }

    public Score getWordScore() {
        return score;
    }
}
