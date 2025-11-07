package com.napier.sem;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for Population class
 */
class PopulationTest {

    private Population population;

    @BeforeEach
    void setUp() {
        population = new Population();
    }

    @Test
    void testDefaultConstructor() {
        assertNotNull(population);
        assertNull(population.getName());
        assertEquals(0, population.getTotalPopulation());
        assertEquals(0, population.getPopulationInCities());
        assertEquals(0, population.getPopulationNotInCities());
        assertEquals(0.0, population.getPercentageInCities());
        assertEquals(0.0, population.getPercentageNotInCities());
    }

    @Test
    void testParameterizedConstructor() {
        Population testPop = new Population("Europe", 746000000L, 558000000L, 188000000L);

        assertEquals("Europe", testPop.getName());
        assertEquals(746000000L, testPop.getTotalPopulation());
        assertEquals(558000000L, testPop.getPopulationInCities());
        assertEquals(188000000L, testPop.getPopulationNotInCities());
        assertEquals(74.79, testPop.getPercentageInCities(), 0.01);
        assertEquals(25.20, testPop.getPercentageNotInCities(), 0.01);
    }

    @Test
    void testPercentageCalculation() {
        population.setName("Asia");
        population.setTotalPopulation(4600000000L);
        population.setPopulationInCities(2300000000L);
        population.setPopulationNotInCities(2300000000L);

        assertEquals(50.0, population.getPercentageInCities(), 0.01);
        assertEquals(50.0, population.getPercentageNotInCities(), 0.01);
    }

    @Test
    void testZeroTotalPopulation() {
        Population testPop = new Population("Empty Region", 0L, 0L, 0L);

        assertEquals(0.0, testPop.getPercentageInCities());
        assertEquals(0.0, testPop.getPercentageNotInCities());
    }

    @Test
    void testSetName() {
        population.setName("North America");
        assertEquals("North America", population.getName());
    }

    @Test
    void testSetTotalPopulation() {
        population.setTotalPopulation(580000000L);
        assertEquals(580000000L, population.getTotalPopulation());
    }

    @Test
    void testSetPopulationInCities() {
        population.setTotalPopulation(1000000L);
        population.setPopulationInCities(750000L);

        assertEquals(750000L, population.getPopulationInCities());
        assertEquals(75.0, population.getPercentageInCities(), 0.01);
    }

    @Test
    void testSetPopulationNotInCities() {
        population.setTotalPopulation(1000000L);
        population.setPopulationNotInCities(250000L);

        assertEquals(250000L, population.getPopulationNotInCities());
        assertEquals(25.0, population.getPercentageNotInCities(), 0.01);
    }

    @Test
    void testAllPopulationInCities() {
        Population testPop = new Population("City State", 5000000L, 5000000L, 0L);

        assertEquals(100.0, testPop.getPercentageInCities(), 0.01);
        assertEquals(0.0, testPop.getPercentageNotInCities(), 0.01);
    }

    @Test
    void testNoPopulationInCities() {
        Population testPop = new Population("Rural Area", 1000000L, 0L, 1000000L);

        assertEquals(0.0, testPop.getPercentageInCities(), 0.01);
        assertEquals(100.0, testPop.getPercentageNotInCities(), 0.01);
    }

    @Test
    void testToString() {
        population.setName("Africa");
        population.setTotalPopulation(1340000000L);
        population.setPopulationInCities(550000000L);
        population.setPopulationNotInCities(790000000L);

        String result = population.toString();
        assertTrue(result.contains("Africa"));
        assertTrue(result.contains("1340000000"));
        assertTrue(result.contains("550000000"));
        assertTrue(result.contains("790000000"));
    }

    @Test
    void testSetNullName() {
        population.setName(null);
        assertNull(population.getName());
    }

    @Test
    void testSetEmptyStringName() {
        population.setName("");
        assertEquals("", population.getName());
    }

    @Test
    void testLargePopulation() {
        long large = 7800000000L; // World population
        Population testPop = new Population("World", large, 4200000000L, 3600000000L);

        assertEquals(large, testPop.getTotalPopulation());
        assertEquals(53.84, testPop.getPercentageInCities(), 0.01);
        assertEquals(46.15, testPop.getPercentageNotInCities(), 0.01);
    }

    @Test
    void testVerySmallPercentages() {
        Population testPop = new Population("Small City", 10000000L, 1L, 9999999L);

        assertEquals(0.00001, testPop.getPercentageInCities(), 0.00001);
        assertEquals(99.99999, testPop.getPercentageNotInCities(), 0.00001);
    }

    @Test
    void testPercentageRecalculationOnUpdate() {
        population.setTotalPopulation(1000000L);
        population.setPopulationInCities(500000L);
        assertEquals(50.0, population.getPercentageInCities(), 0.01);

        // Update city population
        population.setPopulationInCities(750000L);
        assertEquals(75.0, population.getPercentageInCities(), 0.01);
    }

    @Test
    void testMultipleInstancesIndependent() {
        Population pop1 = new Population("Region1", 1000000L, 600000L, 400000L);
        Population pop2 = new Population("Region2", 2000000L, 1400000L, 600000L);

        assertNotEquals(pop1.getName(), pop2.getName());
        assertNotEquals(pop1.getTotalPopulation(), pop2.getTotalPopulation());
        assertNotEquals(pop1.getPercentageInCities(), pop2.getPercentageInCities());
    }

    // Additional Edge Case Tests

    @Test
    void testNegativeTotalPopulation() {
        population.setTotalPopulation(-1000000L);
        assertEquals(-1000000L, population.getTotalPopulation());
    }

    @Test
    void testNegativeCityPopulation() {
        population.setTotalPopulation(1000000L);
        population.setPopulationInCities(-500000L);
        assertEquals(-500000L, population.getPopulationInCities());
    }

    @Test
    void testCityPopulationExceedsTotalPopulation() {
        Population testPop = new Population("Test", 1000000L, 1500000L, -500000L);
        assertEquals(1500000L, testPop.getPopulationInCities());
        assertEquals(150.0, testPop.getPercentageInCities(), 0.01);
    }

    @Test
    void testPopulationSumDoesNotMatchTotal() {
        // Cities: 600k, Not in cities: 300k, but Total is 1M
        Population testPop = new Population("Test", 1000000L, 600000L, 300000L);

        assertEquals(1000000L, testPop.getTotalPopulation());
        assertEquals(600000L, testPop.getPopulationInCities());
        assertEquals(300000L, testPop.getPopulationNotInCities());
        // Percentages calculated from total, not from sum
        assertEquals(60.0, testPop.getPercentageInCities(), 0.01);
        assertEquals(30.0, testPop.getPercentageNotInCities(), 0.01);
    }

    @Test
    void testMaximumLongValues() {
        Population testPop = new Population("World", Long.MAX_VALUE,
                Long.MAX_VALUE / 2, Long.MAX_VALUE / 2);
        assertEquals(Long.MAX_VALUE, testPop.getTotalPopulation());
    }

    @Test
    void testMinimumLongValues() {
        population.setTotalPopulation(Long.MIN_VALUE);
        assertEquals(Long.MIN_VALUE, population.getTotalPopulation());
    }

    @Test
    void testWhitespaceOnlyName() {
        population.setName("   ");
        assertEquals("   ", population.getName());
    }

    @Test
    void testUnicodeCharactersInName() {
        population.setName("亚洲"); // Asia in Chinese
        assertEquals("亚洲", population.getName());
    }

    @Test
    void testArabicCharactersInName() {
        population.setName("أفريقيا"); // Africa in Arabic
        assertEquals("أفريقيا", population.getName());
    }

    @Test
    void testEmojisInName() {
        population.setName("Europe 🇪🇺");
        assertEquals("Europe 🇪🇺", population.getName());
    }

    @Test
    void testVeryLongRegionName() {
        String longName = "A".repeat(500);
        population.setName(longName);
        assertEquals(longName, population.getName());
    }

    @Test
    void testLeadingAndTrailingSpaces() {
        population.setName("  South America  ");
        assertEquals("  South America  ", population.getName());
    }

    @Test
    void testNameWithNewlines() {
        population.setName("North\nAmerica");
        assertEquals("North\nAmerica", population.getName());
    }

    @Test
    void testNameWithTabs() {
        population.setName("South\tAmerica");
        assertEquals("South\tAmerica", population.getName());
    }

    @Test
    void testSQLInjectionInName() {
        population.setName("'; DROP TABLE population; --");
        assertEquals("'; DROP TABLE population; --", population.getName());
    }

    @Test
    void testPercentageWithOnePersonTotal() {
        Population testPop = new Population("Tiny", 1L, 1L, 0L);
        assertEquals(100.0, testPop.getPercentageInCities(), 0.01);
        assertEquals(0.0, testPop.getPercentageNotInCities(), 0.01);
    }

    @Test
    void testPercentageWithOnePersonInCity() {
        Population testPop = new Population("Mostly Rural", 1000000L, 1L, 999999L);
        assertEquals(0.0001, testPop.getPercentageInCities(), 0.0001);
        assertEquals(99.9999, testPop.getPercentageNotInCities(), 0.0001);
    }

    @Test
    void testAllNegativeValues() {
        Population testPop = new Population("Negative", -1000000L, -600000L, -400000L);
        assertEquals(-1000000L, testPop.getTotalPopulation());
        assertEquals(-600000L, testPop.getPopulationInCities());
        assertEquals(-400000L, testPop.getPopulationNotInCities());
    }

    @Test
    void testZeroTotalWithNonZeroCityPopulation() {
        population.setTotalPopulation(0L);
        population.setPopulationInCities(100000L);

        assertEquals(0L, population.getTotalPopulation());
        assertEquals(100000L, population.getPopulationInCities());
        // Should not throw division by zero error
        assertEquals(0.0, population.getPercentageInCities());
    }

    @Test
    void testUpdateTotalPopulationToZero() {
        population.setTotalPopulation(1000000L);
        population.setPopulationInCities(500000L);

        // Change total to zero
        population.setTotalPopulation(0L);

        // Percentages should handle zero division
        assertEquals(0.0, population.getPercentageInCities());
    }

    @Test
    void testPopulationOne() {
        Population testPop = new Population("Single", 1L, 0L, 1L);
        assertEquals(1L, testPop.getTotalPopulation());
        assertEquals(0.0, testPop.getPercentageInCities(), 0.01);
        assertEquals(100.0, testPop.getPercentageNotInCities(), 0.01);
    }

    @Test
    void testAllFieldsNullOrZero() {
        Population testPop = new Population(null, 0L, 0L, 0L);
        assertNull(testPop.getName());
        assertEquals(0L, testPop.getTotalPopulation());
        assertEquals(0.0, testPop.getPercentageInCities());
        assertEquals(0.0, testPop.getPercentageNotInCities());
    }
}
