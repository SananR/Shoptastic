package me.shoptastic.app;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import me.shoptastic.app.data.model.Resources;
import me.shoptastic.app.ui.register.RegisterActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Resources.setContext(this);
        Intent i = new Intent(this, RegisterActivity.class);
        startActivity(i);
    }

}