package com.example.testbro;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class TimePeriod {
    private Date start, end;
    private long duration;

    TimePeriod(){}

    TimePeriod(Date start, Date end){
        this.start = start;
        this.end = end;
        this.duration = end.getTime() - start.getTime();
    }

    public Date getStart(){return start;}
    public Date getEnd(){return end;}
    public int getHours(){
        return (int)Math.round(TimeUnit.HOURS.convert(this.duration, TimeUnit.MILLISECONDS));
    }
    public boolean overlap(TimePeriod that){
        return ((this.end.getTime() > that.start.getTime()) && (that.end.getTime() > this.start.getTime())
                || (this.start.getTime() < that.end.getTime() && this.end.getTime() > that.end.getTime())
                || (this.start.getTime() > that.start.getTime() && this.end.getTime() < that.end.getTime())
        );
    }
}
