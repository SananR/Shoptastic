package me.shoptastic.app.ui.register;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

import me.shoptastic.app.R;
import me.shoptastic.app.data.Result;
import me.shoptastic.app.data.model.Resources;
import me.shoptastic.app.data.register.presenter.RegisterCustomerPresenter;
import me.shoptastic.app.data.register.presenter.RegisterOwnerPresenter;
import me.shoptastic.app.data.register.presenter.RegisterPresenter;

public class RegisterActivity extends AppCompatActivity {

    private RegisterPresenter presenter;

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
        if (!checkBox.isChecked()) {
            presenter = new RegisterCustomerPresenter(this);
            Result r = presenter.register(name.getText().toString(), email.getText().toString(), phone.getText().toString(), password.getText().toString());
            if (r instanceof Result.Success) {

            } else {
                //TODO Update UI with error
            }
        } else {
            //TODO Need some changes to architecture to support this
           // presenter = new RegisterOwnerPresenter();

        }
    }
}
