package me.shoptastic.app.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import me.shoptastic.app.R;
import me.shoptastic.app.adapter.StoresAdapter;
import me.shoptastic.app.data.firebase.StoreRepository;
import me.shoptastic.app.data.model.Store;

public class StoresActivity extends AppCompatActivity implements StoresAdapter.StoreClickListener {

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

    @Override
    public void onStoreClick(int position) {
        Store s = StoreRepository.getInstance().getStores().get(position);
        Intent i = new Intent(this, Products.class);
        i.putExtra("me.shoptastic.app.storeName", s.getName());
        i.putExtra("me.shoptastic.app.storeDescription", s.getDescription());
        i.putExtra("me.shoptastic.app.storeAddress", s.getAddress());
        //i.putExtra("me.shoptastic.app.storeProducts", s.getProducts());
        startActivity(i);
    }
}