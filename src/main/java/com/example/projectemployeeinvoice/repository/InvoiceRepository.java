package com.example.projectemployeeinvoice.repository;

import com.example.projectemployeeinvoice.model.EmployeeEntry;
import com.example.projectemployeeinvoice.PaymentEntry;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InvoiceRepository {

    private Connection conn;

    // Connection to the database
    public InvoiceRepository() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/webapp", "root", "");
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // creating table employees and register usernamne 'bob' and password '123'
    public void registerEmployee() {

        String sql = "CREATE TABLE IF NOT EXISTS employees (id INT(2) AUTO_INCREMENT PRIMARY KEY, name varchar(255), password varchar(255))";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        sql = "TRUNCATE TABLE employees";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        sql = "INSERT INTO employees (name, password) VALUES ('bob', '123')";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Get all from table payments
    public List<PaymentEntry> getAll() {
        List<PaymentEntry> entries = new ArrayList<>();
        String sql = "SELECT * FROM payments";

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                PaymentEntry entry = new PaymentEntry();

                entry.setId(rs.getInt("id"));
                entry.setTitle(rs.getString("title"));
                entry.setDate(rs.getString("date"));
                entry.setDescription(rs.getString("description"));
                entry.setCategory(rs.getString("category"));
                entry.setPrice(rs.getDouble("price"));
                entry.setEmployeeId(rs.getInt("employeeId"));

                entries.add(entry);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return entries;
    }



    // get payments by id
    public PaymentEntry get(int id) {
        PaymentEntry entry = null;
        String sql = "SELECT * FROM payments WHERE id = ?";

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                entry = new PaymentEntry();
                entry.setId(rs.getInt("id"));
                entry.setTitle(rs.getString("title"));
                entry.setDate(rs.getString("date"));
                entry.setDescription(rs.getString("description"));
                entry.setCategory(rs.getString("category"));
                entry.setPrice(rs.getDouble("price"));
                entry.setEmployeeId(rs.getInt("employee_id"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return entry;
    }

    // Creating table payments
    public void createTable() {

        String sql = "CREATE TABLE IF NOT EXISTS payments ("
                + "id INT AUTO_INCREMENT PRIMARY KEY, "
                + "title VARCHAR(64), "
                + "date VARCHAR(64), "
                + "description VARCHAR(255), "
                + "category VARCHAR(255), "
                + "price DOUBLE, "
                + "employeeId INT (64))";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }


    // Creating payment
    public void create(String title, String date, String description, String category, double price, int employeeId) {
        String sql = "INSERT INTO payments (title, date, description, category, price, employeeId) VALUES (?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, title);
            pstmt.setString(2, date);
            pstmt.setString(3, description);
            pstmt.setString(4, category);
            pstmt.setDouble(5, price);
            pstmt.setInt(6, employeeId);

            pstmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    // Delete payment
    public boolean delete(int id) {
        String sql = "DELETE FROM payments WHERE id=?";

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);

            pstmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    // Update payment
    public PaymentEntry update(PaymentEntry entry) {
        String sql = "UPDATE payments SET title=?, date=?, description=?, category=?, price=?, employee_id=? WHERE id=?";

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, entry.getTitle());
            pstmt.setString(2, entry.getDate());
            pstmt.setString(3, entry.getDescription());
            pstmt.setString(4, entry.getCategory());
            pstmt.setDouble(5, entry.getPrice());
            pstmt.setInt(6, entry.getEmployeeId());
            pstmt.setInt(7, entry.getId());

            pstmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return entry;
    }


    // Gets user by username
    public EmployeeEntry getUserByUsername(String username) {
        EmployeeEntry user = null;
        String sql = "SELECT name, password FROM employees WHERE name = ?";

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                user = new EmployeeEntry();
                user.setUsername(rs.getString("name"));
                user.setPassword(rs.getString("password"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;


    }
}
