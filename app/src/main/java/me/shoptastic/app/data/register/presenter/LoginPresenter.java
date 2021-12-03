package me.shoptastic.app.data.register.presenter;

import android.util.Patterns;

import java.util.Arrays;
import java.util.Locale;

import me.shoptastic.app.R;
import me.shoptastic.app.data.LoginRepository;
import me.shoptastic.app.data.Result;
import me.shoptastic.app.data.model.Resources;
import me.shoptastic.app.data.model.User;
import me.shoptastic.app.data.register.RegisterRepository;

public class LoginPresenter {
    private LoginRepository loginRepository;

    public Result<User> login(String email, String password){
        Result<User> result = loginRepository.login(email, password);
        if (result instanceof Result.Success){
            User data = ((Result.Success<User>)result).getData();
        }
        return result;
    }
}