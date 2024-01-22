package com.example.projectemployeeinvoice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.*;
import java.sql.Date;
import java.util.*;
public class PaymentDAO {

    private Connection connection;

    public PaymentDAO() {
        DatabaseConnection dbConnection = new DatabaseConnection();
            this.connection = dbConnection.connect();
    }

    // Constructor with Connection parameter
    public PaymentDAO(Connection connection) {
        this.connection = connection;
    }


    public void addPayment(Payment payment) {
        String sql = "INSERT INTO Payments(title, date, description, category, price, employee_id) VALUES(?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, payment.getTitle());
            statement.setDate(2, new java.sql.Date(payment.getDate().getTime())); // Convert java.util.Date to java.sql.Date
            statement.setString(3, payment.getDescription());
            statement.setString(4, payment.getCategory());
            statement.setDouble(5, payment.getPrice());
            statement.setInt(6, payment.getEmployeeId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Payment getPayment(int id) {
        String sql = "SELECT * FROM Payments WHERE id = ?";
        Payment payment = null;

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                payment = new Payment();
                payment.setId(rs.getInt("id"));
                payment.setTitle(rs.getString("title"));
                payment.setDate(rs.getDate("date"));
                payment.setDescription(rs.getString("description"));
                payment.setCategory(rs.getString("category"));
                payment.setPrice(rs.getDouble("price"));
                payment.setEmployeeId(rs.getInt("employee_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return payment;
    }


    public List<Payment> getPaymentsByEmployeeId(int employeeId) {
        String sql = "SELECT * FROM Payments WHERE employee_id = ?";
        List<Payment> payments = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, employeeId);
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    Payment payment = new Payment();
                    payment.setId(rs.getInt("id"));
                    payment.setTitle(rs.getString("title"));
                    payment.setDate(rs.getDate("date"));
                    payment.setDescription(rs.getString("description"));
                    payment.setCategory(rs.getString("category"));
                    payment.setPrice(rs.getDouble("price"));
                    payment.setEmployeeId(rs.getInt("employee_id"));
                    payments.add(payment);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return payments;
    }

    public void updatePayment(Payment payment) {
        String sql = "UPDATE Payments SET title=?, date=?, description=?, category=?, price=?, employee_id=? WHERE id=?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, payment.getTitle());
            statement.setDate(2, new java.sql.Date(payment.getDate().getTime())); // Convert java.util.Date to java.sql.Date
            statement.setString(3, payment.getDescription());
            statement.setString(4, payment.getCategory());
            statement.setDouble(5, payment.getPrice());
            statement.setInt(6, payment.getEmployeeId());
            statement.setInt(7, payment.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletePayment(int id) {
        String sql = "DELETE FROM Payments WHERE id=?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



}
