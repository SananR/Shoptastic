package me.shoptastic.app.ui.register;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashSet;

import me.shoptastic.app.R;
import me.shoptastic.app.data.model.Resources;
import me.shoptastic.app.data.model.Store;
import me.shoptastic.app.data.register.presenter.RegisterOwnerPresenter;
import me.shoptastic.app.data.register.presenter.RegisterStorePresenter;

public class RegisterStoreActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Resources.setContext(this);
        setContentView(R.layout.activity_shop_info_sign_in);
        Button button = (Button) findViewById(R.id.shop_confirm);
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

    public String getLocation() {
        return ((EditText) findViewById(R.id.store_address)).getText().toString();
    }

    public String getStoreName() {
        return ((EditText) findViewById(R.id.store_name)).getText().toString();
    }

    public Store getStore() {
        return new Store(getName(), getLocation(), new HashSet<>());
    }

    public void register(View v) {
        RegisterStorePresenter storePresenter = new RegisterStorePresenter(this);
        storePresenter.register();
        RegisterOwnerPresenter ownerPresenter = new RegisterOwnerPresenter(this);
        ownerPresenter.register();
    }


    public void showErrorMsg(String errorString) {
        Toast.makeText(getApplicationContext(), errorString, Toast.LENGTH_SHORT).show();
    }

}