package me.shoptastic.app.ui.register;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import me.shoptastic.app.R;

public class RegisterCustomerActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_sign_in);
    }

    public void register(View v) {
        final EditText name = findViewById(R.id.editTextTextPersonName);
        final EditText email = findViewById(R.id.editTextTextEmailAddress);
        final EditText phone = findViewById(R.id.editTextPhone);
        final EditText password = findViewById(R.id.editTextTextPassword);
        final Button createButton = findViewById(R.id.button3);
        final CheckBox checkBox = findViewById(R.id.checkBox);
        if (!checkBox.isChecked()) {
        } else {

        }
    }
}
