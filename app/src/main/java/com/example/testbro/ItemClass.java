package com.example.testbro;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ItemClass implements Serializable {
    private String name, itemID, clubID, availability;
    private HashMap<String, BookingObj> log;

    public ItemClass(){

    }
    public ItemClass(String name, String clubID){
        this.clubID = clubID;
        this.name = name;
        this.availability = "Available";
        this.itemID = UUID.randomUUID().toString();
        this.log = new HashMap<String, BookingObj>();
        this.log.put("asdf", new BookingObj("string", "string1", "string3", "string6", new TimePeriod(new Date(), new Date())));
    }

    public String getAvailability(){
        return availability;
    }

    public boolean isInStock(){
        if(this.availability == "Available"){
            return true;
        }
        return false;
    }

    public String getName() {
        return name;
    }
    public String getItemID(){
        return itemID;
    }
    public String getClubID(){
        return clubID;
    }
    public void setLog(HashMap<String, BookingObj> log){
        try{
            this.log.putAll(log);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public HashMap<String, BookingObj> getLog() {
        return log;
    }
}
