package test.datedifferombulator;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * DateDifferOmbulatorApp Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>03/11/2011</pre>
 */
public class DateDifferOmbulatorAppTest extends TestCase {
    public DateDifferOmbulatorAppTest(String name) {
        super(name);
    }

    public void setUp() throws Exception {
        super.setUp();
    }

    public void tearDown() throws Exception {
        super.tearDown();
    }

    public void testHappyPath() {
        assertEquals(
                "02 07 1969, 02 07 1968, 365",
                datedifferombulator.DateDifferOmbulatorApp.mainWrapper("02 07 1969, 02 07 1968")
        );
    }

    public void testEqualDates() {
        assertEquals(
                "02 07 1968, 02 07 1968, 0",
                datedifferombulator.DateDifferOmbulatorApp.mainWrapper("02 07 1968, 02 07 1968")
        );
    }

    public void testSecondGreaterThanFirst() {
        assertEquals(
                "02 07 1970, 02 07 1969, 365",
                datedifferombulator.DateDifferOmbulatorApp.mainWrapper("02 07 1969, 02 07 1970")
        );
    }


    public static Test suite() {
        return new TestSuite(DateDifferOmbulatorAppTest.class);
    }
}
