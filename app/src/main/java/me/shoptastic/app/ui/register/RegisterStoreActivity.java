package me.shoptastic.app.ui.register;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import me.shoptastic.app.R;

public class RegisterStoreActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_register_owner);
    }

    public void register(View v) {
        final EditText name = findViewById(R.id.editTextStoreName);
        final EditText address = findViewById(R.id.editTextStoreAddress);
        final Button logo = findViewById(R.id.button4);

    }
}
