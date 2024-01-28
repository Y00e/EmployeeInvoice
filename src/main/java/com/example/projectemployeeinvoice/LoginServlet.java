package com.example.projectemployeeinvoice;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;


@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private EmployeeDAO employeeDAO;

    public void init() {
        DatabaseConnection dbConnection = new DatabaseConnection();
        Connection connection = dbConnection.connect();
        employeeDAO = new EmployeeDAO(connection);

        try {
            Employee employee1 = new Employee("username1", "password1");
            Employee employee2 = new Employee("username2", "password2");
            Employee employee3 = new Employee("username3", "password3");
            Employee employee4 = new Employee("username4", "password4");

            //employeeDAO.addEmployee(employee1);
            //employeeDAO.addEmployee(employee2);
            //employeeDAO.addEmployee(employee3);
            //employeeDAO.addEmployee(employee4);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try {
            Employee employee = employeeDAO.getEmployeeByUsername(username);
            if (employee != null && employee.getPassword().equals(password)) {
                // Användaren är inloggad
                HttpSession session = request.getSession();
                session.setAttribute("name", employee);
                response.sendRedirect(request.getContextPath() + "/InvoiceServlet");  // Antag att invoice.jsp är din Invoice page
            } else {
                // Ogiltigt användarnamn eller lösenord
                request.setAttribute("errorMessage", "Invalid username or password");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            // Visa ett felmeddelande till användaren
            response.getWriter().println("Oops! Something went wrong. Please try again later.");
            e.printStackTrace();
        }
    }
}
