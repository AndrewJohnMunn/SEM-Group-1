package com.napier.sem;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Generates reports about countries from the world database.
 */
public class CountryReport
{
    private final Connection con;

    public CountryReport(Connection con)
    {
        this.con = con;
    }

    /**
     * Runs the country report.
     */
    public void runReport()
    {
        try
        {
            System.out.println("\n=== Country Report ===\n");

            // SQL query: list all countries with required fields
            String query =
                    "SELECT country.Code, country.Name, country.Continent, " +
                            "       country.Region, country.Population, city.Name AS Capital " +
                            "FROM country " +
                            "LEFT JOIN city ON country.Capital = city.ID " +
                            "ORDER BY country.Population DESC " +
                            "LIMIT 10;";


            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            // Print header
            System.out.printf("%-5s %-40s %-15s %-25s %-12s %-30s%n",
                    "Code", "Name", "Continent", "Region", "Population", "Capital");
            System.out.println("-----------------------------------------------------------------------------------------------------------------------------------");

            // Process each row
            while (rs.next())
            {
                String code = rs.getString("Code");
                String name = rs.getString("Name");
                String continent = rs.getString("Continent");
                String region = rs.getString("Region");
                long population = rs.getLong("Population");
                String capital = rs.getString("Capital");

                System.out.printf("%-5s %-40s %-15s %-25s %-12d %-30s%n",
                        code, name, continent, region, population, capital);
            }

            rs.close();
            stmt.close();

            System.out.println("\nCountry report complete.\n");
        }
        catch (Exception e)
        {
            System.out.println("[ERROR] Failed to run country report: " + e.getMessage());
        }
    }
}

