package com.tkachuk.dday.util;

import java.util.Calendar;
import java.util.Date;

public class DateManager {

    public static Date getCurrentDate(){
        return dateWithoutTime(new Date());
    }

    public static Date getMinus30Date() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(DateManager.getCurrentDate());
        cal.add(Calendar.DATE, -30);
        return dateWithoutTime(cal.getTime());
    }

    public static Date dateWithoutTime(Date date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }
}
