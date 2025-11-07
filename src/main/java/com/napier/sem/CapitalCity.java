package com.napier.sem;

public class CapitalCity {
    private int id;
    private String name;
    private String country;
    private String continent;
    private int population;

    // ✅ Constructor
    public CapitalCity(int id, String name, String country, String continent, int population) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.continent = continent;
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

    public String getContinent() {
        return continent;
    }

    public int getPopulation() {
        return population;
    }

    // ✅ toString() for printing and debugging
    @Override
    public String toString() {
        return String.format("%-20s %-20s %-15s %,d",
                name, country, continent, population);
    }
}

