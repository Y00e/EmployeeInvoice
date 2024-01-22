package com.example.projectemployeeinvoice;


import java.util.Date;



public class Payment {
    private int id;
    private String title;
    private Date date;
    private String description;
    private String category;
    private double price;
    private int employeeId;


    // Standardkonstruktor
    public Payment() {
    }
    // Konstruktor för att skapa en Payment baserat på data från databasen
    public Payment(int id, String title, Date date, String description, String category, double price, int employeeId) {
        this.id = id;
        this.title = title;
        this.date = date;
        this.description = description;
        this.category = category;
        this.price = price;
        this.employeeId = employeeId;
    }

    // Konstruktor för att skapa en ny Payment som ska läggas till i databasen
    public Payment(String title, Date date, String description, String category, double price, int employeeId) {
        this.title = title;
        this.date = date;
        this.description = description;
        this.category = category;
        this.price = price;
        this.employeeId = employeeId;
    }


    // getters
    public int getId() {
        return id;

    }

    public String getTitle() {
        return title;
    }

    public Date getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    public String getCategory() {
        return category;
    }

    public double getPrice() {
        return price;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    // setters
    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

}