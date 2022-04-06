package com.example.testbro;

import java.io.Serializable;
import java.util.HashMap;

public class UserClass implements Serializable {

    private String name, email, clubID, userID, phone, clubName;
    private HashMap<String, BookingObj> bookings;
    public UserClass(){

    }
    public UserClass(String club, String name, String email, String phone, String clubName){
        this.clubID = club;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.clubName = clubName;
        this.userID = phone+name;
        this.bookings = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public String getClubID() {
        return clubID;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone(){
        return phone;
    }

    public String getUserID(){
        return userID;
    }
    public String getClubName(){
        return clubName;
    }

    public HashMap<String, BookingObj> getBookings(){
        return bookings;
    }
    public void setBookings(HashMap<String, BookingObj> bookings){
        this.bookings.putAll(bookings);
    }
}
