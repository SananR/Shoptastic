package me.shoptastic.app.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import me.shoptastic.app.R;
import me.shoptastic.app.data.model.Resources;

public class Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Resources.setContext(this);
        setContentView(R.layout.activity_account);
    }
}
