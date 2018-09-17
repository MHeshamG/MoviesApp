package com.example.mheshamg.xmovies.utils;

import android.util.Log;

public class DateFormater {

    private static final String TAG=DateFormater.class.getSimpleName();

    private static String day;
    private static String month;
    private static String year;

    private DateFormater(){}

    public static void parseDate(String date){
        String[] dateArray=date.split("-");
        year=dateArray[0];
        month=dateArray[1];
        day=dateArray[2];
        Log.i(TAG,year+" "+month+" "+day);
    }
}
