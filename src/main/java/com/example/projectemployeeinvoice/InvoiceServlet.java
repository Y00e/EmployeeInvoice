package com.example.projectemployeeinvoice;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/InvoiceServlet")
public class InvoiceServlet extends HttpServlet {
    private PaymentDAO paymentDAO;

    public void init() {
        DatabaseConnection dbConnection = new DatabaseConnection();
        Connection connection = dbConnection.connect();
        paymentDAO = new PaymentDAO(connection);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Employee employee = (Employee) session.getAttribute("name");

        if (employee == null) {
            // Användaren är inte inloggad, omdirigera till login-sidan
            response.sendRedirect("login.jsp");
        } else {


            // Användaren är inloggad, fortsätt med att hämta betalningarna
            List<Payment> payments = paymentDAO.getPaymentsByEmployeeId(employee.getId());
            PrintWriter out = response.getWriter();

            response.setContentType("text/html");

           for (Payment payment : payments) {
               out.println(" Title: " + payment.getTitle() + " Date: " + payment.getDate() + " description: " + payment.getDescription() + " category: " + payment.getCategory() + " price: " + payment.getPrice() + " employee_Id: " + payment.getEmployeeId() + "<br>");
           }



            out.println("<a href='payment.jsp'>Go to Payment</a>");

            request.setAttribute("payments", payments);
            request.getRequestDispatcher("/InvoiceServlet").forward(request, response);
        }
    }

}
