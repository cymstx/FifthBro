package com.example.fifthbro;

public class ItemClass {
    public String status;
    public String name;
    public String log;
    public String timeStamp;

    ItemClass(){

    }
    ItemClass(String name){
        this.name = name;
        this.status = "Available";
    }
}
