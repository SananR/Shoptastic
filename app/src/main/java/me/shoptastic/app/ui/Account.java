package me.shoptastic.app.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import me.shoptastic.app.R;
import me.shoptastic.app.data.model.StoreOwner;
import me.shoptastic.app.data.model.User;

public class Account extends Activity {

    public static final String NAME = "me.Shoptastic.app.NAME";
    public static final String EMAIL = "me.Shoptastic.app.EMAIL";
    public static final String PHN_NUMBER = "me.Shoptastic.app.PHN_NUMBER";
    public static final String STORE_NAME = "me.Shoptastic.app.STORE_NAME";
    public static final String ADDRESS = "me.Shoptastic.app.ADDRESS";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
    }

    // This function is called when clicked acc details
    public void account_details(View v){
        /*User user = LoginRepository.getInstance().getUser();
        //EditText editText = (EditText) findViewById(R.id.editTextTextPersonName);

        if (user instanceof StoreOwner) {
            Intent s_intent = new Intent(this, SAccount.class);
            String name = user.getDisplayName();
            s_intent.putExtra(NAME, name);
            String email = user.getEmail();
            s_intent.putExtra(EMAIL, email);
            String phone_number = user.getPhone();
            s_intent.putExtra(PHN_NUMBER, phone_number);
            String store_name = ((StoreOwner) user).getStore().getStore_name();
            s_intent.putExtra(STORE_NAME, store_name);
            String address = ((StoreOwner) user).getStore().getAddress();
            s_intent.putExtra(ADDRESS, address);
            startActivity(s_intent);
        }else{
            Intent c_intent = new Intent(this, CAccount.class);
            String name = user.getDisplayName();
            c_intent.putExtra(NAME, name);
            String email = user.getEmail();
            c_intent.putExtra(EMAIL, email);
            String phone_number = user.getPhone();
            c_intent.putExtra(PHN_NUMBER, phone_number);
            startActivity(c_intent);
        }*/

    }
}