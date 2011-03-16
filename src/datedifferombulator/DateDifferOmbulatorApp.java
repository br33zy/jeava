package datedifferombulator;

import java.text.ParseException;

/**
 * Created by IntelliJ IDEA.
 * User: mbreeze
 * Date: 8/03/11
 * Time: 10:29 PM
 * To change this template use File | Settings | File Templates.
 */
public class DateDifferOmbulatorApp {

    public DateDifferOmbulatorApp() {
    }

    // Not sure how to unit test main - so wrapping it with something I can...
    // If I could be bothered, I guess this is where Cucumber comes in.
    public static String mainWrapper(String arg) {
        String[] dates = chompAndSplitToDateStrings(arg);
        if (dates.length == 2) {
            try {
                MyJulianDate first = new MyJulianDate(dates[0]);
                MyJulianDate second = new MyJulianDate(dates[1]);

                Integer difference;
                if (first.greaterThanOrEqual(second)) {
                    difference = first.minus(second);
                    return first.gregorianDateString + ", " + second.gregorianDateString + ", " + difference.toString();
                } else {
                    difference = second.minus(first);
                    return second.gregorianDateString + ", " + first.gregorianDateString + ", " + difference.toString();
                }
            } catch (ParseException e) {
                System.out.println(e);
                return help();
            }
        }
        return help();
    }

    private static String help() {
        return "Enter two valid dates of format 'DD MM YYYY' separated by a comma. E.g. 02 07 1969, 10 01 1921";
    }

    private static String[] chompAndSplitToDateStrings(String string) {
        return string.replaceAll("\\s+$", "").split(", ");
    }

    private static String join(String[] strings, String joiner) {
        String string = "";
        for (int i = 0; i < strings.length; i++) {
            string += strings[i] + joiner;
        }
        return string;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println(mainWrapper(join(args, " ")));
    }

}
