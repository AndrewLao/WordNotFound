package com.cs151.wordnotfound;


import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Tests the score class
 */
public class ScoreTest {
    /**
     * Method to test the score class
     */
    @Test
    public void scoreTest(){
        Score s = new Score();
        s.setScore(500);
        assertEquals(500, s.getScore());
        s.setScore(1000);
        assertEquals(1000, s.getScore());
    }

}