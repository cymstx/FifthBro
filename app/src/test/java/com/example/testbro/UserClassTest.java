package com.example.testbro;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class UserClassTest {
    private UserClass testClass;

    @Before
    public void setUp(){
        testClass = new UserClass("swimming", "John Doe", "john@email.com", "91234567", "Swimming");
    }

    @Test
    public void getNameTest() { assertEquals("John Doe", testClass.getName()); }

    @Test
    public void getClubIDTest() {
        assertEquals("swimming", testClass.getClubID());
    }

    @Test
    public void getEmailTest() {
        assertEquals("john@email.com", testClass.getEmail());
    }

    @Test
    public void getPhoneTest() {
        assertEquals("91234567", testClass.getPhone());
    }

    @Test
    public void getUserIDTest() { assertEquals("91234567John Doe", testClass.getUserID()); }

    @Test
    public void getClubNameTest() { assertEquals("Swimming", testClass.getClubName()); }

    @After
    public void tearDown(){
        testClass = null;
    }
}