package me.shoptastic.app.data.register;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.SignInMethodQueryResult;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;

import me.shoptastic.app.data.LoginRepository;
import me.shoptastic.app.data.Result;
import me.shoptastic.app.data.model.Customer;
import me.shoptastic.app.data.model.Resources;
import me.shoptastic.app.data.model.User;

public class RegisterDataSource {
    private final FirebaseAuth fAuth;
    private final DatabaseReference dRef;

    public RegisterDataSource() {
        fAuth = FirebaseAuth.getInstance();
        dRef = FirebaseDatabase.getInstance(Resources.FireBaseLink).getReference();
    }

    public boolean isEmailRegistered(String email) {
        fAuth.fetchSignInMethodsForEmail(email).addOnCompleteListener(new OnCompleteListener<SignInMethodQueryResult>() {
            @Override
            public void onComplete(@NonNull Task<SignInMethodQueryResult> task) {
                if (task.isSuccessful()) {
                    return;
                }
            }
        });
        return true;
    }

    public Result register(User user, String password) {
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
                    //TODO
                    Log.d("TEST", task.getException().getMessage());
                }
            });
            return new Result.Success<>(user);
//            if (fAuth.getCurrentUser() != null) return new Result.Success<>(user);
//            else return new Result.Error(new IOException("Error creating Firebase user"), "Registration worked but current ");
        } catch (Exception e) {
            return new Result.Error(new IOException("Error registering new user", e), "Error registering user");
        }
    }
}
