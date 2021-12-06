package me.shoptastic.app.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputLayout;

import java.util.HashSet;

import me.shoptastic.app.R;
import me.shoptastic.app.data.model.Store;
import me.shoptastic.app.data.register.presenter.RegisterOwnerPresenter;
import me.shoptastic.app.data.register.presenter.RegisterStorePresenter;

public class OwnerRegisterActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_owner);
        Button button = findViewById(R.id.button_owner_register);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                register(v);
            }
        });
    }

    public String getName() {
        return getIntent().getStringExtra(RegisterActivity.name);
    }

    public String getEmail() {
        return getIntent().getStringExtra(RegisterActivity.email);
    }

    public String getPhone() {
        return getIntent().getStringExtra(RegisterActivity.phone);
    }

    public String getPassword() {
        return getIntent().getStringExtra(RegisterActivity.password);
    }

    public String getAddress() {
        return ((EditText) findViewById(R.id.editTextStoreAddress)).getText().toString();
    }

    public String getStoreName() {
        return ((EditText) findViewById(R.id.editTextStoreName)).getText().toString();
    }

    public Store getStore() {
        return new Store(getStoreName(), getAddress(), new HashSet<>());
    }

    public void error(String name, String address) {
        TextInputLayout tilName = findViewById(R.id.tilStoreName);
        TextInputLayout tilAddress = findViewById(R.id.tilStoreAddress);
        if (name != null) tilName.setError(name);
        else tilName.setErrorEnabled(false);
        if (address != null) tilAddress.setError(address);
        else tilAddress.setErrorEnabled(false);
    }

    public void register(View v) {
        RegisterOwnerPresenter presenter = new RegisterOwnerPresenter(this);
        boolean valid = presenter.validateInput();
        if (valid) {
            presenter.register(new Store(getStoreName(), getAddress(), new HashSet<>()));
        }
    }


}