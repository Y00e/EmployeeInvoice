package com.example.projectemployeeinvoice;

import java.sql.*;

public class EmployeeDAO {
    private Connection connection;

    public EmployeeDAO(Connection connection) {
        this.connection = connection;
    }

    public void addEmployee(Employee employee) throws SQLException {
        String query = "INSERT INTO Employees (username, password) VALUES (?, ?)";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, employee.getUsername());
        statement.setString(2, employee.getPassword());
        statement.executeUpdate();
    }

    public Employee getEmployeeByUsername(String username) throws SQLException {
        String query = "SELECT * FROM Employees WHERE username = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, username);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            return new Employee(
                    resultSet.getInt("id"),
                    resultSet.getString("username"),
                    resultSet.getString("password")
            );
        } else {
            return null;
        }
    }
}