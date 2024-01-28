package com.example.projectemployeeinvoice;

import java.sql.*;

public class DatabaseConnection {
    private String url = "jdbc:mysql://localhost:3306/EmployeeInvoice";
    private String user = "root";
    private String password = "";

    public Connection connect() {
        Connection conn = null;
        try {
            // Lägg till denna rad för att ladda drivrutinen
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the database!");
        } catch (ClassNotFoundException e) {
            System.out.println("Oops! Couldn't find the JDBC driver." + e.getMessage());
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Oops! Something went wrong." + e.getMessage());
            e.printStackTrace();
        }
        return conn;
    }


}

