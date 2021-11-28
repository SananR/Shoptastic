package me.shoptastic.app.data;

import com.google.firebase.database.FirebaseDatabase;

import me.shoptastic.app.R;
import me.shoptastic.app.data.model.User;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class LoginDataSource {
    private final FirebaseDatabase firebaseDatabase;
    public LoginDataSource(){
        firebaseDatabase = FirebaseDatabase.getInstance("https://shoptastic-6670d-default-rtdb.firebaseio.com/");
    }

    public Result<User> login(String username, String password) {
        return new Result.Error(new Exception(""), R.string.login_failed);
//        try {
//            // TODO: handle loggedInUser authentication
////            User fakeUser =
////                    new User(
////                            java.util.UUID.randomUUID().toString(),
////                            "Jane Doe");
//            return new Result.Success<>(null);
//        } catch (Exception e) {
//            return new Result.Error(new IOException("Error logging in", e));
//        }
    }

    public void logout() {
        // TODO: revoke authentication
    }
}