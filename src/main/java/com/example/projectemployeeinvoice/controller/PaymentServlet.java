package com.example.projectemployeeinvoice.controller;



import com.example.projectemployeeinvoice.model.PaymentEntry;
import com.example.projectemployeeinvoice.service.InvoiceService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

@WebServlet("/payment/*")
public class PaymentServlet extends HttpServlet {

    private InvoiceService invoiceService = new InvoiceService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(true);
        String username = (String) session.getAttribute("username");

        if(username == null) {
            resp.sendRedirect("/login.jsp");
        } else {
            req.getRequestDispatcher("/index.jsp").include(req, resp);

            List<PaymentEntry> entries = invoiceService.getAll();

            PrintWriter out = resp.getWriter();
            for(PaymentEntry entry : entries) {
                out.println("<br>Title:" + entry.getTitle() + "<br>Date:" + entry.getDate() + "<br>Description:" + entry.getDescription()
                        + "<br>Category:" + entry.getCategory() + "<br>Dare:" + entry.getPrice() + "<br>EmployeeId:" + entry.getEmployeeId());
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        switch (req.getPathInfo()) {
            case "/": createEntry(req, resp); break;
            case "/delete": deleteEntry(req, resp); break;
        }
    }

    private void deleteEntry(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(true);
        String username = (String) session.getAttribute("username");

        int id = Integer.parseInt(req.getParameter("id"));

        if(username == null) {
            resp.sendRedirect("/login.jsp");
        } else {
            invoiceService.delete(id);
        }
    }

    private void createEntry(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(true);
        String username = (String) session.getAttribute("username");

        if(username == null) {
            resp.sendRedirect("/login.jsp");
        } else {
            String title = req.getParameter("title");
            String date = req.getParameter("date");
            String description = req.getParameter("description");
            String category = req.getParameter("category");
            double price = Double.parseDouble(req.getParameter("price"));
            int employeeId = Integer.parseInt(req.getParameter("employee_id"));

            invoiceService.create(title, date, description, category, price, employeeId);

            resp.sendRedirect(req.getServletPath());
        }
    }
}