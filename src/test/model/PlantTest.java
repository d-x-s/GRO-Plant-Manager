package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test suite for Plant.
 */

public class PlantTest {

    private Plant plant;

    @BeforeEach
    void runBefore() {
        plant = new Plant("Clover", "Monday");
    }

    @Test //dummy test for Plant default constructor, necessary for Jackson functionality
    void testPlantDefaultConstructor() {
        Plant plant = new Plant();
    }

    @Test
    void testGetPlantName() {
        assertEquals("Clover", plant.getName());
    }

    @Test
    void testGetPlantWateringDay() {
        assertEquals("Monday", plant.getDayOfAction());
    }

}
