package model;

import exceptions.InvalidDayException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

/**
 * Test suite for Schedule.
 */

class ScheduleTest {

    private Schedule schedule;

    @BeforeEach
    void runBefore() {
        schedule = new Schedule();
    }

//    @Test //dummy test for Schedule default constructor, necessary for Jackson functionality
//    void testScheduleDefaultConstructor() {
//
//    }

    @Test
    void testAddOneEntrySuccess() {
        try {
            schedule.addEntry("Mangrove", "Monday");

        } catch (InvalidDayException e) {
            System.out.println("Was not expecting to catch an exception!");
            fail();
            e.printStackTrace();
        }
    }

    @Test
    void testAddOneEntryFailure() {
        try {
            schedule.addEntry("Mangrove", "THIS IS NOT A REAL DAY!");
            System.out.println("Not expecting to execute this line of code!");
            fail();
        } catch (InvalidDayException e) {
            e.printStackTrace();
            System.out.println("Invalid day as expected");
        }
    }

    @Test
    void testRemoveEntrySuccess() {
        try {
            schedule.addEntry("Mangrove", "Monday");
            assertTrue(schedule.removeEntry("Mangrove"));
        } catch (InvalidDayException e) {
            System.out.println("Not expecting to execute this line of code!");
            e.printStackTrace();
            fail();
        }
    }

    @Test
    void testRemoveEntryFailure() {
        try {
            schedule.addEntry("Mangrove", "Monday");
            assertFalse(schedule.removeEntry("THE WRONG PLANT"));
        } catch (InvalidDayException e) {
            System.out.println("Not expecting to execute this line of code!");
            e.printStackTrace();
            fail();
        }
    }

    @Test
    void testDayToIndexSuccess() {
        try {
            assertEquals(0, schedule.dayToIndex("Monday"));
            assertEquals(1, schedule.dayToIndex("Tuesday"));
            assertEquals(2, schedule.dayToIndex("Wednesday"));
            assertEquals(3, schedule.dayToIndex("Thursday"));
            assertEquals(4, schedule.dayToIndex("Friday"));
            assertEquals(5, schedule.dayToIndex("Saturday"));
            assertEquals(6, schedule.dayToIndex("Sunday"));

        } catch (InvalidDayException e) {
            e.printStackTrace();
            System.out.println("Not expecting to execute this line of code!");
            fail();
        }
    }

    @Test
    void testDayToIndexFailure() {
        try {
            schedule.dayToIndex("THIS IS NOT A REAL DAY!");
            System.out.println("Not expecting to execute this line of code!");
            fail();
        } catch (InvalidDayException e) {
            e.printStackTrace();
            System.out.println("Invalid day as expected!");
        }
    }
}