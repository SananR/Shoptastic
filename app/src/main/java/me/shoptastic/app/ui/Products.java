package me.shoptastic.app.ui;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import me.shoptastic.app.R;
import me.shoptastic.app.adapter.ProductAdapter;
import me.shoptastic.app.data.firebase.ProductRepository;

public class Products extends AppCompatActivity {

    public static String productName = "me.shoptastic.app.productName";
    public static String productDescription = "me.shoptastic.app.productDescription";
    public static String productPrice = "me.shoptastic.app.productPrice";
    public static String productID = "me.shoptastic.app.productID";
    public static String productStore = "me.shoptastic.app.productStore";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);

        ((TextView)findViewById(R.id.productsTitleTextView)).setText(String.format("%s's Products", getIntent().getStringExtra(productStore)));

        recyclerView();
    }

    public String getStoreName() {
        return getIntent().getStringExtra(Products.productStore);
    }

    private void recyclerView() {
        RecyclerView recyclerViewList = findViewById(R.id.productsRecylerView);
        recyclerViewList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        ProductAdapter adapter = new ProductAdapter(this, new ArrayList<>(ProductRepository.getInstance().getProducts(getStoreName())));
        recyclerViewList.setAdapter(adapter);


    }
}