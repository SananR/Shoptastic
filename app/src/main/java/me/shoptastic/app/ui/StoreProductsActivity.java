package me.shoptastic.app.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import me.shoptastic.app.R;
import me.shoptastic.app.adapter.ProductAdapter;
import me.shoptastic.app.data.model.Product;

public class StoreProductsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sproducts);

        recyclerView();
    }

    public String getStoreName() {
        return getIntent().getStringExtra(Products.productStore);
    }

    private void recyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        RecyclerView recyclerViewList = findViewById(R.id.recyclerView);
        recyclerViewList.setLayoutManager(linearLayoutManager);

        ArrayList<Product> products = new ArrayList<>();
        ProductAdapter adapter = new ProductAdapter(null, products);
        recyclerViewList.setAdapter(adapter);
    }

}