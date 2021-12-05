package me.shoptastic.app.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import me.shoptastic.app.R;

public class CAccount extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caccount);

        // Get the intend that started this activity
        Intent intent = getIntent();
        String d_name = intent.getStringExtra(Account.NAME);
        String d_email = intent.getStringExtra(Account.EMAIL);
        String d_phn = intent.getStringExtra(Account.PHN_NUMBER);

        // Capture the layout's TextView
        TextView name = findViewById(R.id.name);
        TextView email = findViewById(R.id.email);
        TextView phone_number = findViewById(R.id.phone_number);

        // Set the string as its text
        name.setText(d_name);
        email.setText(d_email);
        phone_number.setText(d_phn);
    }
}