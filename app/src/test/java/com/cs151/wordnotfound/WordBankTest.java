package com.cs151.wordnotfound;

import org.junit.Test;

import java.io.BufferedReader;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

/**
 * Tests the WordBank class
 */
public class WordBankTest {
    /**
     * Method to test the creation of WordBanks
     */
    @Test
    public void createWordBankTest(){
        WordBank w = null;
        try {
            BufferedReader bufferedReader = org.mockito.Mockito.mock(BufferedReader.class);
            when(bufferedReader.readLine())
                    .thenReturn("1")
                    .thenReturn("cloud, example, test, mercy")
                    .thenReturn("mhtesteeeeybjlrswldtpcraupumymoiqeabllxhfxcrwpgne");
            w = new WordBank(bufferedReader);
            bufferedReader.close();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        assertEquals("cloud", w.getBank().get(0).getWord());
        assertEquals("example", w.getBank().get(1).getWord());
        assertEquals("test", w.getBank().get(2).getWord());
        assertEquals("mercy", w.getBank().get(3).getWord());
    }

    /**
     * Method to test if a wordbank returns a formatted string
     */
    @Test
    public void formatTest(){
        WordBank w = null;
        try {
            BufferedReader bufferedReader = org.mockito.Mockito.mock(BufferedReader.class);
            when(bufferedReader.readLine())
                    .thenReturn("1")
                    .thenReturn("cloud, example, test, mercy")
                    .thenReturn("mhtesteeeeybjlrswldtpcraupumymoiqeabllxhfxcrwpgne");
            w = new WordBank(bufferedReader);
            bufferedReader.close();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        assertEquals("mhtesteeeeybjlrswldtpcraupumymoiqeabllxhfxcrwpgne", w.getFormat());
    }

    /**
     * Method to test if level retrieval is possible
     */
    @Test
    public void getLevelTest(){
        WordBank w = null;
        try {
            BufferedReader bufferedReader = org.mockito.Mockito.mock(BufferedReader.class);
            when(bufferedReader.readLine())
                    .thenReturn("1")
                    .thenReturn("cloud, example, test, mercy")
                    .thenReturn("mhtesteeeeybjlrswldtpcraupumymoiqeabllxhfxcrwpgne");
            w = new WordBank(bufferedReader);
            bufferedReader.close();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        assertEquals("mhtesteeeeybjlrswldtpcraupumymoiqeabllxhfxcrwpgne" ,w.getLevel(0).getFormat());
    }

}