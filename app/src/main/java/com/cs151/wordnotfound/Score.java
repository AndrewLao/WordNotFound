package com.cs151.wordnotfound;

/**
 * Score provided to each word
 */
public class Score {
    private int score;

    /**
     * Sets a score
     * @param score
     * Score to be set
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * Gets score on record
     * @return int
     * current score
     */
    public int getScore() {
        return score;
    }
}
