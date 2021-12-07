package me.shoptastic.app.data.presenter;

import android.content.Intent;

import me.shoptastic.app.data.firebase.UserRepository;
import me.shoptastic.app.data.model.Result;
import me.shoptastic.app.data.model.User;
import me.shoptastic.app.ui.LoginActivity;
import me.shoptastic.app.ui.StoresActivity;

public class LoginPresenter {
    LoginActivity activity;

    public LoginPresenter(LoginActivity activity) {
        this.activity = activity;
    }

    //TODO
    public void login() {
        UserRepository.getInstance().login(activity.getEmail(), activity.getPassword(), this);
    }

    public void onLoginSuccess(User user) {
        UserRepository.getInstance().setLoggedInUser(user);
        Intent i = new Intent(activity, StoresActivity.class);
        activity.startActivity(i);
    }

    public void onLoginFailed(Result.Error err) {
        activity.error(err.getError());
    }
}