package me.shoptastic.app.data.model;

import java.util.ArrayList;
import java.util.Objects;
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

    public String getPhone() {
        return phone;
    }

    public String getDisplayName() {
        return displayName;
    }

    public UUID getUUID() {
        return uuid;
    }

    public abstract ArrayList<Order> getOrders();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return email.equals(user.email) && phone.equals(user.phone) && displayName.equals(user.displayName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, phone, displayName);
    }
}