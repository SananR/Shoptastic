package me.shoptastic.app.data.firebase;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import me.shoptastic.app.data.model.Customer;
import me.shoptastic.app.data.model.Resources;
import me.shoptastic.app.data.model.Result;
import me.shoptastic.app.data.model.StoreOwner;
import me.shoptastic.app.data.model.User;
import me.shoptastic.app.data.presenter.LoginPresenter;
import me.shoptastic.app.data.presenter.RegisterCustomerPresenter;

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
        dRef.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful()) {
                    for (DataSnapshot snapshot : task.getResult().child(customersKey).getChildren()) {
                        String userPassword = snapshot.child("password").getValue(String.class);
                        if (userPassword != null && userPassword.equals(password)) {
                            presenter.onLoginSuccess(snapshot.getValue(Customer.class));
                            return;
                        }
                    }
                    for (DataSnapshot snapshot : task.getResult().child(ownersKey).getChildren()) {
                        String userPassword = snapshot.child("password").getValue(String.class);
                        if (userPassword != null && userPassword.equals(password)) {
                            presenter.onLoginSuccess(snapshot.getValue(StoreOwner.class));
                            return;
                        }
                    }
                }
                presenter.onLoginFailed(new Result.Error(new IllegalArgumentException("Password or username incorrect"),
                        "Password or email incorrect"));
            }
        });

    }


    public void validateUserRegister(RegisterCustomerPresenter presenter, String email, boolean owner) {
        dRef.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful()) {
                    for (DataSnapshot snap : task.getResult().child(customersKey).getChildren()) {
                        Customer c = snap.getValue(Customer.class);
                        if (email.equals(c.getEmail())) {
                            //User exists already
                            presenter.errorUserExists();
                            return;
                        }
                    }
                    for (DataSnapshot snap : task.getResult().child(ownersKey).getChildren()) {
                        Customer c = snap.getValue(Customer.class);
                        if (email.equals(c.getEmail())) {
                            //User exists already
                            presenter.errorUserExists();
                            return;
                        }
                    }
                    presenter.complete(owner);
                } else {
                    throw new RuntimeException(task.getException());
                }
            }
        });
    }

    public void register(User user) {
        // handle register
        String child;
        if (user instanceof Customer) child = customersKey;
        else child = ownersKey;
        dRef.child(child).child(user.getUUID()).setValue(user);
        setLoggedInUser(user);
    }


}
