package com.napier.sem;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CountryTest {

    @Test
    void testCountryConstructorAndGetters() {
        Country c = new Country("GBR", "United Kingdom", "Europe", "British Isles", 67886011, 456);

        assertEquals("GBR", c.getCode());
        assertEquals("United Kingdom", c.getName());
        assertEquals("Europe", c.getContinent());
        assertEquals("British Isles", c.getRegion());
        assertEquals(67886011, c.getPopulation());
        assertEquals(456, c.getCapital());
    }

    @Test
    void testToString() {
        Country c = new Country("IRL", "Ireland", "Europe", "British Isles", 4982904, 1447);
        String result = c.toString();
        assertTrue(result.contains("IRL"));
        assertTrue(result.contains("Ireland"));
    }
}

