package me.shoptastic.app.data;

import me.shoptastic.app.data.model.User;

public class RegisterRepository {
    private static volatile RegisterRepository instance;
    private final RegisterDataSource dataSource;


    // private constructor : singleton access
    private RegisterRepository(RegisterDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public static RegisterRepository getInstance(RegisterDataSource dataSource) {
        if (instance == null) {
            instance = new RegisterRepository(dataSource);
        }
        return instance;
    }


    public Result<User> register(User user) {
        // handle register
        return dataSource.register(user);
    }
}
