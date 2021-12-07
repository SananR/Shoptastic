package me.shoptastic.app.data.firebase;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import me.shoptastic.app.data.model.Customer;
import me.shoptastic.app.data.model.Resources;
import me.shoptastic.app.data.model.Result;
import me.shoptastic.app.data.model.StoreOwner;
import me.shoptastic.app.data.model.User;
import me.shoptastic.app.data.presenter.LoginPresenter;
import me.shoptastic.app.data.presenter.RegisterPresenter;

/**
 * Class that requests authentication and user information from the remote data source and
 * maintains an in-memory cache of login status and user credentials information.
 */
public class UserRepository {
    public static final String customersKey = "users";
    public static final String ownersKey = "owners";

    private static volatile UserRepository instance;

    private final DatabaseReference dRef;

    // If user credentials will be cached in local storage, it is recommended it be encrypted
    // @see https://developer.android.com/training/articles/keystore
    private User user = null;

    // private constructor : singleton access
    private UserRepository() {
        this.dRef = FirebaseDatabase.getInstance(Resources.FireBaseLink).getReference();
    }

    public static UserRepository getInstance() {
        if (instance == null) {
            instance = new UserRepository();
        }
        return instance;
    }
    public User getUser() { return user; }

    public boolean isLoggedIn() {
        return user != null;
    }

    //TODO
    public void logout() {
        user = null;
    }

    public void setLoggedInUser(User user) {
        this.user = user;
    }

    public void login(String email, String password, LoginPresenter presenter) {
        DatabaseReference ref = dRef.child(customersKey);
        ref.orderByChild("email").equalTo(email).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    System.out.println("exists");
                    for (DataSnapshot snap :
                            snapshot.getChildren()) {
                        String userPassword = snap.child("password").getValue(String.class);
                        if (userPassword != null && userPassword.equals(password)) {
                            presenter.onLoginSuccess(snap.getValue(Customer.class));
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
        ref = dRef.child(ownersKey);
        ref.orderByChild("email").equalTo(email).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot snap :
                            snapshot.getChildren()) {
                        String userPassword = snap.child("password").getValue(String.class);
                        if (userPassword != null && userPassword.equals(password)) {
                            presenter.onLoginSuccess(snap.getValue(StoreOwner.class));
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
        presenter.onLoginFailed(new Result.Error(new IllegalArgumentException("Password or username incorrect"),
                "Password or email incorrect"));
    }


    public void register(RegisterPresenter presenter, User user, String password) {
        // handle register
        dRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot snap : snapshot.child("users").getChildren()) {
                    Customer c = snap.getValue(Customer.class);
                    if (c.getEmail().equals(user.getEmail())) {
                        //User already exists as a customer
                        presenter.error(null, "Email address is in use.", null, null);
                        return;
                    }
                }
                for (DataSnapshot snap : snapshot.child("owners").getChildren()) {
                    StoreOwner o = snap.getValue(StoreOwner.class);
                    if (o.getEmail().equals(user.getEmail())) {
                        //User already exists as a store owner
                        presenter.error(null, "Email address is in use.", null, null);
                        return;
                    }
                }
                //Email is not in use
                String child;
                if (user instanceof Customer) child = "users";
                else child = "owners";
                dRef.child(child).child(user.getUUID()).setValue(user);
                dRef.child(child).child(user.getUUID()).child("password").setValue(password);
                setLoggedInUser(user);
                presenter.complete();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                throw new RuntimeException(error.getMessage());
            }
        });
    }


}
