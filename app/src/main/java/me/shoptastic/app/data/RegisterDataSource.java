package me.shoptastic.app.data;

import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;

import me.shoptastic.app.data.model.Customer;
import me.shoptastic.app.data.model.User;

public class RegisterDataSource {
    private final FirebaseAuth fAuth;
    private final DatabaseReference dRef;

    public RegisterDataSource() {
        fAuth = FirebaseAuth.getInstance();
        dRef = FirebaseDatabase.getInstance().getReference();
    }

    public Result<User> register(User user, String password) {
        try {
            fAuth.createUserWithEmailAndPassword(user.getEmail(), password).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    //Registration successful
                    String child;
                    if (user instanceof Customer) child = "users";
                    else child = "owners";
                    dRef.child(child).child(user.getUUID().toString()).setValue(user);
                    LoginRepository.getInstance().setLoggedInUser(user);
                    Log.d("TEST", "Success");
                } else {
                    //TODOs
                    Log.d("TEST", task.getException().getMessage());
                    Log.d("TEST", user.getEmail());
                }
            });
            if (fAuth.getCurrentUser() != null) return new Result.Success<>(user);
            else return new Result.Error(new IOException("Error creating Firebase user"));
        } catch (Exception e) {
            return new Result.Error(new IOException("Error registering new user", e));
        }
    }
}
