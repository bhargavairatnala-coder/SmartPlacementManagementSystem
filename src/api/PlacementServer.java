package api;

import com.sun.net.httpserver.HttpServer;

import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import service.DatabaseConnection;

public class PlacementServer {

    public static void main(String[] args) {

        try {

            HttpServer server =
                    HttpServer.create(
                        new InetSocketAddress(8081),
                        0
                    );

            // =========================
            // STUDENTS API
            // =========================

            server.createContext(
                "/students",
                exchange -> {

                    StringBuilder response =
                            new StringBuilder();

                    String sql =
                            "SELECT student_id, name, email, " +
                            "phone, branch, year " +
                            "FROM students";

                    try (
                        Connection connection =
                                DatabaseConnection.getConnection();

                        PreparedStatement statement =
                                connection.prepareStatement(sql);

                        ResultSet result =
                                statement.executeQuery()
                    ) {

                        response.append("[");

                        boolean first = true;

                        while (result.next()) {

                            if (!first) {
                                response.append(",");
                            }

                            response.append("{");

                            response.append("\"studentId\":");
                            response.append(
                                result.getInt("student_id")
                            );

                            response.append(",");

                            response.append("\"name\":\"");
                            response.append(
                                result.getString("name")
                            );
                            response.append("\"");

                            response.append(",");

                            response.append("\"email\":\"");
                            response.append(
                                result.getString("email")
                            );
                            response.append("\"");

                            response.append(",");

                            response.append("\"phone\":\"");
                            response.append(
                                result.getString("phone")
                            );
                            response.append("\"");

                            response.append(",");

                            response.append("\"branch\":\"");
                            response.append(
                                result.getString("branch")
                            );
                            response.append("\"");

                            response.append(",");

                            response.append("\"year\":");
                            response.append(
                                result.getInt("year")
                            );

                            response.append("}");

                            first = false;
                        }

                        response.append("]");

                    } catch (Exception e) {

                        response =
                            new StringBuilder(
                                "{\"error\":\"Database error\"}"
                            );

                        e.printStackTrace();
                    }

                    sendResponse(exchange, response.toString());
                }
            );


            // =========================
            // COMPANIES API
            // =========================

            server.createContext(
                "/companies",
                exchange -> {

                    StringBuilder response =
                            new StringBuilder();

                    String sql =
                            "SELECT company_id, company_name, " +
                            "job_role, location, package_lpa " +
                            "FROM companies";

                    try (
                        Connection connection =
                                DatabaseConnection.getConnection();

                        PreparedStatement statement =
                                connection.prepareStatement(sql);

                        ResultSet result =
                                statement.executeQuery()
                    ) {

                        response.append("[");

                        boolean first = true;

                        while (result.next()) {

                            if (!first) {
                                response.append(",");
                            }

                            response.append("{");

                            response.append("\"companyId\":");
                            response.append(
                                result.getInt("company_id")
                            );

                            response.append(",");

                            response.append("\"companyName\":\"");
                            response.append(
                                result.getString("company_name")
                            );
                            response.append("\"");

                            response.append(",");

                            response.append("\"jobRole\":\"");
                            response.append(
                                result.getString("job_role")
                            );
                            response.append("\"");

                            response.append(",");

                            response.append("\"location\":\"");
                            response.append(
                                result.getString("location")
                            );
                            response.append("\"");

                            response.append(",");

                            response.append("\"packageLpa\":");
                            response.append(
                                result.getDouble("package_lpa")
                            );

                            response.append("}");

                            first = false;
                        }

                        response.append("]");

                    } catch (Exception e) {

                        response =
                            new StringBuilder(
                                "{\"error\":\"Database error\"}"
                            );

                        e.printStackTrace();
                    }

                    sendResponse(exchange, response.toString());
                }
            );


            // =========================
            // PLACEMENTS API
            // =========================

            server.createContext(
                "/placements",
                exchange -> {

                    StringBuilder response =
                            new StringBuilder();

                    String sql =
                            "SELECT p.placement_id, " +
                            "s.name AS student_name, " +
                            "c.company_name, " +
                            "c.job_role, " +
                            "p.status " +
                            "FROM placements p " +
                            "JOIN students s " +
                            "ON p.student_id = s.student_id " +
                            "JOIN companies c " +
                            "ON p.company_id = c.company_id";

                    try (
                        Connection connection =
                                DatabaseConnection.getConnection();

                        PreparedStatement statement =
                                connection.prepareStatement(sql);

                        ResultSet result =
                                statement.executeQuery()
                    ) {

                        response.append("[");

                        boolean first = true;

                        while (result.next()) {

                            if (!first) {
                                response.append(",");
                            }

                            response.append("{");

                            response.append("\"placementId\":");
                            response.append(
                                result.getInt("placement_id")
                            );

                            response.append(",");

                            response.append("\"studentName\":\"");
                            response.append(
                                result.getString("student_name")
                            );
                            response.append("\"");

                            response.append(",");

                            response.append("\"companyName\":\"");
                            response.append(
                                result.getString("company_name")
                            );
                            response.append("\"");

                            response.append(",");

                            response.append("\"jobRole\":\"");
                            response.append(
                                result.getString("job_role")
                            );
                            response.append("\"");

                            response.append(",");

                            response.append("\"status\":\"");
                            response.append(
                                result.getString("status")
                            );
                            response.append("\"");

                            response.append("}");

                            first = false;
                        }

                        response.append("]");

                    } catch (Exception e) {

                        response =
                            new StringBuilder(
                                "{\"error\":\"Database error\"}"
                            );

                        e.printStackTrace();
                    }

                    sendResponse(exchange, response.toString());
                }
            );


            // =========================
            // START SERVER
            // =========================

            server.start();
            // =========================
// DASHBOARD API
// =========================

server.createContext(
    "/dashboard",
    exchange -> {

        StringBuilder response =
                new StringBuilder();

        String sql =
                "SELECT " +
                "(SELECT COUNT(*) FROM students) AS students, " +
                "(SELECT COUNT(*) FROM companies) AS companies, " +
                "(SELECT COUNT(*) FROM placements) AS applications, " +
                "(SELECT COUNT(*) FROM placements " +
                "WHERE status = 'Selected') AS selected";

        try (
            Connection connection =
                    DatabaseConnection.getConnection();

            PreparedStatement statement =
                    connection.prepareStatement(sql);

            ResultSet result =
                    statement.executeQuery()
        ) {

            if (result.next()) {

                response.append("{");

                response.append("\"students\":");
                response.append(
                    result.getInt("students")
                );

                response.append(",");

                response.append("\"companies\":");
                response.append(
                    result.getInt("companies")
                );

                response.append(",");

                response.append("\"applications\":");
                response.append(
                    result.getInt("applications")
                );

                response.append(",");

                response.append("\"selected\":");
                response.append(
                    result.getInt("selected")
                );

                response.append("}");
            }

        } catch (Exception e) {

            response =
                new StringBuilder(
                    "{\"error\":\"Database error\"}"
                );

            e.printStackTrace();
        }

        sendResponse(exchange, response.toString());
    }
);

            System.out.println(
                "Placement API Server started!"
            );

            System.out.println(
                "Students: http://localhost:8081/students"
            );

            System.out.println(
                "Companies: http://localhost:8081/companies"
            );

            System.out.println(
                "Placements: http://localhost:8081/placements"
            );

        } catch (Exception e) {

            e.printStackTrace();
        }
    }


    // =========================
    // SEND JSON RESPONSE
    // =========================

    private static void sendResponse(
            com.sun.net.httpserver.HttpExchange exchange,
            String response
    ) throws java.io.IOException {

        exchange.getResponseHeaders().set(
            "Content-Type",
            "application/json"
        );

        byte[] responseBytes =
                response.getBytes();

        exchange.sendResponseHeaders(
            200,
            responseBytes.length
        );

        try (
            OutputStream output =
                    exchange.getResponseBody()
        ) {

            output.write(responseBytes);
        }
    }
}