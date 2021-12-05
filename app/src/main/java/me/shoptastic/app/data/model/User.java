package me.shoptastic.app.data.model;

import java.util.ArrayList;
import java.util.UUID;

import javax.annotation.Nonnull;

/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 */
public abstract class User {

    private String email;
    private String phone;
    private String displayName;
    @Nonnull
    private String uuid;
    public ArrayList<Product> anOrder = new ArrayList<Product>();

    public User(String email, String displayName, String phone) {
        this.email = email;
        this.phone = phone;
        this.displayName = displayName;
        this.uuid = UUID.randomUUID().toString();
    }

    public User(){}

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getUUID() { return this.uuid; }

    public abstract ArrayList<Product> getOrder();
}