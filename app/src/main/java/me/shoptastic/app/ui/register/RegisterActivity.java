package me.shoptastic.app.ui.register;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

import me.shoptastic.app.OwnerRegisterActivity;
import me.shoptastic.app.R;
import me.shoptastic.app.StoresActivity;
import me.shoptastic.app.data.Result;
import me.shoptastic.app.data.model.Resources;
import me.shoptastic.app.data.register.presenter.RegisterPresenter;

public class RegisterActivity extends AppCompatActivity {

    private RegisterPresenter presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.presenter = new RegisterPresenter(this);
        Resources.setContext(this);
        setContentView(R.layout.activity_register);
        Button button = (Button) findViewById(R.id.button_register);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                register(v);
            }
        });
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
        final EditText name = findViewById(R.id.editTextTextPersonName);
        final EditText email = findViewById(R.id.editTextTextEmailAddress);
        final EditText phone = findViewById(R.id.editTextPhone);
        final EditText password = findViewById(R.id.editTextTextPassword);
        final CheckBox checkBox = findViewById(R.id.checkBox);
        Boolean valid = presenter.validateInput(name.getText().toString(), email.getText().toString(), phone.getText().toString(), password.getText().toString());
        if (valid) {
            if (!checkBox.isChecked()) {
                Result r = presenter.register(name.getText().toString(), email.getText().toString(), phone.getText().toString(), password.getText().toString());
                if (r instanceof Result.Success) {
                    Intent intent = new Intent(this, StoresActivity.class);
                    startActivity(intent);
                }
            } else {
                Intent intent = new Intent(this, OwnerRegisterActivity.class);
                intent.putExtra("me.shoptastic.app.name", name.getText().toString());
                intent.putExtra("me.shoptastic.app.email", email.getText().toString());
                intent.putExtra("me.shoptastic.app.phone", phone.getText().toString());
                intent.putExtra("me.shoptastic.app.password", password.getText().toString());
                startActivity(intent);
            }
        }

    }
}
