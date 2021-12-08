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

    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerViewList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sproducts);

        recyclerView();
    }

//    private void bottomNavigation(){
//        FloatingActionButton floatingActionButton = findViewById(R.id.cart_btn);
//        LinearLayout homeBtn = findViewById(R.id.homeBtn);
//
//        floatingActionButton.setOnClickListener((new View.OnClickListener() {
//            @Override
//            public void onClick(View v){
//                startActivity(new Intent(SProducts.this, Cart.class));
//            }
//        }));
//
//        homeBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v){
//                startActivity(new Intent(SProducts.this, Cart.class));
//            }
//        });
//    }

    private void recyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewList = findViewById(R.id.recyclerView);
        recyclerViewList.setLayoutManager(linearLayoutManager);

        ArrayList<Product> products = new ArrayList<>();
        //demo
        /*products.add(new Product("Apple", "Good", 2f, 87));
        products.add(new Product("Pineapple", "Fresh", 1f, 28));
        products.add(new Product("Phone", "New", 374f, 28));
        products.add(new Product("Onion", "Veggies", 47f, 28));
        products.add(new Product("Notes", "Useful", 21f, 28));*/
        //adapter = new ProductAdapter(products);
        //recyclerViewList.setAdapter(adapter);


    }

}