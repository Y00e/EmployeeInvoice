package com.example.projectemployeeinvoice;

import java.sql.*;

public class DatabaseConnection {

    
    private Connection conn;

    public DatabaseConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/EmployeeInvoice", "root", "");
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }


}

