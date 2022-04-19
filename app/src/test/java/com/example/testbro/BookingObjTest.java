package com.example.testbro;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.sql.Time;
import java.util.Calendar;
import java.util.Date;

public class BookingObjTest {
    private BookingObj testClass;
    Date start = new Date(122,8,18);
    Date end = new Date(122,9,18);
    TimePeriod timing = new TimePeriod(start, end);
    @Before
    public void setUp(){
        testClass = new BookingObj("d330862e-27dd-469a-9bbe-90d0718accf1", "91234567John", "John", "trunks", timing, "swimming");
    }

    @Test
    public void getClubId() { assertEquals("swimming", testClass.getClubId());}

    @Test
    public void getItemName() { assertEquals("trunks", testClass.getItemName());}

    @Test
    public void getUserName() { assertEquals("John", testClass.getUserName());}

    @Test
    public void getItemId() { assertEquals("d330862e-27dd-469a-9bbe-90d0718accf1", testClass.getItemId());}

    @Test
    public void getUserId() { assertEquals("91234567John", testClass.getUserId());}

    @Test
    public void testToString() {assertEquals("Sun 18 Sep 00:00 - Tue 18 Oct 00:00 by \n" + "John", testClass.toString() ); }
}