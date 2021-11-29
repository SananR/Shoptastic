package me.shoptastic.app;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import me.shoptastic.app.data.model.Customer;
import me.shoptastic.app.data.model.User;

public class Account extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
    }

    // This function is called when clicked acc details
    public void account_details(View v){
        User user = null;
        if (user instanceof Customer){

        }else{

        }
    }
}