package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class PlacementService {

    public void addPlacement(Scanner scanner) {

        try {
            System.out.print("Enter Placement ID: ");
            int placementId = Integer.parseInt(scanner.nextLine());

            if (placementId <= 0) {
                System.out.println("Placement ID must be positive.");
                return;
            }

            System.out.print("Enter Student ID: ");
            int studentId = Integer.parseInt(scanner.nextLine());

            if (studentId <= 0) {
                System.out.println("Student ID must be positive.");
                return;
            }

            System.out.print("Enter Company ID: ");
            int companyId = Integer.parseInt(scanner.nextLine());

            if (companyId <= 0) {
                System.out.println("Company ID must be positive.");
                return;
            }

            System.out.print("Enter Status: ");
            String status = scanner.nextLine();

            if (!isValidStatus(status)) {
                System.out.println(
                    "Invalid status. Use Applied, Selected, or Rejected."
                );
                return;
            }

            String sql =
                    "INSERT INTO placements " +
                    "(placement_id, student_id, company_id, status) " +
                    "VALUES (?, ?, ?, ?)";

            try (
                Connection connection =
                        DatabaseConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql)
            ) {

                statement.setInt(1, placementId);
                statement.setInt(2, studentId);
                statement.setInt(3, companyId);
                statement.setString(4, status);

                statement.executeUpdate();

                System.out.println(
                    "Placement added successfully!"
                );
            }

        } catch (Exception e) {

            System.out.println(
                "Failed to add placement."
            );

            e.printStackTrace();
        }
    }


    private boolean isValidStatus(String status) {

        return status.equalsIgnoreCase("Applied")
                || status.equalsIgnoreCase("Selected")
                || status.equalsIgnoreCase("Rejected");
    }


    public void showPlacements() {

        String sql =
                "SELECT p.placement_id, s.name, c.company_name, " +
                "c.job_role, p.status " +
                "FROM placements p " +
                "JOIN students s ON p.student_id = s.student_id " +
                "JOIN companies c ON p.company_id = c.company_id";

        try (
            Connection connection =
                    DatabaseConnection.getConnection();

            PreparedStatement statement =
                    connection.prepareStatement(sql);

            ResultSet result =
                    statement.executeQuery()
        ) {

            while (result.next()) {

                System.out.println(
                    "Placement ID: " +
                    result.getInt("placement_id")
                );

                System.out.println(
                    "Student Name: " +
                    result.getString("name")
                );

                System.out.println(
                    "Company: " +
                    result.getString("company_name")
                );

                System.out.println(
                    "Job Role: " +
                    result.getString("job_role")
                );

                System.out.println(
                    "Status: " +
                    result.getString("status")
                );

                System.out.println("-------------------------");
            }

        } catch (Exception e) {

            e.printStackTrace();
        }
    }


    public void deletePlacement(Scanner scanner) {

        try {

            System.out.print(
                "Enter Placement ID to delete: "
            );

            int placementId =
                    Integer.parseInt(scanner.nextLine());

            String sql =
                    "DELETE FROM placements " +
                    "WHERE placement_id = ?";

            try (
                Connection connection =
                        DatabaseConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql)
            ) {

                statement.setInt(1, placementId);

                int rows =
                        statement.executeUpdate();

                if (rows > 0) {

                    System.out.println(
                        "Placement deleted successfully!"
                    );

                } else {

                    System.out.println(
                        "Placement ID not found."
                    );
                }
            }

        } catch (Exception e) {

            System.out.println(
                "Failed to delete placement."
            );

            e.printStackTrace();
        }
    }


    public void updatePlacementStatus(Scanner scanner) {

        try {

            System.out.print("Enter Placement ID: ");

            int placementId =
                    Integer.parseInt(scanner.nextLine());

            if (placementId <= 0) {
                System.out.println(
                    "Placement ID must be positive."
                );
                return;
            }

            System.out.print("Enter New Status: ");
            String status = scanner.nextLine();

            if (!isValidStatus(status)) {
                System.out.println(
                    "Invalid status. Use Applied, Selected, or Rejected."
                );
                return;
            }

            String sql =
                    "UPDATE placements SET status = ? " +
                    "WHERE placement_id = ?";

            try (
                Connection connection =
                        DatabaseConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql)
            ) {

                statement.setString(1, status);
                statement.setInt(2, placementId);

                int rows =
                        statement.executeUpdate();

                if (rows > 0) {

                    System.out.println(
                        "Placement status updated successfully!"
                    );

                } else {

                    System.out.println(
                        "Placement ID not found."
                    );
                }
            }

        } catch (Exception e) {

            System.out.println(
                "Failed to update placement status."
            );

            e.printStackTrace();
        }
    }


    public void searchPlacementByStudent(Scanner scanner) {

        try {

            System.out.print("Enter Student ID: ");

            int studentId =
                    Integer.parseInt(scanner.nextLine());

            if (studentId <= 0) {
                System.out.println(
                    "Student ID must be positive."
                );
                return;
            }

            String sql =
                    "SELECT p.placement_id, s.name, " +
                    "c.company_name, c.job_role, " +
                    "c.location, c.package_lpa, p.status " +
                    "FROM placements p " +
                    "JOIN students s " +
                    "ON p.student_id = s.student_id " +
                    "JOIN companies c " +
                    "ON p.company_id = c.company_id " +
                    "WHERE p.student_id = ?";

            try (
                Connection connection =
                        DatabaseConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql)
            ) {

                statement.setInt(1, studentId);

                try (
                    ResultSet result =
                            statement.executeQuery()
                ) {

                    boolean found = false;

                    while (result.next()) {

                        found = true;

                        System.out.println(
                            "\n--- Placement Found ---"
                        );

                        System.out.println(
                            "Placement ID: " +
                            result.getInt("placement_id")
                        );

                        System.out.println(
                            "Student Name: " +
                            result.getString("name")
                        );

                        System.out.println(
                            "Company: " +
                            result.getString("company_name")
                        );

                        System.out.println(
                            "Job Role: " +
                            result.getString("job_role")
                        );

                        System.out.println(
                            "Location: " +
                            result.getString("location")
                        );

                        System.out.println(
                            "Package: " +
                            result.getDouble("package_lpa") +
                            " LPA"
                        );

                        System.out.println(
                            "Status: " +
                            result.getString("status")
                        );

                        System.out.println(
                            "-------------------------"
                        );
                    }

                    if (!found) {

                        System.out.println(
                            "No placement records found for this student."
                        );
                    }
                }
            }

        } catch (Exception e) {

            System.out.println(
                "Failed to search placement."
            );

            e.printStackTrace();
        }
    }
}