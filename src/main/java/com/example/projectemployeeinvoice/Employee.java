package com.example.projectemployeeinvoice;

public class Employee {
    private int id;
    private String username;
    private String password;

    // Constructor
    public Employee(int id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }


    // En ny konstruktor som bara tar emot username och password
    public Employee(String username, String password) {
        this.username = username;
        this.password = password;
    }


    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
