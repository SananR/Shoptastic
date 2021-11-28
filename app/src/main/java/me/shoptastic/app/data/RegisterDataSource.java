package me.shoptastic.app.data;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.io.IOException;

import me.shoptastic.app.R;
import me.shoptastic.app.data.model.Customer;
import me.shoptastic.app.data.model.StoreOwner;
import me.shoptastic.app.data.model.User;

public class RegisterDataSource {
    private final FirebaseAuth fAuth;

    public RegisterDataSource() {
        fAuth = FirebaseAuth.getInstance();
    }

    public Result<User> register(User user, String password) {
        try {
            if (user instanceof Customer) {
                fAuth.createUserWithEmailAndPassword(user.getEmail(), password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            //Registration successful
                            //TODO DATA
                            return;
                        } else {

                        }
                    }
                })
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
