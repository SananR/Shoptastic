package me.shoptastic.app.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import me.shoptastic.app.R;
import me.shoptastic.app.adapter.ProductAdapter;
import me.shoptastic.app.data.firebase.ProductRepository;
import me.shoptastic.app.data.firebase.UserRepository;
import me.shoptastic.app.data.model.StoreOwner;

public class ProductsActivity extends AppCompatActivity {

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

        if (UserRepository.getInstance().getUser() instanceof StoreOwner) {
            Button b = findViewById(R.id.addProductButton);
            b.setVisibility(View.VISIBLE);
            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    addProductView();
                }
            });
        }

        recyclerView();
    }

    private void addProductView() {
        Intent i = new Intent(this, ProductAddActivity.class);
        startActivity(i);
    }

    public String getStoreName() {
        return getIntent().getStringExtra(ProductsActivity.productStore);
    }

    private void recyclerView() {
        RecyclerView recyclerViewList = findViewById(R.id.productsRecylerView);
        recyclerViewList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        ProductAdapter adapter = new ProductAdapter(this, new ArrayList<>(ProductRepository.getInstance().getProducts(getStoreName())));
        recyclerViewList.setAdapter(adapter);


    }
}