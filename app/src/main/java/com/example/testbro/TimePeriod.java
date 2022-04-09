package com.example.testbro;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimePeriod {
    public String start, end;
    public long duration;

    TimePeriod(){}

    TimePeriod(Date st, Date en){
        this.start = new SimpleDateFormat("EEE d MMM HH:mm").format(st);
        this.end = new SimpleDateFormat("EEE d MMM HH:mm").format(en);
        this.duration = en.getTime() - st.getTime();
    }

    public String getStart() {
        return this.start;
    }

    public String getEnd() {
        return this.end;
    }

    public Date retStart(){
        try {
            return new SimpleDateFormat("EEE d MMM HH:mm").parse(start);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void setStart(String st) {
        this.start = st;
    }

    public void setEnd(String en) {
        this.end = en;
    }
    public Date retEnd(){
        try {
            return new SimpleDateFormat("EEE d MMM HH:mm").parse(end);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
//    public int gHours(){
//        return (int)Math.round(TimeUnit.HOURS.convert(this.duration, TimeUnit.MILLISECONDS));
//    }
    public boolean overlap(TimePeriod that){
        return ((this.retEnd().getTime() > that.retStart().getTime()) && (that.retEnd().getTime() > this.retStart().getTime())
                || (this.retStart().getTime() < that.retEnd().getTime() && this.retEnd().getTime() > that.retEnd().getTime())
                || (this.retStart().getTime() > that.retStart().getTime() && this.retEnd().getTime() < that.retEnd().getTime())
        );
    }
}
