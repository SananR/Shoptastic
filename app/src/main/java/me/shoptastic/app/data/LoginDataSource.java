package me.shoptastic.app.data;


import com.google.firebase.auth.FirebaseAuth;

import me.shoptastic.app.R;
import me.shoptastic.app.data.model.User;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class LoginDataSource {

    private final FirebaseAuth fAuth;

    public LoginDataSource() { fAuth = FirebaseAuth.getInstance(); }

    public Result<User> login(String username, String password) {

        return new Result.Error(new Exception(""), R.string.login_failed);
    }

    public void logout() {
        // TODO: revoke authentication
    }
}