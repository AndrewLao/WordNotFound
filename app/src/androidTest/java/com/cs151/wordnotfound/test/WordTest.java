package com.cs151.wordnotfound.test;

import com.cs151.wordnotfound.Word;

import org.junit.Test;

import static org.junit.Assert.*;

public class WordTest {

    @Test
    public void createWordTest(){
        Word w = new Word("hello");
        assertEquals("hello", w.getWord());
        assertEquals(500, w.getWordScore().getScore());
    }

}