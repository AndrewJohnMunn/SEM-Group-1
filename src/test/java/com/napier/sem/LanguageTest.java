package com.napier.sem;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for Language class.
 */
class LanguageTest {

    private Language language;

    @BeforeEach
    void setUp() {
        language = new Language("CHN", "Chinese", true, 92.0);
        language.setSpeakers(1200000000L);
        language.setWorldPercentage(15.9);
    }

    @Test
    void testConstructorAndGetters() {
        assertEquals("CHN", language.getCountryCode());
        assertEquals("Chinese", language.getName());
        assertTrue(language.isOfficial());
        assertEquals(92.0, language.getPercentage(), 0.01);
        assertEquals(1200000000L, language.getSpeakers());
        assertEquals(15.9, language.getWorldPercentage(), 0.01);
    }

    @Test
    void testSetters() {
        language.setCountryCode("IND");
        language.setName("Hindi");
        language.setOfficial(false);
        language.setPercentage(43.0);
        language.setSpeakers(650000000L);
        language.setWorldPercentage(8.4);

        assertEquals("IND", language.getCountryCode());
        assertEquals("Hindi", language.getName());
        assertFalse(language.isOfficial());
        assertEquals(43.0, language.getPercentage(), 0.01);
        assertEquals(650000000L, language.getSpeakers());
        assertEquals(8.4, language.getWorldPercentage(), 0.01);
    }

    @Test
    void testToStringIncludesAllFields() {
        String result = language.toString();
        assertTrue(result.contains("Chinese"));
        assertTrue(result.contains("CHN"));
        assertTrue(result.contains("Official"));
        assertTrue(result.contains("92.00"));
        assertTrue(result.contains("1,200,000,000") || result.contains("1200000000"));
    }

    @Test
    void testNonOfficialLanguage() {
        Language spanish = new Language("USA", "Spanish", false, 13.5);
        assertFalse(spanish.isOfficial());
        assertEquals("Spanish", spanish.getName());
    }

    @Test
    void testZeroPercentage() {
        language.setPercentage(0.0);
        assertEquals(0.0, language.getPercentage());
    }

    @Test
    void testNegativeSpeakers() {
        language.setSpeakers(-1000L);
        assertEquals(-1000L, language.getSpeakers());
    }

    @Test
    void testUnicodeLanguageName() {
        language.setName("العربية"); // Arabic
        assertEquals("العربية", language.getName());
    }

    @Test
    void testEmptyCountryCode() {
        language.setCountryCode("");
        assertEquals("", language.getCountryCode());
    }

    @Test
    void testWorldPercentageFormatting() {
        language.setWorldPercentage(15.923);
        assertEquals(15.923, language.getWorldPercentage(), 0.001);
    }
}
