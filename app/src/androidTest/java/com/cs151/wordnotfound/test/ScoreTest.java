package com.cs151.wordnotfound.test;

import com.cs151.wordnotfound.Score;

import org.junit.Test;

import static org.junit.Assert.*;

public class ScoreTest {

    @Test
    public void scoreTest(){
        Score s = new Score();
        s.setScore(500);
        assertEquals(500, s.getScore());
        s.setScore(1000);
        assertEquals(1000, s.getScore());
    }

}