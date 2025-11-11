package com.napier.sem;

import java.sql.*;

public class LanguageReport {

    private Connection con;

    public LanguageReport(Connection con) {
        this.con = con;
    }

    public void printTopFiveLanguages() {
        try {
            String query = """
                SELECT
                    cl.Language AS Language,
                    SUM(c.Population * cl.Percentage / 100) AS Speakers,
                    ROUND(SUM(c.Population * cl.Percentage / 100) /
                          (SELECT SUM(Population) FROM country) * 100, 2) AS World_Percentage
                FROM
                    countrylanguage cl
                    JOIN country c ON cl.CountryCode = c.Code
                WHERE
                    cl.Language IN ('Chinese', 'English', 'Hindi', 'Spanish', 'Arabic')
                GROUP BY
                    cl.Language
                ORDER BY
                    Speakers DESC;
                """;

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            System.out.printf("%-10s %-15s %-10s%n", "Language", "Speakers", "% of World");
            System.out.println("-------------------------------------------");

            while (rs.next()) {
                String language = rs.getString("Language");
                long speakers = rs.getLong("Speakers");
                double percentage = rs.getDouble("World_Percentage");
                System.out.printf("%-10s %,15d %10.2f%%%n", language, speakers, percentage);
            }

        } catch (SQLException e) {
            System.out.println("Error retrieving language data: " + e.getMessage());
        }
    }
}

