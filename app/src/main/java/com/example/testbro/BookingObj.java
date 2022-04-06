package com.example.testbro;

import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.UUID;

public class BookingObj {
    public String id;
    private String itemId;
    private String userId;
    private String userName;
    private String itemName;
    private TimePeriod timing;
    boolean isLate = false;
    boolean isCheckOut = false;
    boolean isCheckIn = false;
    boolean isComplete = false;

    BookingObj(){}

    BookingObj(String itemId, String userId,String userName, String itemName, TimePeriod timing){
        this.id = UUID.randomUUID().toString();
        this.itemId = itemId;
        this.userId = userId;
        this.timing = timing;
        this.userName = userName;
        this.itemName = itemName;
    }

    public String getBookingId() {
        return id;
    }
    public TimePeriod getTiming() {
        return timing;
    }

    public String getItemName() {
        return itemName;
    }

    public String getUserName() {
        return userName;
    }

    public void setTiming(TimePeriod timing) {
        this.timing = timing;
    }

    public String getItemId() {
        return itemId;
    }

    public String getUserId() {
        return userId;
    }

    public boolean isComplete() {
        return isComplete;
    }

    public void forceComplete(){
        isComplete = true;
    }
    public void forceUncomplete(){
        isComplete = false;
    }

    public boolean isLate() {
        return isLate;
    }

    public void setLate(boolean late) {
        isLate = late;
    }

    public boolean isCheckIn() {
        return isCheckIn;
    }

    public boolean isCheckOut() {
        return isCheckOut;
    }

    public void setCheckIn(boolean checkIn) {
        isCheckIn = checkIn;
    }

    public void setCheckOut(boolean checkOut) {
        isCheckOut = checkOut;
    }

    @Override
    public String toString() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE d MMM HH:mm", Locale.getDefault());
        return simpleDateFormat.format(this.getTiming().getStart()) + " - "
                +simpleDateFormat.format(this.getTiming().getEnd()) +" by \n"
                +this.getUserName();
    }
}
