package com.jdbc;

import java.sql.*;

public class JdbcConnectionDemo2 {
    private static final String URL = "jdbc:mysql://localhost:3306/dev";
    private static final String USER = "root";
    private static final String PASSWORD = "password";
    private static final String SQL = "SELECT employeeNumber, lastName, firstName, email, jobTitle FROM employees";

    public static void main(String[] args) throws ClassNotFoundException {
        // Load MySQL driver (optional for modern JDBC, but kept for clarity)



        // Use try-with-resources to automatically close all connections
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(SQL)) {

            Class.forName("com.mysql.cj.jdbc.Driver");

            System.out.println("Connected to database successfully.");
            System.out.println("Employee Records:\n");

            while (rs.next()) {
                int employeeNumber = rs.getInt("employeeNumber");
                String lastName = rs.getString("lastName");
                String firstName = rs.getString("firstName");
                String email = rs.getString("email");
                String jobTitle = rs.getString("jobTitle");

                System.out.printf("%d | %s | %s | %s | %s%n",
                        employeeNumber, lastName, firstName, email, jobTitle);
            }

        } catch (SQLException e) {
            System.err.println("Database error occurred:");
            e.printStackTrace();
        }
    }
}
