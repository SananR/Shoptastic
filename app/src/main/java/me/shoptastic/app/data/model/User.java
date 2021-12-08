package me.shoptastic.app.data.model;

import com.google.firebase.database.Exclude;

import java.util.Objects;
import java.util.UUID;

import javax.annotation.Nonnull;

import me.shoptastic.app.data.firebase.CartRepository;

/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 */
public abstract class User {

    private String email;
    private String phone;
    private String displayName;
    private String password;
    @Nonnull
    private String uuid;

    public User(String email, String displayName, String phone, String password) {
        this.email = email;
        this.phone = phone;
        this.displayName = displayName;
        this.uuid = UUID.randomUUID().toString();
        this.password = password;
    }

    public User() {
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

    public String getUUID() {
        return this.uuid;
    }

    public String getPassword() {
        return password;
    }

    @Exclude
    public Order getOrder() {
        return CartRepository.getInstance().getOrder();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return email.equals(user.email) && phone.equals(user.phone) && displayName.equals(user.displayName) && password.equals(user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, phone, displayName, password);
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", displayName='" + displayName + '\'' +
                ", password='" + password + '\'' +
                ", uuid='" + uuid + '\'' +
                '}';
    }
}