package com.example.mheshamg.xmovies.utils;

import android.util.Log;

public class DateFormater {

    private static final String TAG=DateFormater.class.getSimpleName();



    private DateFormater(){}

    public static String changeFormat(String date){
        String day,month,year;
        String[] dateArray=date.split("-");
        String resultDate;
        year=dateArray[0];
        month=changeMonth(dateArray[1]);
        day=dateArray[2];
        resultDate=day+" "+month+" "+year;
        return resultDate;
    }

    private static String changeMonth(String month){
       switch (month){
           case "01":
               return "JAN";
           case "02":
               return "FEB";
           case "03":
               return "MAR";
           case "04":
               return "APR";
           case "05":
               return "MAY";
           case "06":
               return "JUN";
           case "07":
               return "JUL";
           case "08":
               return "AUG";
           case "09":
               return "SEP";
           case "10":
               return "OCT";
           case "11":
               return "NOV";
           case "12":
               return "DEC";
           default:
               return "error";
       }
    }

}
