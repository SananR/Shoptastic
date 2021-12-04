package me.shoptastic.app.data;

import me.shoptastic.app.data.model.User;

/**
 * Class that requests authentication and user information from the remote data source and
 * maintains an in-memory cache of login status and user credentials information.
 */
public class LoginRepository {

    private static volatile LoginRepository instance;

    private final LoginDataSource dataSource;

    // If user credentials will be cached in local storage, it is recommended it be encrypted
    // @see https://developer.android.com/training/articles/keystore
    private User user = null;

    // private constructor : singleton access
    private LoginRepository() {
        this.dataSource = new LoginDataSource();
    }

    public static LoginRepository getInstance() {
        if (instance == null) {
            instance = new LoginRepository();
        }
        return instance;
    }

    public User getUser() { return user; }

    public boolean isLoggedIn() {
        return user != null;
    }

    public void logout() {
        user = null;
        dataSource.logout();
    }

    public void setLoggedInUser(User user) {
        this.user = user;
    }

    public Result login(String email, String password) {
        // handle login
        Result result = dataSource.login(email, password);
        if (result instanceof Result.Success) {
            setLoggedInUser(((Result.Success<User>) result).getData());
        }
        return result;
    }

    public static User getUser(){
        return user;
    };
}