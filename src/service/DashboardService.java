package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DashboardService {

    public void showDashboard() {

        try (
            Connection connection = DatabaseConnection.getConnection()
        ) {

            // Total Students
            String studentSql =
                    "SELECT COUNT(*) FROM students";

            try (
                PreparedStatement statement =
                        connection.prepareStatement(studentSql);
                ResultSet result = statement.executeQuery()
            ) {
                if (result.next()) {
                    System.out.println(
                        "Total Students: " +
                        result.getInt(1)
                    );
                }
            }

            // Total Companies
            String companySql =
                    "SELECT COUNT(*) FROM companies";

            try (
                PreparedStatement statement =
                        connection.prepareStatement(companySql);
                ResultSet result = statement.executeQuery()
            ) {
                if (result.next()) {
                    System.out.println(
                        "Total Companies: " +
                        result.getInt(1)
                    );
                }
            }

            // Total Applications
            String applicationSql =
                    "SELECT COUNT(*) FROM placements";

            try (
                PreparedStatement statement =
                        connection.prepareStatement(applicationSql);
                ResultSet result = statement.executeQuery()
            ) {
                if (result.next()) {
                    System.out.println(
                        "Total Applications: " +
                        result.getInt(1)
                    );
                }
            }

            // Selected Students
            String selectedSql =
                    "SELECT COUNT(*) FROM placements " +
                    "WHERE status = 'Selected'";

            try (
                PreparedStatement statement =
                        connection.prepareStatement(selectedSql);
                ResultSet result = statement.executeQuery()
            ) {
                if (result.next()) {
                    System.out.println(
                        "Selected Students: " +
                        result.getInt(1)
                    );
                }
            }

            // Applied Students
            String appliedSql =
                    "SELECT COUNT(*) FROM placements " +
                    "WHERE status = 'Applied'";

            try (
                PreparedStatement statement =
                        connection.prepareStatement(appliedSql);
                ResultSet result = statement.executeQuery()
            ) {
                if (result.next()) {
                    System.out.println(
                        "Applied Students: " +
                        result.getInt(1)
                    );
                }
            }

        } catch (Exception e) {

            System.out.println(
                "Failed to load dashboard."
            );

            e.printStackTrace();
        }
    }
}