package me.shoptastic.app.data;

import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;

import me.shoptastic.app.data.model.Customer;
import me.shoptastic.app.data.model.StoreOwner;
import me.shoptastic.app.data.model.User;

public class RegisterDataSource {
    private final FirebaseDatabase firebaseDatabase;

    public RegisterDataSource() {
        firebaseDatabase = FirebaseDatabase.getInstance();
    }

    public Result<User> register(User user) {

        try {
            if (user instanceof Customer) {

            } else if (user instanceof StoreOwner) {

            }
            // TODO: handle loggedInUser authentication
//            User fakeUser =
//                    new User(
//                            java.util.UUID.randomUUID().toString(),
//                            "Jane Doe");
            return new Result.Success<>(null);
        } catch (Exception e) {
            return new Result.Error(new IOException("Error logging in", e));
        }
    }
}
