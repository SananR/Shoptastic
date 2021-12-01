package me.shoptastic.app.ui.register;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import me.shoptastic.app.R;
import me.shoptastic.app.data.RegisterRepository;
import me.shoptastic.app.data.model.Customer;

public class RegisterCustomerActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_register);
        Button button = (Button) findViewById(R.id.button_register);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                register(v);
            }
        });

    }

    public void register(View v) {
        final EditText name = findViewById(R.id.editTextTextPersonName);
        final EditText email = findViewById(R.id.editTextTextEmailAddress);
        final EditText phone = findViewById(R.id.editTextPhone);
        final EditText password = findViewById(R.id.editTextTextPassword);
        final CheckBox checkBox = findViewById(R.id.checkBox);
        if (!checkBox.isChecked()) {
            Customer c = new Customer(email.getText().toString(), name.getText().toString(), phone.getText().toString());
            RegisterRepository.getInstance().register(c, password.toString());
            Log.d("WHAT", c.getEmail());
        } else {

        }
    }
}
