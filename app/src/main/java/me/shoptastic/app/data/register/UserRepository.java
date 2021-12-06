package me.shoptastic.app.data.register;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import me.shoptastic.app.data.model.Customer;
import me.shoptastic.app.data.model.Resources;
import me.shoptastic.app.data.model.User;
import me.shoptastic.app.data.register.presenter.RegisterCustomerPresenter;

/**
 * Class that requests authentication and user information from the remote data source and
 * maintains an in-memory cache of login status and user credentials information.
 */
public class UserRepository {

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

    private void setLoggedInUser(User user) {
        this.user = user;
    }

    public void login(String email, String password) {
        //TODO handle login
    }

    public void validateUserRegister(RegisterCustomerPresenter presenter, String email, boolean owner) {
        dRef.child("users").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful()) {
                    for (DataSnapshot snap : task.getResult().getChildren()) {
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
        if (user instanceof Customer) child = "users";
        else child = "owners";
        dRef.child(child).child(user.getUUID()).setValue(user);
    }


}
