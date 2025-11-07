package com.napier.sem;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the Country class.
 */
class CountryTest {

    private Country country;

    @BeforeEach
    void setUp() {
        country = new Country("CHN", "China", "Asia", "Eastern Asia", 1409517397, 1815);
    }

    @Test
    void testConstructorAndGetters() {
        assertEquals("CHN", country.getCode());
        assertEquals("China", country.getName());
        assertEquals("Asia", country.getContinent());
        assertEquals("Eastern Asia", country.getRegion());
        assertEquals(1409517397, country.getPopulation());
        assertEquals(1815, country.getCapital());
    }

    @Test
    void testToStringContainsAllFields() {
        String result = country.toString();
        assertTrue(result.contains("CHN"));
        assertTrue(result.contains("China"));
        assertTrue(result.contains("Asia"));
        assertTrue(result.contains("Eastern Asia"));
        assertTrue(result.contains("1409517397"));
        assertTrue(result.contains("1815"));
    }

    @Test
    void testDifferentInstances() {
        Country japan = new Country("JPN", "Japan", "Asia", "Eastern Asia", 126476461, 1532);
        assertNotEquals(country.getCode(), japan.getCode());
        assertNotEquals(country.getName(), japan.getName());
        assertNotEquals(country.getPopulation(), japan.getPopulation());
        assertNotEquals(country.getCapital(), japan.getCapital());
    }

    @Test
    void testZeroPopulation() {
        Country tiny = new Country("XXX", "Tinyland", "Antarctica", "Unknown", 0, 0);
        assertEquals(0, tiny.getPopulation());
    }

    @Test
    void testNegativePopulation() {
        Country glitch = new Country("ERR", "Errorland", "Nowhere", "Null Region", -5000, -1);
        assertEquals(-5000, glitch.getPopulation());
        assertEquals(-1, glitch.getCapital());
    }

    @Test
    void testUnicodeCharacters() {
        Country china = new Country("CHN", "中国", "亚洲", "东亚", 1400000000, 1815);
        assertEquals("中国", china.getName());
        assertEquals("亚洲", china.getContinent());
        assertEquals("东亚", china.getRegion());
    }

    @Test
    void testArabicCharacters() {
        Country egypt = new Country("EGY", "مصر", "Africa", "Northern Africa", 102334404, 608);
        assertEquals("مصر", egypt.getName());
        assertEquals("Africa", egypt.getContinent());
    }

    @Test
    void testEmojiInName() {
        Country uk = new Country("GBR", "United Kingdom 🇬🇧", "Europe", "British Isles", 67886011, 456);
        assertEquals("United Kingdom 🇬🇧", uk.getName());
    }

    @Test
    void testLongName() {
        String longName = "A".repeat(300);
        Country fictional = new Country("FIC", longName, "Imagination", "Dreamland", 12345, 999);
        assertEquals(longName, fictional.getName());
    }

    @Test
    void testSQLInjectionLikeName() {
        Country injection = new Country("SQL", "'; DROP TABLE country; --", "Unknown", "DarkWeb", 1, 0);
        assertEquals("'; DROP TABLE country; --", injection.getName());
    }

    @Test
    void testToStringFormatting() {
        Country germany = new Country("DEU", "Germany", "Europe", "Western Europe", 83783942, 3068);
        String output = germany.toString();

        assertTrue(output.contains("DEU"));
        assertTrue(output.contains("Germany"));
        assertTrue(output.contains("Europe"));
        assertTrue(output.contains("Western Europe"));
        assertTrue(output.contains("83783942"));
        assertTrue(output.contains("3068"));
    }

    @Test
    void testWhitespaceFields() {
        Country blank = new Country("   ", "   ", "   ", "   ", 1000, 10);
        assertEquals("   ", blank.getCode());
        assertEquals("   ", blank.getName());
        assertEquals("   ", blank.getContinent());
        assertEquals("   ", blank.getRegion());
    }

    @Test
    void testPopulationUpperBound() {
        Country mega = new Country("POP", "Megaland", "Galaxy", "Milky Way", Integer.MAX_VALUE, 42);
        assertEquals(Integer.MAX_VALUE, mega.getPopulation());
    }

    @Test
    void testPopulationLowerBound() {
        Country under = new Country("NEG", "NegativeLand", "Underworld", "Void", Integer.MIN_VALUE, -5);
        assertEquals(Integer.MIN_VALUE, under.getPopulation());
    }

    @Test
    void testToStringSeparatorFormat() {
        Country testCountry = new Country("TST", "Testland", "Europe", "Central", 500000, 123);
        String result = testCountry.toString();
        // ensure pipe separators exist between fields
        assertTrue(result.contains("|"));
    }

    @Test
    void testNumericCapitalField() {
        Country france = new Country("FRA", "France", "Europe", "Western Europe", 65273511, 2974);
        assertEquals(2974, france.getCapital());
    }

    @Test
    void testEmptyStrings() {
        Country empty = new Country("", "", "", "", 0, 0);
        assertEquals("", empty.getCode());
        assertEquals("", empty.getName());
        assertEquals("", empty.getContinent());
        assertEquals("", empty.getRegion());
    }
}


