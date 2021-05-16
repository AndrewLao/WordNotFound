package com.cs151.wordnotfound;

/**
 * A single word in the wordserach
 */
public class Word {
    private String word;
    private Score score;

    /**
     * Creates Word Object
     * @param word
     * The desired word in string form
     */
    public Word (String word) {
        this.word = word;
        score = new Score();
        score.setScore(word.length() * 100);

    }

    /**
     * Gets word on file
     * @return String
     */
    public String getWord() {
        return word;
    }

    /**
     * Gets Score object for this word
     * @return Score
     */
    public Score getWordScore() {
        return score;
    }
}
