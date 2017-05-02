/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tweetAnalyze;

/**
 *
 * @author gerald
 */
public class CustomDate {
    private int day;
    private int month;
    private int year;
    private int hour;
    private String weekDay;
    
    /* A set of constructors */
    public CustomDate() {
        setDay(-1);
        setMonth(-1);
        setYear(-1);
        setHour(-1);
    }
    
    public CustomDate(int day, int month, int year, int hour) {
        setDay(day);
        setMonth(month);
        setYear(year);
        setHour(hour);
    }
    
    public CustomDate(int day, int month, int year) {
        setDay(day);
        setMonth(month);
        setYear(year);
        setHour(-1);
    }
    
    public CustomDate(CustomDate other) {
        setDay(other.day);
        setMonth(other.month);
        setYear(other.year);
        setHour(other.hour);
    }
    
    
    /* A set of getters */
    public int getDay() { return this.day; }
    public int getMonth() { return this.month; }
    public int getYear() { return this.year; }
    public int getHour() { return this.hour; }
    public String getWeekDay() { return this.weekDay; }
    
    /* A set of setters */
    public void setDay(int day) { this.day = day; }
    public void setMonth(int month) { this.month = month; }
    public void setYear(int year) { this.year = year; }
    public void setHour(int hour) { this.hour = hour; }
    public void setWeekDay(String weekDay) { this.weekDay = weekDay; }
    
    /* Tells if two objects represent the same date */
    public boolean isEqual(CustomDate other) {
        if(year != other.year)
            return false;
        
        if(month != other.month)
            return false;
        
        if(day != other.day)
            return false;
        
        if(hour != other.hour)
            return false;
        
        return true;
    }
    
    /* Increment using the scale */
    /* 1 = hour, 4 = year */
    public void incrementDate(int scale) {
        switch(scale) {
            case 1:
                addHour();
                break;
                
            case 2: 
                addDay();
                break;
                
            case 3:
                addMonth();
                break;
                
            case 4:
                addYear();
                break;
                
            default:
                break;
        }
        
        computeWeekDay();
    }
    
    /* Computes the day of the week */
    public void computeWeekDay() {
        int c = (14 - month)/12;
        int a = year - c;
        int m = month + 12*c - 2;
        int j = (day + a + a/4 - a/100 + a/400 + (31*m)/12 ) % 7;
        
        switch (j) {
            case 0:
                weekDay = "Sun";
                break;
        }
    }
    
    /* Adds an hour */
    public void addHour() {
        this.hour += 1;
        
        if(this.hour == 24) {
            this.hour = 0;
            addDay();
        }
    }
    
    /* Adds a day */
    public void addDay() {
        this.day += 1;
        
        switch(this.day) {
            case 29:
                if(this.month == 2 && !isLeapfrog(this.year)) {
                    this.day = 1;
                    addMonth();
                } 
                break;
            
            case 30:
                if(this.month == 2) {
                    this.day = 1;
                    addMonth();
                }
                break; 
                
            case 31:
                if(!has31()) {
                    this.day = 1;
                    addMonth();
                }
                break;
               
            case 32:
                this.day = 1;
                addMonth();
                break;
                
            default: 
                break;
        }
    }
    
    /* Adds a month */
    public void addMonth() {
        this.month += 1;
        
        if(this.month == 12) {
            this.month= 1;
            addYear();
        }
    }
    
    /* Adds a year */
    public void addYear() {
        this.year += 1;
    }
    
    /* Is the year leapfrog */
    public boolean isLeapfrog(int year) {
        if(year % 4 == 0 && year % 100 != 0)
            return true;
        
        if(year % 400 == 0)
            return true;
        
        return false;
    }
    
    /* Does the month have a 31 ? */
    public boolean has31() {
        if(month == 1)
            return true;
        
        if(month == 3)
            return true;
        
        if(month == 5)
            return true;
        
        if(month == 7)
            return true;
        
        if(month == 8)
            return true;
        
        if(month == 10)
            return true;
        
        if(month == 12)
            return true;
        
        return false;
    }
    
    /* Gets the number of days in a month */
    public int getDaysByMonth(int month, int year) {
        if(month == 2) {
            if(isLeapfrog(year))
                return 29;
            else
                return 28;
        }
        
        if(month % 2 == 0) {
            if(month < 8 || month >= 8)
                return 31;
        }
        
        return 30;
    }
    
    /* Gets the difference between 2 dates, depending on the scale */
    /* 1 = hours, 4 = years */
    public int getGap(CustomDate end, int scale) {
        int diff = 0;
        
        diff = this.year - end.year;
        
        if(scale < 4)
            diff = (diff-1)*12 + (12 - this.month + end.month);
        
        if(scale < 3) {
            int newDiff = 0;
            newDiff += getDaysByMonth(this.month, this.year) - this.day + 1;
            newDiff += end.day;
            
            CustomDate follow = new CustomDate(this);
            for(int i = this.month + 1; !follow.isEqual(end); i++) {
                follow.incrementDate(2);
                newDiff += getDaysByMonth(follow.month, follow.year);
            }
            
            diff = newDiff;
        }

        if(scale < 2)
            diff *= 24;
        
        return diff;
    }
}
