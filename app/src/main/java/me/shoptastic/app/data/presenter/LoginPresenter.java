package me.shoptastic.app.data.presenter;

import android.content.Intent;

import me.shoptastic.app.data.firebase.UserRepository;
import me.shoptastic.app.data.model.Result;
import me.shoptastic.app.data.model.User;
import me.shoptastic.app.ui.Account;
import me.shoptastic.app.ui.LoginActivity;
import me.shoptastic.app.ui.StoresActivity;

public class LoginPresenter {
    private final UserRepository instance;
    LoginActivity activity;

    public LoginPresenter(LoginActivity activity) {
        this.activity = activity;
        instance = UserRepository.getInstance();
    }

    public LoginPresenter(LoginActivity activity, UserRepository repository) {
        this.activity = activity;
        instance = repository;
    }

    //TODO
    public void login() {
        instance.login(activity.getEmail(), activity.getPassword(), this);
    }

    public void onLoginSuccess(User user) {
        instance.setLoggedInUser(user);
        //Intent i = new Intent(activity, StoresActivity.class);
        Intent i = new Intent(activity, Account.class);
        activity.startActivity(i);
    }

    public void onLoginFailed(Result.Error err) {
        activity.error(err.getError());
    }
}