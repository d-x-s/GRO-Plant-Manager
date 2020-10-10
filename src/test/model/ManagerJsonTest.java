package model;

import exceptions.InvalidDayException;
import org.junit.jupiter.api.BeforeEach;

import java.io.File;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import persistence.ManagerJson;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test suite for ManagerJSON.
 */
public class ManagerJsonTest extends ManagerJson {

    private Schedule schedule;
    private Plant plant;

    @BeforeEach
    void runBefore() {

        ObjectMapper objectMapper = new ObjectMapper();

        schedule = new Schedule();
        plant = new Plant("Mangrove", "Monday");

        try {
            objectMapper.writeValue(new File("./data/testSchedule.json"), schedule);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Not expecting to reach this line of code");
            fail();
        }
    }

    @Test
    void testWritePlantToJsonSuccess() {
        writeNewEntryToJson(plant, 0, "schedule");
    }

    @Test
    void testWritePlantToJsonFailure() {
        writeNewEntryToJson(plant, 0, "This file does not exist!");
    }

    @Test
    void testRemovePlantFromJsonSuccess() {
        try {
            schedule.addEntry("Mangrove", "Monday");
        } catch (InvalidDayException e) {
            e.printStackTrace();
            System.out.println("Not expecting to reach this line code");
            fail();
        }
        assertFalse(findAndRemoveEntryFromJson("Mangrove", "This file does not exist!"));
        assertTrue(findAndRemoveEntryFromJson("Mangrove", "schedule"));
    }

    @Test
    void testRemovePlantFromJsonFailure() {
        try {
            schedule.addEntry("Mangrove", "Monday");
        } catch (InvalidDayException e) {
            e.printStackTrace();
            System.out.println("Not expecting to reach this line code");
            fail();
        }
        assertFalse(findAndRemoveEntryFromJson("Tomato", "schedule"));
    }

    @Test
    void testGetListOfPlantsToWater() {
        try {
                writeNewEntryToJson(plant, 0, "testSchedule");
                assertEquals(1, getListOfEntriesAtWeekDayFromJson(0).size());
            }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}


