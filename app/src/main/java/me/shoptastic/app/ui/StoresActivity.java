package me.shoptastic.app.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import me.shoptastic.app.R;
import me.shoptastic.app.adapter.StoresAdapter;
import me.shoptastic.app.data.firebase.UserRepository;
import me.shoptastic.app.data.model.StoreOwner;
import me.shoptastic.app.data.model.User;
import me.shoptastic.app.data.firebase.StoreRepository;
import me.shoptastic.app.data.model.Store;

public class StoresActivity extends Activity implements StoresAdapter.StoreClickListener {

    private static final String NAME = "me.Shoptastic.app.NAME";
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stores);

        this.recyclerView = findViewById(R.id.storeRecyclerView);

        StoresAdapter adapter = new StoresAdapter(this, this);
        this.recyclerView.setAdapter(adapter);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public void cart(View v){
        // this button is for both store owner and user, as store owner is able to oder too
        User user = UserRepository.getInstance().getUser();
        //EditText editText = (EditText) findViewById(R.id.editTextTextPersonName);

        if (user instanceof StoreOwner) {
            Intent s_intent = new Intent(this, SProducts.class);
            startActivity(s_intent);
        }else{
            Intent c_intent = new Intent(this, Cart.class);
            startActivity(c_intent);
        }


    }

    public void account_b(View v) {
        // this button is for both store owner and user
        User user = UserRepository.getInstance().getUser();
        //EditText editText = (EditText) findViewById(R.id.editTextTextPersonName);
        Intent intent = new Intent(this, Account.class);
        String name = user.getDisplayName();
        intent.putExtra(NAME, name);
        startActivity(intent);
    }

    public void display_store(View v){
        Intent intent = new Intent(this, SProducts.class);
        startActivity(intent);
    }

    public void list_of_orders(View v){
        Intent intent = new Intent(this, ListOfOrders.class);
        startActivity(intent);
    }

    @Override
    public void onStoreClick(int position) {
        Store s = StoreRepository.getInstance().getStores().get(position);
        Intent i = new Intent(this, Products.class);
        i.putExtra(Products.productStore, s.getName());
        i.putExtra("me.shoptastic.app.storeDescription", s.getDescription());
        i.putExtra("me.shoptastic.app.storeAddress", s.getAddress());

        //i.putExtra("me.shoptastic.app.storeProducts", s.getProducts());
        startActivity(i);
    }
  
}