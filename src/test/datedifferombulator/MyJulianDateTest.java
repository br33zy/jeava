package test.datedifferombulator;

import datedifferombulator.MyJulianDate;
import junit.framework.Test;
import junit.framework.TestSuite;
import junit.framework.TestCase;

import java.text.ParseException;

/**
 * MyJulianDate Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>03/08/2011</pre>
 */
public class MyJulianDateTest extends TestCase {
    public MyJulianDateTest(String name) {
        super(name);
    }

    public void setUp() throws Exception {
        super.setUp();
    }

    public void tearDown() throws Exception {
        super.tearDown();
    }

    public void testHappyPathConstruction() {
        try {
            MyJulianDate myJulianDate = new MyJulianDate("02 07 1969");
            assertEquals("02 07 1969", myJulianDate.gregorianDateString);
        } catch (ParseException e) {
            fail("Exception raised on valid date string");
        }
    }

    public void testInvalidDateStringFormat() {
        try {
            MyJulianDate myJulianDate = new MyJulianDate("07 1969");
            fail("Exception not raised for invalid date string");
        } catch (ParseException e) {
            assertTrue("Exception raised for invalid date string", true);
        }
    }

    public void testOutOfRangeDayNumber() {
        try {
            MyJulianDate myJulianDate = new MyJulianDate("00 07 1969");
            fail("Exception not raised for invalid day number");
        } catch (ParseException e) {
            assertTrue("Exception raised for invalid day number", true);
        }
    }

    public void testOutOfRangeMonthNumber() {
        try {
            MyJulianDate myJulianDate = new MyJulianDate("01 13 1969");
            fail("Exception not raised for invalid month number");
        } catch (ParseException e) {
            assertTrue("Exception raised for invalid month number", true);
        }
    }

    public void testOutOfRangeYearNumber() {
        try {
            MyJulianDate myJulianDate = new MyJulianDate("00 07 1899");
            fail("Exception not raised for invalid year number");
        } catch (ParseException e) {
            assertTrue("Exception raised for invalid year number", true);
        }
    }

    public void testInvalidLeapDay() {
        try {
            MyJulianDate myJulianDate = new MyJulianDate("29 02 1999");
            fail("Exception not raised for invalid leap day.");
        } catch (ParseException e) {
            assertTrue("Exception raised for invalid leap day", true);
        }
    }

    public void testValidLeapDay() {
        try {
            MyJulianDate myJulianDate = new MyJulianDate("29 02 2000");
            assertEquals("29 02 2000", myJulianDate.gregorianDateString);
        } catch (ParseException e) {
            fail("Exception raised on valid date string");
        }
    }

    public void testDateSubtractionSimple() {
        try {
            MyJulianDate now = new MyJulianDate("01 03 2000");
            MyJulianDate then = new MyJulianDate("29 02 2000");
            assertEquals(1, now.minus(then));
        } catch (ParseException e) {
            fail("Exception raised on valid date string");
        }
    }

    public void testDateSubtractionYearBoundary() {
        try {
            MyJulianDate now = new MyJulianDate("01 03 2000");
            MyJulianDate then = new MyJulianDate("31 12 1999");
            assertEquals(61, now.minus(then));
        } catch (ParseException e) {
            fail("Exception raised on valid date string");
        }
    }

    public void testDateSubtractionThenLaterThanNow() {
        try {
            MyJulianDate now = new MyJulianDate("01 03 2000");
            MyJulianDate then = new MyJulianDate("31 12 1999");
            assertEquals(-61, then.minus(now));
        } catch (ParseException e) {
            fail("Exception raised on valid date string");
        }
    }

    public void testDateSubtractionEqualDates() {
        try {
            MyJulianDate now = new MyJulianDate("31 12 1999");
            MyJulianDate then = new MyJulianDate("31 12 1999");
            assertEquals(0, now.minus(then));
        } catch (ParseException e) {
            fail("Exception raised on valid date string");
        }
    }

    public void testDateGreaterThanOrEquals() {
        try {
            MyJulianDate now = new MyJulianDate("01 03 2000");
            MyJulianDate then = new MyJulianDate("31 12 1999");
            assertEquals(true, now.greaterThanOrEqual(then));
        } catch (ParseException e) {
            fail("Exception raised on valid date string");
        }
    }

    public void testDateGreaterThanOrEqualsThenLaterThanNow() {
        try {
            MyJulianDate now = new MyJulianDate("01 03 2000");
            MyJulianDate then = new MyJulianDate("31 12 1999");
            assertEquals(false, then.greaterThanOrEqual(now));
        } catch (ParseException e) {
            fail("Exception raised on valid date string");
        }
    }

    public void testDateGreaterThanOrEqualsEqualDates() {
        try {
            MyJulianDate now = new MyJulianDate("31 12 1999");
            MyJulianDate then = new MyJulianDate("31 12 1999");
            assertEquals(true, now.greaterThanOrEqual(then));
        } catch (ParseException e) {
            fail("Exception raised on valid date string");
        }
    }

    public static Test suite() {
        return new TestSuite(MyJulianDateTest.class);
    }
}
