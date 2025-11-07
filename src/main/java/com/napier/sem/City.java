package com.napier.sem;

/**
 * Represents a city entity in the database.
 */
public class City {
    private int id;
    private String name;
    private String country;
    private String district;
    private int population;

    // ✅ Constructor
    public City(int id, String name, String country, String district, int population) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.district = district;
        this.population = population;
    }

    // ✅ Getters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public String getDistrict() {
        return district;
    }

    public int getPopulation() {
        return population;
    }

    // ✅ toString for debugging and formatted reports
    @Override
    public String toString() {
        return String.format("%-25s %-20s %-20s %,d",
                name, country, district, population);
    }
}

