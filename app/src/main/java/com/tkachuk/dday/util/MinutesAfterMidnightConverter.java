package com.tkachuk.dday.util;

import android.content.Context;
import android.text.format.DateFormat;

import java.util.Calendar;

public class MinutesAfterMidnightConverter {

    public static int toMinutesAfterMidnight(int hours, int minutes){
        return (hours * 60) + minutes;
    }

    public static Calendar toCalendar (int minutesAfterMidnight, Context context){
        int hours = minutesAfterMidnight / 60;
        int minutes = minutesAfterMidnight % 60;
        boolean is24hour = DateFormat.is24HourFormat(context);

        Calendar calendar = Calendar.getInstance();
        if(is24hour){
            calendar.set(Calendar.HOUR_OF_DAY, hours);
        }else calendar.set(Calendar.HOUR, hours);

        calendar.set(Calendar.MINUTE, minutes);
        return calendar;
    }
}
