package me.shoptastic.app.data;


import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;

import me.shoptastic.app.data.model.Customer;
import me.shoptastic.app.data.model.Product;
import me.shoptastic.app.data.model.Store;
import me.shoptastic.app.data.model.StoreOwner;
import me.shoptastic.app.data.model.User;

public class StoreDataSource {
    private final FirebaseAuth fAuth;
    private final DatabaseReference dRef;

    public StoreDataSource() {
        fAuth = FirebaseAuth.getInstance();
        dRef = FirebaseDatabase.getInstance().getReference();
    }

    public Result<User> register(StoreOwner user, Store store, String password) {
        try {
            fAuth.createUserWithEmailAndPassword(user.getEmail(), password).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    //Registration successful
                    String child;
                    child = "stores";
                    dRef.child(child).child(user.getUUID().toString()).setValue(store);
                    LoginRepository.getInstance().setLoggedInUser(user);
                } else {
                    //TODO
                    Log.d("TEST", task.getException().getMessage());
                }
            });
            if (fAuth.getCurrentUser() != null) return new Result.Success<>(store);
            else return new Result.Error(new IOException("Error creating Firebase store"));
        } catch (Exception e) {
            return new Result.Error(new IOException("Error registering new store", e));
        }
    }
}
