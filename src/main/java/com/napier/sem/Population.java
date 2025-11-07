package com.napier.sem;

/**
 * Represents a population record for a region.
 * Stores total, city, and non-city populations and calculates percentages.
 */
public class Population {
    private String name;
    private long totalPopulation;
    private long populationInCities;
    private long populationNotInCities;
    private double percentageInCities;
    private double percentageNotInCities;

    /**
     * Default constructor initializes fields to default values.
     */
    public Population() {
        this.name = null;
        this.totalPopulation = 0L;
        this.populationInCities = 0L;
        this.populationNotInCities = 0L;
        this.percentageInCities = 0.0;
        this.percentageNotInCities = 0.0;
    }

    /**
     * Parameterized constructor.
     * @param name The name of the region.
     * @param totalPopulation The total population.
     * @param populationInCities The population living in cities.
     * @param populationNotInCities The population not living in cities.
     */
    public Population(String name, long totalPopulation, long populationInCities, long populationNotInCities) {
        this.name = name;
        this.totalPopulation = totalPopulation;
        this.populationInCities = populationInCities;
        this.populationNotInCities = populationNotInCities;
        recalculatePercentages();
    }

    /** Recalculates the population percentages based on the total population. */
    private void recalculatePercentages() {
        if (totalPopulation > 0) {
            this.percentageInCities = (double) populationInCities / totalPopulation * 100.0;
            this.percentageNotInCities = (double) populationNotInCities / totalPopulation * 100.0;
        } else {
            this.percentageInCities = 0.0;
            this.percentageNotInCities = 0.0;
        }
    }

    // Getters and Setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getTotalPopulation() {
        return totalPopulation;
    }

    public void setTotalPopulation(long totalPopulation) {
        this.totalPopulation = totalPopulation;
        recalculatePercentages();
    }

    public long getPopulationInCities() {
        return populationInCities;
    }

    public void setPopulationInCities(long populationInCities) {
        this.populationInCities = populationInCities;
        recalculatePercentages();
    }

    public long getPopulationNotInCities() {
        return populationNotInCities;
    }

    public void setPopulationNotInCities(long populationNotInCities) {
        this.populationNotInCities = populationNotInCities;
        recalculatePercentages();
    }

    public double getPercentageInCities() {
        return percentageInCities;
    }

    public double getPercentageNotInCities() {
        return percentageNotInCities;
    }

    @Override
    public String toString() {
        return "Population{" +
                "name='" + name + '\'' +
                ", totalPopulation=" + totalPopulation +
                ", populationInCities=" + populationInCities +
                ", populationNotInCities=" + populationNotInCities +
                ", percentageInCities=" + String.format("%.2f", percentageInCities) +
                ", percentageNotInCities=" + String.format("%.2f", percentageNotInCities) +
                '}';
    }
}
