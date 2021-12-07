package me.shoptastic.app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import me.shoptastic.app.Adapter.ProductAdapter;
import me.shoptastic.app.data.model.Product;

public class SProducts extends AppCompatActivity {
    private RecyclerView.Adapter adapter, adapter2;
    private RecyclerView recyclerViewList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sproducts);

        recyclerView();
    }

    private void bottomNavigation(){
        FloatingActionButton floatingActionButton = findViewById(R.id.cart_btn);
        LinearLayout homeBtn = findViewById(R.id.homeBtn);

        floatingActionButton.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v){
                startActivity(new Intent(SProducts.this, Cart.class));
            }
        }));

        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                startActivity(new Intent(SProducts.this, Cart.class));
            }
        });
    }

    private void recyclerView(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewList = findViewById(R.id.recyclerView);
        recyclerViewList.setLayoutManager(linearLayoutManager);

        ArrayList<Product> products = new ArrayList<>();
        //demo
        products.add(new Product("Apple", "Good", 2,87));
        products.add(new Product("Pineapple", "Fresh", 1,28));
        products.add(new Product("Phone", "New", 374,28));
        products.add(new Product("Onion", "Veggies", 47,28));
        products.add(new Product("Notes", "Useful", 21,28));
        adapter = new ProductAdapter(products);
        recyclerViewList.setAdapter(adapter);


    }

}