package me.shoptastic.app.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputLayout;

import me.shoptastic.app.R;
import me.shoptastic.app.data.model.Resources;
import me.shoptastic.app.data.presenter.RegisterCustomerPresenter;

public class RegisterActivity extends Activity {

    public static String name = "me.shoptastic.app.name";
    public static String email = "me.shoptastic.app.email";
    public static String phone = "me.shoptastic.app.phone";
    public static String password = "me.shoptastic.app.password";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Resources.setContext(this);
        setContentView(R.layout.activity_register);
        Button button = findViewById(R.id.button_register);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                register(v);
            }
        });

    }

    public String getName() {
        return ((EditText) findViewById(R.id.editTextTextPersonName)).getText().toString();
    }

    public String getEmail() {
        return ((EditText) findViewById(R.id.editTextTextEmailAddress)).getText().toString();
    }

    public String getPhone() {
        return ((EditText) findViewById(R.id.editTextPhone)).getText().toString();
    }

    public String getPassword() {
        return ((EditText) findViewById(R.id.editTextTextPassword)).getText().toString();
    }

    public boolean getCheckbox() {
        return ((CheckBox) findViewById(R.id.checkBox)).isChecked();
    }

    public void error(String name, String email, String phone, String password) {
        TextInputLayout tilEmail = findViewById(R.id.tilEmail);
        TextInputLayout tilName = findViewById(R.id.tilName);
        TextInputLayout tilPhone = findViewById(R.id.tilPhone);
        TextInputLayout tilPass = findViewById(R.id.tilPassword);
        if (name != null) tilName.setError(name);
        else tilName.setErrorEnabled(false);
        if (email != null) tilEmail.setError(email);
        else tilEmail.setErrorEnabled(false);
        if (phone != null) tilPhone.setError(phone);
        else tilPhone.setErrorEnabled(false);
        if (password != null) tilPass.setError(password);
        else tilPass.setErrorEnabled(false);
    }

    public void register(View v) {
        RegisterCustomerPresenter presenter = new RegisterCustomerPresenter(this);
        if (presenter.validateInput()) {
            presenter.validateUserRegister(getCheckbox());
        }
    }
}
