package com.example.projectemployeeinvoice.repository;

import com.example.projectemployeeinvoice.model.PaymentEntry;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InvoiceRepository {

    private Connection conn;
    public InvoiceRepository() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/EmployeeInvoice", "root", "");
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<PaymentEntry> getAll() {
        List<PaymentEntry> entries = new ArrayList<>();
        String sql = "SELECT * FROM Payments";

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            while(rs.next()) {
                PaymentEntry entry = new PaymentEntry();

                entry.setId(rs.getInt("id"));
                entry.setTitle(rs.getString("title"));
                entry.setDate(rs.getString("date"));
                entry.setDescription(rs.getString("description"));
                entry.setCategory(rs.getString("category"));
                entry.setPrice(rs.getDouble("price"));
                entry.setEmployeeId(rs.getInt("employee_id"));

                entries.add(entry);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return entries;
    }



    public List<PaymentEntry> getAll(int employee_id) {
        List<PaymentEntry> entries = new ArrayList<>();
        String sql = "SELECT * FROM Payments WHERE employee_id=?";

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, employee_id);
            ResultSet rs = pstmt.executeQuery();

            while(rs.next()) {
                PaymentEntry entry = new PaymentEntry();

                entry.setTitle(rs.getString("title"));
                entry.setDate(rs.getString("date"));
                entry.setDescription(rs.getString("description"));
                entry.setCategory(rs.getString("category"));
                entry.setPrice(rs.getDouble("price"));

                entries.add(entry);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return entries;
    }


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


    public void create(String title, String date, String description, String category, double price, int employee_id) {
        String sql = "INSERT INTO Payments (title, date, description, category, price, employee_id) VALUES (?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, title);
            pstmt.setString(2, date);
            pstmt.setString(3, description);
            pstmt.setString(4, category);
            pstmt.setDouble(5, price);
            pstmt.setInt(6, employee_id);

            pstmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public boolean delete(int id) {
        String sql = "DELETE FROM Payments WHERE id=?";

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);

            pstmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return true;
    }



    public PaymentEntry update(PaymentEntry entry) {
        String sql = "UPDATE Payments SET title=?, date=?, description=?, category=?, price=?, employee_id=? WHERE id=?";

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




}
