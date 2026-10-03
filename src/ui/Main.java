package ui;

import service.StudentService;
import service.CompanyService;
import service.PlacementService;
import service.DashboardService;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        StudentService studentService = new StudentService();
        CompanyService companyService = new CompanyService();
        PlacementService placementService = new PlacementService();
        DashboardService dashboardService = new DashboardService();

        while (true) {

            System.out.println("\n===== SMART PLACEMENT MANAGEMENT SYSTEM =====");

            System.out.println("1. View Students");
            System.out.println("2. Add Student");
            System.out.println("3. Delete Student");
            System.out.println("4. Update Student");
            System.out.println("5. Search Student");

            System.out.println("6. View Companies");
            System.out.println("7. Add Company");
            System.out.println("8. Delete Company");
            System.out.println("9. Update Company");
            System.out.println("10. Search Company");

            System.out.println("11. View Placements");
            System.out.println("12. Add Placement");
            System.out.println("13. Delete Placement");
            System.out.println("14. Update Placement Status");
            System.out.println("15. Search Placement by Student");

            System.out.println("16. Dashboard");
            System.out.println("17. Exit");

            System.out.print("Enter your choice: ");

            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {

                case 1:
                    System.out.println("\n--- Student Records ---");
                    studentService.showStudents();
                    break;

                case 2:
                    System.out.println("\n--- Add New Student ---");
                    studentService.addStudent(scanner);
                    break;

                case 3:
                    System.out.println("\n--- Delete Student ---");
                    studentService.deleteStudent(scanner);
                    break;

                case 4:
                    System.out.println("\n--- Update Student ---");
                    studentService.updateStudent(scanner);
                    break;

                case 5:
                    System.out.println("\n--- Search Student ---");
                    studentService.searchStudent(scanner);
                    break;

                case 6:
                    System.out.println("\n--- Company Records ---");
                    companyService.showCompanies();
                    break;

                case 7:
                    System.out.println("\n--- Add New Company ---");
                    companyService.addCompany(scanner);
                    break;

                case 8:
                    System.out.println("\n--- Delete Company ---");
                    companyService.deleteCompany(scanner);
                    break;

                case 9:
                    System.out.println("\n--- Update Company ---");
                    companyService.updateCompany(scanner);
                    break;

                case 10:
                    System.out.println("\n--- Search Company ---");
                    companyService.searchCompany(scanner);
                    break;

                case 11:
                    System.out.println("\n--- Placement Records ---");
                    placementService.showPlacements();
                    break;

                case 12:
                    System.out.println("\n--- Add New Placement ---");
                    placementService.addPlacement(scanner);
                    break;

                case 13:
                    System.out.println("\n--- Delete Placement ---");
                    placementService.deletePlacement(scanner);
                    break;

                case 14:
                    System.out.println("\n--- Update Placement Status ---");
                    placementService.updatePlacementStatus(scanner);
                    break;

                case 15:
                    System.out.println(
                        "\n--- Search Placement by Student ---"
                    );
                    placementService.searchPlacementByStudent(scanner);
                    break;

                case 16:
                    System.out.println("\n--- Placement Dashboard ---");
                    dashboardService.showDashboard();
                    break;

                case 17:
                    System.out.println(
                        "Thank you for using Smart Placement Management System."
                    );

                    scanner.close();
                    return;

                default:
                    System.out.println(
                        "Invalid choice. Please try again."
                    );
            }
        }
    }
}