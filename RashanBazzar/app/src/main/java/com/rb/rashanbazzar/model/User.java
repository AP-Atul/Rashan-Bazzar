package com.rb.rashanbazzar.model;

public class User {
    private String email, name, city;

    public User(String email, String name, String city) {
        this.email = email;
        this.name = name;
        this.city = city;
    }

    public User() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
