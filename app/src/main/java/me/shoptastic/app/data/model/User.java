package me.shoptastic.app.data.model;

import java.util.ArrayList;

/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 */
public abstract class User {

    private String email;
    private String password;
    private String phone;
    private String displayName;


    public User(String email, String password, String displayName, String phone) {
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.displayName = displayName;
    }

    public String getEmail() {
        return email;
    }

    public String getDisplayName() {
        return displayName;
    }

    public abstract ArrayList<Order> getOrders();
}