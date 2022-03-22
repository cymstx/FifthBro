package com.example.fifthbro;

import java.util.ArrayList;
import java.util.HashMap;

public class ClubClass {
    public static HashMap<String, ClubClass> clubList = new HashMap<>();

    public ArrayList<UserClass> users;
    public String clubName;
    public HashMap<String, ItemClass> inventory;
    public HashMap<ItemClass, UserClass> loanedOut;

    // constructor
    public ClubClass(){

    }
    public ClubClass(String name){
        this.inventory = new HashMap<>();
        this.clubName = name;
        this.users = new ArrayList<>();
        clubList.put(name, this);


        this.loanedOut = new HashMap<>();
    }
}
