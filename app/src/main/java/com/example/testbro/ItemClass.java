package com.example.testbro;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ItemClass implements Serializable {
    private String name, itemID, clubID, availability;
    private ArrayList<String> log;

    public ItemClass(){

    }
    public ItemClass(ItemClass itemClass) {
        this.clubID = itemClass.getClubID();
        this.name = itemClass.getName();
        this.availability = itemClass.getAvailability();
        this.itemID = itemClass.itemID;
        this.log = itemClass.getLog();
    }

    public ItemClass(String name, String clubID){
        this.clubID = clubID;
        this.name = name;
        this.availability = "Available";
        this.itemID = UUID.randomUUID().toString();
        this.log = new ArrayList<String>();
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
    public void addToLog(String logID) {
        this.log.add(logID);
    }
    public ArrayList<String> getLog() {
        return log;
    }
}
