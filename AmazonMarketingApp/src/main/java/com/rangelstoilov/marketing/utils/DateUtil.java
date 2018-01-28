package app.utils;

import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Month;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
    public static String getDateTodayWithSlashes() {
        DateFormat df = new SimpleDateFormat("MM/dd/yyy");
        Date date = new Date();
        return df.format(date);
    }

    public static String changeDateToSlashes(String string) throws ParseException {
        DateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        Date date = format.parse(string);
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        return df.format(date);
    }


    public static String changeDateToDashes(String string) throws ParseException {
        DateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        Date date = format.parse(string);
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return df.format(date);
    }

    public static String stringDateMonth(String string) throws ParseException {
        DateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        Date date = format.parse(string);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return String.valueOf((cal.get(Calendar.MONTH) + 1));
    }

    public static String stringDateDay(String string) throws ParseException {
        DateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        Date date = format.parse(string);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return String.valueOf((cal.get(Calendar.DAY_OF_MONTH)));
    }

    public static String stringDateYear(String string) throws ParseException {
        DateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        Date date = format.parse(string);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return String.valueOf(cal.get(Calendar.YEAR));
    }

    public static String getMonthName(String string) throws ParseException {
        DateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        Date date = format.parse(string);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return new DateFormatSymbols().getMonths()[cal.get(Calendar.MONTH)];
    }

    public static String getCurrentDate() {
        Date date = new Date();
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        return df.format(date);
    }

    public static String convertMonthForGoogle(Month month) {
        String monthConverted = "";
        switch (month) {
            case JANUARY:
                monthConverted = ":1";
                break;
            case FEBRUARY:
                monthConverted = ":2";
                break;
            case MARCH:
                monthConverted = ":3";
                break;
            case APRIL:
                monthConverted = ":4";
                break;
            case MAY:
                monthConverted = ":5";
                break;
            case JUNE:
                monthConverted = ":6";
                break;
            case JULY:
                monthConverted = ":7";
                break;
            case AUGUST:
                monthConverted = ":8";
                break;
            case SEPTEMBER:
                monthConverted = ":9";
                break;
            case OCTOBER:
                monthConverted = ":a";
                break;
            case NOVEMBER:
                monthConverted = ":b";
                break;
            case DECEMBER:
                monthConverted = ":c";
                break;
        }
        return monthConverted;
    }
}
