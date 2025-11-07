package com.napier.sem;

public class Country {
    private String code;
    private String name;
    private String continent;
    private String region;
    private int population;
    private int capital;

    // ✅ Constructor
    public Country(String code, String name, String continent, String region, int population, int capital) {
        this.code = code;
        this.name = name;
        this.continent = continent;
        this.region = region;
        this.population = population;
        this.capital = capital;
    }

    // Getters
    public String getCode() { return code; }
    public String getName() { return name; }
    public String getContinent() { return continent; }
    public String getRegion() { return region; }
    public int getPopulation() { return population; }
    public int getCapital() { return capital; }

    @Override
    public String toString() {
        return String.format("%s | %s | %s | %s | %d | %d",
                code, name, continent, region, population, capital);
    }


}

