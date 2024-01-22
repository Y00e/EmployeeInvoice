package com.example.projectemployeeinvoice;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


@WebServlet("/PaymentServlet")
public class PaymentServlet extends HttpServlet {

    private PaymentDAO paymentDAO;

    public void init() {
        DatabaseConnection dbConnection = new DatabaseConnection();
        Connection connection = dbConnection.connect();
        paymentDAO = new PaymentDAO(connection);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Hämta formulärdata i Servlet
        String title = request.getParameter("title");
        String description = request.getParameter("description");

        String category = request.getParameter("category");

        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        Date date = null;
        try {
            date = formatter.parse(request.getParameter("date"));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        double price = Double.parseDouble(request.getParameter("price"));

        int employeeId = Integer.parseInt(request.getParameter("employeeId"));


        // Skapar ett nytt paymentb objekt
        Payment payment = new Payment(title, date, description, category, price, employeeId);


        // Använd metoden addPayment för att lägga till betalningen i databasen
        paymentDAO.addPayment(payment);

        // Omdirigera användare till faktura
        response.sendRedirect("invoice");
    }
}