package me.shoptastic.app.ui.register;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import me.shoptastic.app.R;
import me.shoptastic.app.data.Result;
import me.shoptastic.app.data.model.Resources;
import me.shoptastic.app.data.register.presenter.RegisterPresenter;
import me.shoptastic.app.data.register.presenter.RegisterStorePresenter;

public class RegisterStoreActivity extends RegisterActivity {
    public static String storeLocation;
    public static String storeOwnerName;
    public static String storeOwnerEmail;

    private RegisterPresenter presenter;
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

    public void register(View v) {
        final EditText store_name = findViewById(R.id.store_name);
        final EditText location = findViewById(R.id.store_address);
        final EditText password = findViewById(R.id.editTextTextPassword);
        storeLocation = location.getText().toString();
        storeOwnerName = storeOwner.getDisplayName();
        storeOwnerEmail = storeOwner.getEmail();
        String ownerPhone = storeOwner.getPhone();
            presenter = new RegisterStorePresenter();
            Result r = presenter.register(store_name.getText().toString(), storeOwnerEmail, ownerPhone, password.toString());
            if (r instanceof Result.Success) {
            } else {
                //TODO Update UI with error
            }
        }

    }