package com.napier.sem;

import java.sql.*;
import java.util.ArrayList;

/**
 * Generates and prints reports about world languages.
 */
public class LanguageReport {

    private final Connection con;

    /**
     * Constructor taking a database connection.
     * @param con The database connection.
     */
    public LanguageReport(Connection con) {
        this.con = con;
    }

    /**
     * Retrieves a list of the most spoken world languages (Chinese, English, Hindi, Spanish, Arabic),
     * ordered by total speakers descending.
     * @return A list of Language objects with speaker and world percentage data.
     */
        public ArrayList<Language> getTopWorldLanguages() {
        ArrayList<Language> languages = new ArrayList<>();

        try {
            String sql = """
                SELECT
                    cl.Language AS name,
                    SUM(c.Population * (cl.Percentage / 100)) AS speakers,
                    (SUM(c.Population * (cl.Percentage / 100)) / (SELECT SUM(Population) FROM country)) * 100 AS worldPercentage
                FROM
                    countrylanguage cl
                JOIN
                    country c ON cl.CountryCode = c.Code
                WHERE
                    cl.Language IN ('Chinese', 'English', 'Hindi', 'Spanish', 'Arabic')
                GROUP BY
                    cl.Language
                ORDER BY
                    speakers DESC;
            """;

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Language lang = new Language();
                lang.setName(rs.getString("name"));
                lang.setSpeakers(rs.getLong("speakers"));
                lang.setWorldPercentage(rs.getDouble("worldPercentage"));
                languages.add(lang);
            }

            rs.close();
            stmt.close();

        } catch (SQLException e) {
            System.out.println("[ERROR] Failed to get top world languages: " + e.getMessage());
        }

        return languages;
    }

    /**
     * Prints the report of the most spoken world languages.
     * @param languages The list of languages to print.
     */
    public void printLanguageReport(ArrayList<Language> languages) {
        if (languages == null || languages.isEmpty()) {
            System.out.println("No language data available.");
            return;
        }

        System.out.printf("%-15s %-15s %-15s%n", "Language", "Speakers", "World %");
        System.out.println("-----------------------------------------------------");

        for (Language lang : languages) {
            System.out.printf("%-15s %-15s %-15.2f%n",
                    lang.getName(),
                    String.format("%,d", lang.getSpeakers()),
                    lang.getWorldPercentage());
        }
    }

    /**
     * Runs the language report end-to-end (query + print).
     */
    public void runReport() {
        ArrayList<Language> languages = getTopWorldLanguages();
        printLanguageReport(languages);
    }
}

