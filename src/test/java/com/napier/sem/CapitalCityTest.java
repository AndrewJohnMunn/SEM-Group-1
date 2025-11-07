package com.napier.sem;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for CapitalCity class.
 */
class CapitalCityTest {

    private CapitalCity capital;

    @BeforeEach
    void setUp() {
        // Using your defined constructor
        capital = new CapitalCity(1, "Tokyo", "Japan", "Asia", 37400068);
    }

    @Test
    void testConstructorAndGetters() {
        assertEquals(1, capital.getId());
        assertEquals("Tokyo", capital.getName());
        assertEquals("Japan", capital.getCountry());
        assertEquals("Asia", capital.getContinent());
        assertEquals(37400068, capital.getPopulation());
    }

    @Test
    void testToStringContainsAllFields() {
        String result = capital.toString();
        assertTrue(result.contains("Tokyo"));
        assertTrue(result.contains("Japan"));
        assertTrue(result.contains("Asia"));
        assertTrue(result.contains("37,400,068")); // formatted with commas
    }

    @Test
    void testDifferentInstanceValues() {
        CapitalCity other = new CapitalCity(2, "Paris", "France", "Europe", 2148327);

        assertNotEquals(capital.getId(), other.getId());
        assertNotEquals(capital.getName(), other.getName());
        assertNotEquals(capital.getCountry(), other.getCountry());
        assertNotEquals(capital.getPopulation(), other.getPopulation());
    }

    @Test
    void testZeroPopulation() {
        CapitalCity empty = new CapitalCity(3, "SmallTown", "NowhereLand", "Antarctica", 0);
        assertEquals(0, empty.getPopulation());
    }

    @Test
    void testNegativePopulation() {
        CapitalCity weird = new CapitalCity(4, "GhostTown", "Nowhere", "Unknown", -1000);
        assertEquals(-1000, weird.getPopulation());
    }

    @Test
    void testUnicodeCharactersInName() {
        CapitalCity beijing = new CapitalCity(5, "北京市", "中国", "Asia", 21540000);
        assertEquals("北京市", beijing.getName());
        assertEquals("中国", beijing.getCountry());
    }

    @Test
    void testArabicCharactersInName() {
        CapitalCity cairo = new CapitalCity(6, "القاهرة", "مصر", "Africa", 9500000);
        assertEquals("القاهرة", cairo.getName());
    }

    @Test
    void testEmojiInName() {
        CapitalCity london = new CapitalCity(7, "London 🇬🇧", "UK", "Europe", 8982000);
        assertEquals("London 🇬🇧", london.getName());
    }

    @Test
    void testLongName() {
        String longName = "A".repeat(300);
        CapitalCity longCity = new CapitalCity(8, longName, "Fictionland", "Unknown", 1000);
        assertEquals(longName, longCity.getName());
    }

    @Test
    void testSQLInjectionLikeName() {
        CapitalCity injection = new CapitalCity(9, "'; DROP TABLE capitalcity; --", "EvilLand", "Unknown", 1);
        assertEquals("'; DROP TABLE capitalcity; --", injection.getName());
    }

    @Test
    void testToStringFormatting() {
        CapitalCity berlin = new CapitalCity(10, "Berlin", "Germany", "Europe", 3769000);
        String formatted = berlin.toString();

        // It should align roughly using your format specifiers
        assertTrue(formatted.contains("Berlin"));
        assertTrue(formatted.contains("Germany"));
        assertTrue(formatted.contains("Europe"));
        assertTrue(formatted.contains("3,769,000"));
    }

    @Test
    void testWhitespaceFields() {
        CapitalCity city = new CapitalCity(11, "   ", "   ", "   ", 500);
        assertEquals("   ", city.getName());
        assertEquals("   ", city.getCountry());
        assertEquals("   ", city.getContinent());
    }

    @Test
    void testPopulationFormattingWithMillions() {
        CapitalCity ny = new CapitalCity(12, "New York", "USA", "North America", 8419600);
        String out = ny.toString();
        assertTrue(out.contains("8,419,600"));
    }
}




