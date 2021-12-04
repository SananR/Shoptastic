package me.shoptastic.app.ui.register;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import me.shoptastic.app.R;
import me.shoptastic.app.data.Result;
import me.shoptastic.app.data.model.Resources;
import me.shoptastic.app.data.register.presenter.RegisterCustomerPresenter;
import me.shoptastic.app.data.register.presenter.RegisterOwnerPresenter;
import me.shoptastic.app.data.register.presenter.RegisterPresenter;

public class RegisterActivity extends AppCompatActivity {

    public static String name = "me.shoptastic.app.name"";
    public static String email = "me.shoptastic.app.email";
    public static String phone = "me.shoptastic.app.phone";
    public static String password = "me.shoptastic.app.password";


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

    public void error(boolean name, boolean email, boolean phone, boolean password) {
        TextInputLayout tilEmail = (TextInputLayout) findViewById(R.id.tilEmail);
        TextInputLayout tilName = (TextInputLayout) findViewById(R.id.tilName);
        TextInputLayout tilPhone = (TextInputLayout) findViewById(R.id.tilPhone);
        TextInputLayout tilPass = (TextInputLayout) findViewById(R.id.tilPassword);
        if (name) tilName.setError(Resources.getString(R.string.register_invalid_name));
        else tilName.setErrorEnabled(false);
        if (email) tilEmail.setError(Resources.getString(R.string.register_invalid_email));
        else tilEmail.setErrorEnabled(false);
        if (phone) tilPhone.setError(Resources.getString(R.string.register_invalid_phone));
        else tilPhone.setErrorEnabled(false);
        if (password) tilPass.setError(Resources.getString(R.string.register_invalid_password));
        else tilPass.setErrorEnabled(false);
    }

    public void register(View v) {
        Boolean valid = presenter.validateInput();
        if (valid) {
            final boolean checkBox = ((CheckBox) findViewById(R.id.checkBox)).isChecked();
            if (!checkBox) {
                Result r = presenter.register();
                if (r instanceof Result.Success) {
                    Intent intent = new Intent(this, StoresActivity.class);
                    startActivity(intent);
                }
            } else {
                Intent intent = new Intent(this, OwnerRegisterActivity.class);
                intent.putExtra(RegisterActivity.name, getName());
                intent.putExtra(RegisterActivity.email, getEmail());
                intent.putExtra(RegisterActivity.phone, getPhone());
                intent.putExtra(RegisterActivity.password, getPassword());
                startActivity(intent);
            }
        }
    }
}
