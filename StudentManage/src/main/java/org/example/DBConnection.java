package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    // Database credentials
//    private static final String URL = "jdbc:mysql://localhost:3306/student_management"; // Replace with your DB URL
//    private static final String URL = "jdbc:mysql://localhost:3306/student_management?useSSL=false";
    private static final String URL = "jdbc:mysql://localhost:3306/student_management?useSSL=false&allowPublicKeyRetrieval=true";
    private static final String USER = "root"; // Replace with your MySQL username
    private static final String PASSWORD = "root"; // Replace with your MySQL password

    // Method to get a connection to the database
    public static Connection getConnection() throws SQLException {
        try {
            // Load the MySQL JDBC driver (this is optional for newer versions of Java)
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Return a connection to the database
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            throw new SQLException("MySQL JDBC Driver not found.", e);
        }
    }

    // Optionally, you can add a method to close the connection, although try-with-resources handles it
    public static void closeConnection(Connection connection) {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
