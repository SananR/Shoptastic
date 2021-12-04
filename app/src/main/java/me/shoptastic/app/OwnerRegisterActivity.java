package me.shoptastic.app;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

import me.shoptastic.app.data.Result;
import me.shoptastic.app.data.model.Resources;
import me.shoptastic.app.data.register.presenter.RegisterOwnerPresenter;

public class OwnerRegisterActivity extends AppCompatActivity {

    private RegisterOwnerPresenter presenter;

    private String name, email, phone, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        this.presenter = new RegisterOwnerPresenter(this);

        this.name = intent.getStringExtra("me.shoptastic.app.name");
        this.email = intent.getStringExtra("me.shoptastic.app.email");
        this.phone = intent.getStringExtra("me.shoptastic.app.phone");
        this.password = intent.getStringExtra("me.shoptastic.app.password");

        setContentView(R.layout.activity_register_owner);
        Button button = (Button) findViewById(R.id.button_owner_register);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                register(v);
            }
        });
    }
    public void error(boolean name, boolean address) {
        TextInputLayout tilName = (TextInputLayout) findViewById(R.id.tilStoreName);
        TextInputLayout tilAddress = (TextInputLayout) findViewById(R.id.tilStoreAddress);
        if (name) tilName.setError(Resources.getString(R.string.register_invalid_name));
        else tilName.setErrorEnabled(false);
        if (address) tilAddress.setError(Resources.getString(R.string.register_invalid_email));
        else tilAddress.setErrorEnabled(false);
    }

    public void register(View v) {
        final EditText storeName = findViewById(R.id.editTextStoreName);
        final EditText address = findViewById(R.id.editTextStoreAddress);
        Result r = presenter.register(this.name, this.email, this.phone, this.password, storeName.getText().toString(), address.getText().toString(), null);
        if (r instanceof Result.Success) {
            Log.d("YAY", "WORKED");
        } else {

        }
    }


}