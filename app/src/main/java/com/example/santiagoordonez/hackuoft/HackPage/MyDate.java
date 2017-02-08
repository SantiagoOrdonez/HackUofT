package com.example.santiagoordonez.hackuoft.HackPage;

import android.provider.Settings;

import java.util.Calendar;
import java.util.Date;
/**
 * Date class.
 */
public class MyDate {

    private int month;// 0 <= month <= 11
    private int day; // 1 < day < 31
    private int year;// 2017 < year
    private String months[] = {"January", "February", "March", "April", "May", "June", "July", "August",
            "September", "October", "November", "December"};

    /**
     * Default constructor creates the current date object
     */
    public MyDate(){
        Date today = Calendar.getInstance().getTime();
        String monthPrefix = today.toString().substring(4, 6);
        //format returned is Wed Aug 15 02:45:15 IST 2012
        for (int i = 0; i < months.length; i++) {
            if (months[i].startsWith(monthPrefix)) {
                setMonth(i+1);
                setYear(Integer.parseInt(today.toString().substring(24)));
                setDay(Integer.parseInt(today.toString().substring(8,9)));
            }
        }
    }
    public MyDate(int month, int year, int day){
        this.month = month;
        this.year = year;
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public String getMonthStr(int month) {
        return months[month-1];
    }
    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}