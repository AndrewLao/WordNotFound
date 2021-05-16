package com.cs151.wordnotfound;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Tests the Word class
 */
public class WordTest {
    /**
     * Method to test the creation of a Word object
     */
    @Test
    public void createWordTest(){
        Word w = new Word("hello");
        assertEquals("hello", w.getWord());
        assertEquals(500, w.getWordScore().getScore());
    }

}