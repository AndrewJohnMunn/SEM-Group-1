package com.napier.sem;

/**
 * Represents a language spoken in a country and globally.
 */
public class Language {
    private String countryCode;
    private String name;
    private boolean isOfficial;
    private double percentage;
    private long speakers;          // optional field for report aggregation
    private double worldPercentage; // optional field for world summary

    // Constructors
    public Language() {}

    public Language(String countryCode, String name, boolean isOfficial, double percentage) {
        this.countryCode = countryCode;
        this.name = name;
        this.isOfficial = isOfficial;
        this.percentage = percentage;
    }

    // Getters
    public String getCountryCode() { return countryCode; }
    public String getName() { return name; }
    public boolean isOfficial() { return isOfficial; }
    public double getPercentage() { return percentage; }
    public long getSpeakers() { return speakers; }
    public double getWorldPercentage() { return worldPercentage; }

    // Setters
    public void setCountryCode(String countryCode) { this.countryCode = countryCode; }
    public void setName(String name) { this.name = name; }
    public void setOfficial(boolean official) { isOfficial = official; }
    public void setPercentage(double percentage) { this.percentage = percentage; }
    public void setSpeakers(long speakers) { this.speakers = speakers; }
    public void setWorldPercentage(double worldPercentage) { this.worldPercentage = worldPercentage; }

    @Override
    public String toString() {
        return String.format("%-15s %-5s %-10s %10.2f%% (%d speakers, %.2f%% world)",
                name, countryCode, (isOfficial ? "Official" : "Unofficial"),
                percentage, speakers, worldPercentage);
    }
}
