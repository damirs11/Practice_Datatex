package com.company.Utils;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;

public class DateUtils {

    private GregorianCalendar gc = new GregorianCalendar();
    private Calendar calendar = Calendar.getInstance();
    private Random random = new Random();

    public Date takeRandomDate(int lowerBound, int upperBound){

        int year = random.nextInt(upperBound - lowerBound) + lowerBound;
        int dayOfYear = random.nextInt(gc.getActualMaximum(Calendar.DAY_OF_YEAR)) + 1;

        gc.set(Calendar.YEAR, year);
        gc.set(Calendar.DAY_OF_YEAR, dayOfYear);

        return gc.getTime();
    }

    public Date addDays(Date date, int days){
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_YEAR, days);

        return calendar.getTime();
    }
}
