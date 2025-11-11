package com.napier.sem;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the City class.
 */
class CityTest {

    private City city;

    @BeforeEach
    void setUp() {
        city = new City(1, "Shanghai", "China", "Shanghai", 24183300);
    }

    @Test
    void testConstructorAndGetters() {
        assertEquals(1, city.getId());
        assertEquals("Shanghai", city.getName());
        assertEquals("China", city.getCountry());
        assertEquals("Shanghai", city.getDistrict());
        assertEquals(24183300, city.getPopulation());
    }

    @Test
    void testToStringContainsAllFields() {
        String result = city.toString();
        assertTrue(result.contains("Shanghai"));
        assertTrue(result.contains("China"));
        assertTrue(result.contains("24,183,300")); // formatted with commas
    }

    @Test
    void testDifferentInstances() {
        City tokyo = new City(2, "Tokyo", "Japan", "Tokyo Prefecture", 37400068);
        assertNotEquals(city.getId(), tokyo.getId());
        assertNotEquals(city.getName(), tokyo.getName());
        assertNotEquals(city.getCountry(), tokyo.getCountry());
        assertNotEquals(city.getPopulation(), tokyo.getPopulation());
    }

    @Test
    void testZeroPopulation() {
        City empty = new City(3, "SmallTown", "NowhereLand", "Unknown", 0);
        assertEquals(0, empty.getPopulation());
    }

    @Test
    void testNegativePopulation() {
        City ghost = new City(4, "GhostTown", "Nowhere", "Desert", -500);
        assertEquals(-500, ghost.getPopulation());
    }


    @Test
    void testEmojiInName() {
        City london = new City(7, "London 🇬🇧", "UK", "Greater London", 8982000);
        assertEquals("London 🇬🇧", london.getName());
    }

    @Test
    void testLongName() {
        String longName = "A".repeat(200);
        City longCity = new City(8, longName, "Fictionland", "Imagination", 1000);
        assertEquals(longName, longCity.getName());
    }

    @Test
    void testSQLInjectionLikeName() {
        City injection = new City(9, "'; DROP TABLE city; --", "EvilLand", "Dark District", 1);
        assertEquals("'; DROP TABLE city; --", injection.getName());
    }

    @Test
    void testToStringFormatting() {
        City berlin = new City(10, "Berlin", "Germany", "Berlin", 3769000);
        String formatted = berlin.toString();
        assertTrue(formatted.contains("Berlin"));
        assertTrue(formatted.contains("Germany"));
        assertTrue(formatted.contains("Berlin"));
        assertTrue(formatted.contains("3,769,000"));
    }

    @Test
    void testWhitespaceFields() {
        City blank = new City(11, "   ", "   ", "   ", 500);
        assertEquals("   ", blank.getName());
        assertEquals("   ", blank.getCountry());
        assertEquals("   ", blank.getDistrict());
    }

    @Test
    void testPopulationFormattingWithMillions() {
        City ny = new City(12, "New York", "USA", "New York", 8419600);
        String out = ny.toString();
        assertTrue(out.contains("8,419,600"));
    }
}

