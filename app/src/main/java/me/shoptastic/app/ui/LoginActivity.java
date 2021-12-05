package me.shoptastic.app.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import me.shoptastic.app.R;

public class LoginActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);
    }

    public void signIn(View v) {
        // User has clicked sign in
        final EditText email = findViewById(R.id.username);
        final EditText password = findViewById(R.id.password);
        final Button login = findViewById(R.id.login);
        final ProgressBar loading = findViewById(R.id.loading);

       /* Result user = repository.login(email.getText().toString(), password.getText().toString());
        if (user instanceof Result.Success) {
            // User login successful, start new activity
        } else if (user instanceof Result.Error) {
            // User login failed, display error
            showLoginFailed(((Result.Error) user).getError());
        } else {
            throw new IllegalArgumentException("Result returned not a success and not a failure?");
        }*/
    }


    private void showLoginFailed(String errorString) {
        Toast.makeText(getApplicationContext(), errorString, Toast.LENGTH_SHORT).show();
    }
}