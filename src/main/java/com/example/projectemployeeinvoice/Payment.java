package com.example.projectemployeeinvoice;


import java.util.Date;



public class Payment {
    private int id;
    private String title;
    private String date;
    private String description;
    private String category;
    private double price;
    private int employeeId;



    // getters
    public int getId() {
        return id;

    }

    public String getTitle() {
        return title;
    }

    public String getDate() {
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

    public void setDate(Stirng date) {
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
