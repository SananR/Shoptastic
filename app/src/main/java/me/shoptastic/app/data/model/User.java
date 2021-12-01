package me.shoptastic.app.data.model;

import java.util.ArrayList;
import java.util.UUID;

import javax.annotation.Nonnull;

/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 */
public abstract class User {

    private final String email;
    private final String phone;
    private final String displayName;
    @Nonnull
    private final UUID uuid;

    public User(String email, String displayName, String phone) {
        this.email = email;
        this.phone = phone;
        this.displayName = displayName;
        this.uuid = UUID.randomUUID();
    }

    public String getEmail() {
        return email;
    }

    public String getDisplayName() {
        return displayName;
    }

    public UUID getUUID() { return uuid; }

    public abstract ArrayList<Order> getOrders();
}