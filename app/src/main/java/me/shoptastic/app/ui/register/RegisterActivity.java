package me.shoptastic.app.ui.register;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import me.shoptastic.app.R;
import me.shoptastic.app.data.model.Resources;
import me.shoptastic.app.data.register.presenter.RegisterCustomerPresenter;

public class RegisterActivity extends AppCompatActivity {

    public static String name = "name";
    public static String email = "email";
    public static String phone = "phone";
    public static String password = "password";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Resources.setContext(this);
        setContentView(R.layout.activity_register);
        Button button = (Button) findViewById(R.id.button_register);
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

    public void register(View v) {
        final boolean checkBox = ((CheckBox) findViewById(R.id.checkBox)).isChecked();
        RegisterCustomerPresenter presenter = new RegisterCustomerPresenter(this);
        if (!checkBox) {
            presenter.register();
        } else {
            if (presenter.validate(getName(), getEmail(), getPhone(), getPassword())) {
                Intent intent = new Intent(this, RegisterStoreActivity.class);
                intent.putExtra(RegisterActivity.name, getName());
                intent.putExtra(RegisterActivity.email, getEmail());
                intent.putExtra(RegisterActivity.phone, getPhone());
                intent.putExtra(RegisterActivity.password, getPassword());
                startActivity(intent);
            }
        }
    }

    public void showErrorMsg(String errorString) {
        Toast.makeText(getApplicationContext(), errorString, Toast.LENGTH_SHORT).show();
    }
}
