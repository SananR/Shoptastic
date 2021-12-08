package me.shoptastic.app.ui;

import android.os.Bundle;
import android.text.Editable;
import android.view.View;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import me.shoptastic.app.R;
import me.shoptastic.app.data.firebase.UserRepository;
import me.shoptastic.app.data.presenter.LoginPresenter;

public class LoginActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UserRepository.getInstance().logout();
        setContentView(R.layout.activity_login);
        findViewById(R.id.login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn(view);
            }
        });
    }

    public String getEmail() {
        Editable email = ((TextInputEditText) findViewById(R.id.loginEmail)).getText();
        if (email == null) return "";
        return email.toString();
    }

    public String getPassword() {
        Editable password = (((TextInputEditText) findViewById(R.id.loginPassword))).getText();
        if (password == null) return "";
        return password.toString();
    }

    public void signIn(View v) {
        LoginPresenter presenter = new LoginPresenter(this);
        presenter.login();
    }

    public void error(String err) {
        TextInputLayout emailLayout = findViewById(R.id.loginEmailLayout);
        TextInputLayout passwordLayout = findViewById(R.id.loginPasswordLayout);
        emailLayout.setError(err);
        passwordLayout.setError(err);
    }
}