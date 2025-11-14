package com.napier.sem;

import java.sql.*;
import java.util.ArrayList;

/**
 * Generates capital city reports from the world database.
 */
public class CapitalCityReport
{
    private final Connection con;

    /**
     * Constructor
     */
    public CapitalCityReport(Connection con)
    {
        this.con = con;
    }

    /**
     * Data object representing one capital city record.
     */
    public static class CapitalCity
    {
        public String name;
        public String country;
        public int population;
    }

    /**
     * Retrieves all capital cities sorted by population descending.
     */
    public ArrayList<CapitalCity> getCapitalCities()
    {
        ArrayList<CapitalCity> list = new ArrayList<>();

        try
        {
            String query =
                    "SELECT city.Name AS CapitalName, country.Name AS CountryName, city.Population " +
                            "FROM city " +
                            "JOIN country ON city.ID = country.Capital " +
                            "ORDER BY city.Population DESC;";

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next())
            {
                CapitalCity cap = new CapitalCity();
                cap.name = rs.getString("CapitalName");
                cap.country = rs.getString("CountryName");
                cap.population = rs.getInt("Population");

                list.add(cap);
            }
        }
        catch (Exception e)
        {
            System.out.println("[ERROR] Cannot fetch capital cities: " + e.getMessage());
        }

        return list;
    }

    /**
     * Prints the report results to the console.
     */
    public void printReport(ArrayList<CapitalCity> cities)
    {
        System.out.printf("%-35s %-35s %-15s%n", "Capital", "Country", "Population");
        System.out.println("--------------------------------------------------------------------------------------");

        for (CapitalCity c : cities)
        {
            System.out.printf("%-35s %-35s %-15d%n",
                    c.name, c.country, c.population);
        }
    }
}

