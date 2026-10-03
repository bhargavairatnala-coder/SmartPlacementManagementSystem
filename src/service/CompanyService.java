package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class CompanyService {

    public void addCompany(Scanner scanner) {

        try {
            System.out.print("Enter Company ID: ");
            int companyId = Integer.parseInt(scanner.nextLine());

            if (companyId <= 0) {
                System.out.println("Company ID must be positive.");
                return;
            }

            System.out.print("Enter Company Name: ");
            String companyName = scanner.nextLine();

            if (companyName.trim().isEmpty()) {
                System.out.println("Company name cannot be empty.");
                return;
            }

            System.out.print("Enter Job Role: ");
            String jobRole = scanner.nextLine();

            if (jobRole.trim().isEmpty()) {
                System.out.println("Job role cannot be empty.");
                return;
            }

            System.out.print("Enter Location: ");
            String location = scanner.nextLine();

            if (location.trim().isEmpty()) {
                System.out.println("Location cannot be empty.");
                return;
            }

            System.out.print("Enter Package (LPA): ");
            double packageLpa =
                    Double.parseDouble(scanner.nextLine());

            if (packageLpa <= 0) {
                System.out.println(
                    "Package must be greater than 0."
                );
                return;
            }

            String sql =
                    "INSERT INTO companies " +
                    "(company_id, company_name, job_role, location, package_lpa) " +
                    "VALUES (?, ?, ?, ?, ?)";

            try (
                Connection connection =
                        DatabaseConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql)
            ) {

                statement.setInt(1, companyId);
                statement.setString(2, companyName);
                statement.setString(3, jobRole);
                statement.setString(4, location);
                statement.setDouble(5, packageLpa);

                statement.executeUpdate();

                System.out.println(
                    "Company added successfully!"
                );
            }

        } catch (Exception e) {

            System.out.println(
                "Failed to add company."
            );

            e.printStackTrace();
        }
    }


    public void showCompanies() {

        String sql = "SELECT * FROM companies";

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
                    "Company ID: " +
                    result.getInt("company_id")
                );

                System.out.println(
                    "Company Name: " +
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

                System.out.println("-------------------------");
            }

        } catch (Exception e) {

            e.printStackTrace();
        }
    }


    public void deleteCompany(Scanner scanner) {

        try {

            System.out.print(
                "Enter Company ID to delete: "
            );

            int companyId =
                    Integer.parseInt(scanner.nextLine());

            String sql =
                    "DELETE FROM companies WHERE company_id = ?";

            try (
                Connection connection =
                        DatabaseConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql)
            ) {

                statement.setInt(1, companyId);

                int rows =
                        statement.executeUpdate();

                if (rows > 0) {

                    System.out.println(
                        "Company deleted successfully!"
                    );

                } else {

                    System.out.println(
                        "Company ID not found."
                    );
                }
            }

        } catch (Exception e) {

            System.out.println(
                "Failed to delete company."
            );

            e.printStackTrace();
        }
    }


    public void updateCompany(Scanner scanner) {

        try {

            System.out.print(
                "Enter Company ID to update: "
            );

            int companyId =
                    Integer.parseInt(scanner.nextLine());

            if (companyId <= 0) {
                System.out.println(
                    "Company ID must be positive."
                );
                return;
            }

            System.out.print("Enter New Company Name: ");
            String companyName = scanner.nextLine();

            if (companyName.trim().isEmpty()) {
                System.out.println(
                    "Company name cannot be empty."
                );
                return;
            }

            System.out.print("Enter New Job Role: ");
            String jobRole = scanner.nextLine();

            if (jobRole.trim().isEmpty()) {
                System.out.println(
                    "Job role cannot be empty."
                );
                return;
            }

            System.out.print("Enter New Location: ");
            String location = scanner.nextLine();

            if (location.trim().isEmpty()) {
                System.out.println(
                    "Location cannot be empty."
                );
                return;
            }

            System.out.print("Enter New Package (LPA): ");

            double packageLpa =
                    Double.parseDouble(scanner.nextLine());

            if (packageLpa <= 0) {
                System.out.println(
                    "Package must be greater than 0."
                );
                return;
            }

            String sql =
                    "UPDATE companies SET " +
                    "company_name = ?, " +
                    "job_role = ?, " +
                    "location = ?, " +
                    "package_lpa = ? " +
                    "WHERE company_id = ?";

            try (
                Connection connection =
                        DatabaseConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql)
            ) {

                statement.setString(1, companyName);
                statement.setString(2, jobRole);
                statement.setString(3, location);
                statement.setDouble(4, packageLpa);
                statement.setInt(5, companyId);

                int rows =
                        statement.executeUpdate();

                if (rows > 0) {

                    System.out.println(
                        "Company updated successfully!"
                    );

                } else {

                    System.out.println(
                        "Company ID not found."
                    );
                }
            }

        } catch (Exception e) {

            System.out.println(
                "Failed to update company."
            );

            e.printStackTrace();
        }
    }


    public void searchCompany(Scanner scanner) {

        try {

            System.out.print(
                "Enter Company ID to search: "
            );

            int companyId =
                    Integer.parseInt(scanner.nextLine());

            String sql =
                    "SELECT * FROM companies " +
                    "WHERE company_id = ?";

            try (
                Connection connection =
                        DatabaseConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql)
            ) {

                statement.setInt(1, companyId);

                try (
                    ResultSet result =
                            statement.executeQuery()
                ) {

                    if (result.next()) {

                        System.out.println(
                            "\n--- Company Found ---"
                        );

                        System.out.println(
                            "Company ID: " +
                            result.getInt("company_id")
                        );

                        System.out.println(
                            "Company Name: " +
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

                    } else {

                        System.out.println(
                            "Company ID not found."
                        );
                    }
                }
            }

        } catch (Exception e) {

            System.out.println(
                "Failed to search company."
            );

            e.printStackTrace();
        }
    }
}