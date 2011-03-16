package datedifferombulator;

import java.text.ParseException;

/**
 * Created by IntelliJ IDEA.
 * User: mbreeze
 * Date: 8/03/11
 * Time: 10:30 PM
 * To change this template use File | Settings | File Templates.
 */
public class MyJulianDate {
    public int julianDay;
    public int gregorianDay, gregorianMonth, gregorianYear;
    public String gregorianDateString;

    public MyJulianDate(String dateString) throws ParseException {
        if (validDateString(dateString)) {
            String DMY[] = dateString.split(" ");

            gregorianYear = gregorianYearFromString(DMY[2]);
            gregorianMonth = gregorianMonthFromString(DMY[1]);
            gregorianDay = gregorianDayFromString(DMY[0], gregorianMonth, gregorianYear);

            if (validDateComponents(gregorianDay, gregorianMonth, gregorianYear)) {

                julianDay = julianDayFromGregorianDayMonthYear(gregorianDay, gregorianMonth, gregorianYear);
                gregorianDateString = dateString;

            } else {
                throw new ParseException(dateString + " - Date string format must be 'DD MM YYYY' and represent a valid date", 0);
            }
        } else {
            throw new ParseException(dateString + " - Date string format must be 'DD MM YYYY' and represent a valid date", 0);
        }
    }

    public int minus(MyJulianDate thatDate) {
        return (julianDay - thatDate.julianDay);
    }

    public boolean greaterThanOrEqual(MyJulianDate thatDate) {
        return (julianDay >= thatDate.julianDay);
    }

    private boolean validDateComponents(int gregorianDay, int gregorianMonth, int gregorianYear) {
        return (gregorianDay != -1 && gregorianMonth != -1 && gregorianYear != -1);
    }

    /* Format = "DD MM YYYY" */
    private boolean validDateString(String dateString) {
        return dateString.matches("\\d\\d \\d\\d \\d\\d\\d\\d");
    }

    /* use month and year to flush out leap days */
    private int gregorianDayFromString(String dayString, int monthNumber, int yearNumber) {
        try {
            int day = new Integer(dayString);
            int maximumValidDay = getNumberOfDaysInThisMonthAndYear(monthNumber, yearNumber);

            if (day > 0 && day <= maximumValidDay) {
                return day;
            }
        } catch (Exception e) {
            return -1;
        }
        return -1;
    }

    private int gregorianMonthFromString(String monthString) {
        int month = new Integer(monthString);
        if (month > 0 && month <= 12) {
            return month;
        }
        return -1;
    }

    private int gregorianYearFromString(String yearString) {
        int year = new Integer(yearString);
        if (year >= 1900 && year <= 2010) {
            return year;
        }
        return -1;
    }

    private int getNumberOfDaysInThisMonthAndYear(int monthNumber, int yearNumber) {
        int[] daysInMonths = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

        if (isLeapYear(yearNumber)) {
            daysInMonths[1] = 29;
        }

        return daysInMonths[monthNumber - 1];
    }

    /*
    http://en.wikipedia.org/wiki/Leap_year#Algorithm
       */
    private boolean isLeapYear(int year) {
        if (year % 400 == 0) {
            return true;
        }
        if (year % 100 == 0) {
            return false;
        }
        if (year % 4 == 0) {
            return true;
        }
        return false;
    }

    /*
       Calc lifted from http://en.wikipedia.org/wiki/Julian_day

       Note, Integer division all the way. Java's strong typing helps here!
    */
    private int julianDayFromGregorianDayMonthYear(int day, int month, int year) {
        int julianDay = 0;

        int a = (14 - month) / 12;
        int y = year + 4800 - a;
        int m = month + (12 * a) - 3;

        julianDay = day + (((153 * m) + 2) / 5) + (365 * y) + (y / 4) - (y / 100) + (y / 400) - 32045;

        return julianDay;
    }
}
