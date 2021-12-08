package me.shoptastic.app.ui;

import android.content.Intent;
import android.os.Bundle;

import me.shoptastic.app.data.firebase.StoreRepository;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StoreRepository.getInstance().setup();
        Intent i = new Intent(this, LoginActivity.class);
        startActivity(i);
    }

}