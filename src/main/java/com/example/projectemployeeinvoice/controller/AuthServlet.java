package com.example.projectemployeeinvoice.controller;


import com.example.projectemployeeinvoice.model.EmployeeEntry;
import com.example.projectemployeeinvoice.repository.InvoiceRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

//url: /auth/login, /auth/logout, /auth/payment
@WebServlet("/auth/*")
public class AuthServlet extends HttpServlet {


    private InvoiceRepository invoiceRepository = new InvoiceRepository();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        invoiceRepository = new InvoiceRepository();
        invoiceRepository.registerEmployee();
        invoiceRepository.createTable();

        EmployeeEntry user = invoiceRepository.getUserByUsername(username);



        if (user != null && user.getPassword().equals(password)) {
            HttpSession session = req.getSession();
            session.setAttribute("username", username);
            resp.sendRedirect("/payment");
        }else {
            req.setAttribute("loginError", "Invalid username or password");
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
        }
    }

    
}
