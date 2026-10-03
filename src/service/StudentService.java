package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class StudentService {

    public void addStudent(Scanner scanner) {

        try {
            System.out.print("Enter Student ID: ");
            int studentId = Integer.parseInt(scanner.nextLine());

            if (studentId <= 0) {
                System.out.println("Student ID must be positive.");
                return;
            }

            System.out.print("Enter Name: ");
            String name = scanner.nextLine();

            if (name.trim().isEmpty()) {
                System.out.println("Name cannot be empty.");
                return;
            }

            System.out.print("Enter Email: ");
            String email = scanner.nextLine();

            if (email.trim().isEmpty() || !email.contains("@")) {
                System.out.println("Please enter a valid email.");
                return;
            }

            System.out.print("Enter Phone: ");
            String phone = scanner.nextLine();

            if (!phone.matches("\\d{10}")) {
                System.out.println("Phone number must contain 10 digits.");
                return;
            }

            System.out.print("Enter Branch: ");
            String branch = scanner.nextLine();

            if (branch.trim().isEmpty()) {
                System.out.println("Branch cannot be empty.");
                return;
            }

            System.out.print("Enter Year: ");
            int year = Integer.parseInt(scanner.nextLine());

            if (year < 1 || year > 4) {
                System.out.println("Year must be between 1 and 4.");
                return;
            }

            String sql =
                    "INSERT INTO students " +
                    "(student_id, name, email, phone, branch, year) " +
                    "VALUES (?, ?, ?, ?, ?, ?)";

            try (
                Connection connection =
                        DatabaseConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql)
            ) {

                statement.setInt(1, studentId);
                statement.setString(2, name);
                statement.setString(3, email);
                statement.setString(4, phone);
                statement.setString(5, branch);
                statement.setInt(6, year);

                statement.executeUpdate();

                System.out.println("Student added successfully!");
            }

        } catch (Exception e) {

            System.out.println("Failed to add student.");
            e.printStackTrace();
        }
    }


    public void showStudents() {

        String sql = "SELECT * FROM students";

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
                    "Student ID: " +
                    result.getInt("student_id")
                );

                System.out.println(
                    "Name: " +
                    result.getString("name")
                );

                System.out.println(
                    "Email: " +
                    result.getString("email")
                );

                System.out.println(
                    "Phone: " +
                    result.getString("phone")
                );

                System.out.println(
                    "Branch: " +
                    result.getString("branch")
                );

                System.out.println(
                    "Year: " +
                    result.getInt("year")
                );

                System.out.println("-------------------------");
            }

        } catch (Exception e) {

            e.printStackTrace();
        }
    }


    public void deleteStudent(Scanner scanner) {

        try {

            System.out.print("Enter Student ID to delete: ");

            int studentId =
                    Integer.parseInt(scanner.nextLine());

            String sql =
                    "DELETE FROM students WHERE student_id = ?";

            try (
                Connection connection =
                        DatabaseConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql)
            ) {

                statement.setInt(1, studentId);

                int rows =
                        statement.executeUpdate();

                if (rows > 0) {

                    System.out.println(
                        "Student deleted successfully!"
                    );

                } else {

                    System.out.println(
                        "Student ID not found."
                    );
                }
            }

        } catch (Exception e) {

            System.out.println(
                "Failed to delete student."
            );

            e.printStackTrace();
        }
    }


    public void updateStudent(Scanner scanner) {

        try {

            System.out.print("Enter Student ID to update: ");

            int studentId =
                    Integer.parseInt(scanner.nextLine());

            System.out.print("Enter New Name: ");
            String name = scanner.nextLine();

            if (name.trim().isEmpty()) {
                System.out.println("Name cannot be empty.");
                return;
            }

            System.out.print("Enter New Email: ");
            String email = scanner.nextLine();

            if (email.trim().isEmpty() || !email.contains("@")) {
                System.out.println("Please enter a valid email.");
                return;
            }

            System.out.print("Enter New Phone: ");
            String phone = scanner.nextLine();

            if (!phone.matches("\\d{10}")) {
                System.out.println("Phone number must contain 10 digits.");
                return;
            }

            System.out.print("Enter New Branch: ");
            String branch = scanner.nextLine();

            if (branch.trim().isEmpty()) {
                System.out.println("Branch cannot be empty.");
                return;
            }

            System.out.print("Enter New Year: ");
            int year =
                    Integer.parseInt(scanner.nextLine());

            if (year < 1 || year > 4) {
                System.out.println("Year must be between 1 and 4.");
                return;
            }

            String sql =
                    "UPDATE students SET " +
                    "name = ?, email = ?, phone = ?, " +
                    "branch = ?, year = ? " +
                    "WHERE student_id = ?";

            try (
                Connection connection =
                        DatabaseConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql)
            ) {

                statement.setString(1, name);
                statement.setString(2, email);
                statement.setString(3, phone);
                statement.setString(4, branch);
                statement.setInt(5, year);
                statement.setInt(6, studentId);

                int rows =
                        statement.executeUpdate();

                if (rows > 0) {

                    System.out.println(
                        "Student updated successfully!"
                    );

                } else {

                    System.out.println(
                        "Student ID not found."
                    );
                }
            }

        } catch (Exception e) {

            System.out.println(
                "Failed to update student."
            );

            e.printStackTrace();
        }
    }


    public void searchStudent(Scanner scanner) {

        try {

            System.out.print("Enter Student ID to search: ");

            int studentId =
                    Integer.parseInt(scanner.nextLine());

            String sql =
                    "SELECT * FROM students " +
                    "WHERE student_id = ?";

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

                    if (result.next()) {

                        System.out.println(
                            "\n--- Student Found ---"
                        );

                        System.out.println(
                            "Student ID: " +
                            result.getInt("student_id")
                        );

                        System.out.println(
                            "Name: " +
                            result.getString("name")
                        );

                        System.out.println(
                            "Email: " +
                            result.getString("email")
                        );

                        System.out.println(
                            "Phone: " +
                            result.getString("phone")
                        );

                        System.out.println(
                            "Branch: " +
                            result.getString("branch")
                        );

                        System.out.println(
                            "Year: " +
                            result.getInt("year")
                        );

                    } else {

                        System.out.println(
                            "Student ID not found."
                        );
                    }
                }
            }

        } catch (Exception e) {

            System.out.println(
                "Failed to search student."
            );

            e.printStackTrace();
        }
    }
}