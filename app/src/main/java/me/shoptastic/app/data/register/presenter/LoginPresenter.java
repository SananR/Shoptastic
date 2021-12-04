package me.shoptastic.app.data.register.presenter;

import me.shoptastic.app.data.LoginRepository;
import me.shoptastic.app.data.Result;
import me.shoptastic.app.data.model.User;

public class LoginPresenter {
    private LoginRepository loginRepository;

    public Result login(String email, String password) {
        Result result = loginRepository.login(email, password);
        if (result instanceof Result.Success) {
            User data = ((Result.Success<User>) result).getData();
        }
        return result;
    }
}