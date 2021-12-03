package me.shoptastic.app.ui.register;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashSet;

import me.shoptastic.app.R;
import me.shoptastic.app.data.Result;
import me.shoptastic.app.data.model.Product;
import me.shoptastic.app.data.model.Resources;
import me.shoptastic.app.data.model.Store;
import me.shoptastic.app.data.model.StoreOwner;
import me.shoptastic.app.data.register.presenter.RegisterCustomerPresenter;
import me.shoptastic.app.data.register.presenter.RegisterOwnerPresenter;
import me.shoptastic.app.data.register.presenter.RegisterPresenter;

public class RegisterActivity extends AppCompatActivity {

    private RegisterPresenter presenter;
    StoreOwner storeOwner;

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



    public void register(View v) {
        Log.d("TEST", "3");
        final EditText name = findViewById(R.id.editTextTextPersonName);
        final EditText email = findViewById(R.id.editTextTextEmailAddress);
        final EditText phone = findViewById(R.id.editTextPhone);
        final EditText password = findViewById(R.id.editTextTextPassword);
        final CheckBox checkBox = findViewById(R.id.checkBox);
        if (!checkBox.isChecked()) {
            presenter = new RegisterCustomerPresenter();
            Log.d("TEST", "4");
            Result r = presenter.register(name.getText().toString(), email.getText().toString(), phone.getText().toString(), password.toString());
            if (r instanceof Result.Success) {

            } else {
                //TODO Update UI with error
            }
        } else {
            HashSet<Product> p = new HashSet<Product>();
            Store s = new Store("A", "A", p);
            storeOwner = new StoreOwner(name.getText().toString(), email.getText().toString(), phone.getText().toString(), s);

        }
    }
}
