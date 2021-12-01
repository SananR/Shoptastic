package me.shoptastic.app.data.register;

import me.shoptastic.app.data.Result;
import me.shoptastic.app.data.model.User;

public class RegisterRepository {
    private static volatile RegisterRepository instance;
    private final RegisterDataSource dataSource;


    // private constructor : singleton access
    private RegisterRepository() {
        this.dataSource = new RegisterDataSource();
    }

    public static RegisterRepository getInstance() {
        if (instance == null) {
            instance = new RegisterRepository();
        }
        return instance;
    }


    public Result<User> register(User user, String password) {
        // handle register
        return dataSource.register(user, password);
    }
}
