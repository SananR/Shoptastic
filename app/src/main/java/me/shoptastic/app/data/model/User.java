package me.shoptastic.app.data.model;

import java.util.ArrayList;

/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 */
public abstract class User {

    private final String email;
    private final String phone;
    private final String displayName;


    public User(String email, String displayName, String phone) {
        this.email = email;
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