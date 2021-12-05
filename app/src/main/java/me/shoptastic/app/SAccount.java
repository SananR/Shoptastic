package me.shoptastic.app;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SAccount extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saccount);

        // Get the intend that started this activity
        Intent intent = getIntent();
        String d_name = intent.getStringExtra(Account.NAME);
        String d_email = intent.getStringExtra(Account.EMAIL);
        String d_phn = intent.getStringExtra(Account.PHN_NUMBER);
        String d_address = intent.getStringExtra(Account.ADDRESS);
        String d_store_name = intent.getStringExtra(Account.STORE_NAME);

        // Capture the layout's TextView
        TextView name = findViewById(R.id.sacc_name);
        TextView email = findViewById(R.id.sacc_email);
        TextView phone_number = findViewById(R.id.sacc_phn_number);
        TextView store_name = findViewById(R.id.store_name);
        TextView address = findViewById(R.id.address);

        // Set the string as its text
        name.setText(d_name);
        email.setText(d_email);
        phone_number.setText(d_phn);
        store_name.setText(d_store_name);
        address.setText(d_address);
    }
}