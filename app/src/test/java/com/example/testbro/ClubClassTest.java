package com.example.testbro;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ClubClassTest {

    private ClubClass testClub;

    @Before
    public void setUp(){
        testClub = new ClubClass("Swimming", "swimming");
    }

    @Test
    public void getNameTest(){
        assertEquals("Swimming", testClub.getClubName());
    }

    @Test
    public void getIDTest(){
        assertEquals("swimming", testClub.getClubID());
    }

    @Test
    public void getEmptyLogTest(){
        assertEquals(true, testClub.getLog().isEmpty());
    }

    @After
    public void tearDown(){
        testClub = null;
    }
}