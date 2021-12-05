package me.shoptastic.app;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import me.shoptastic.app.R;
import me.shoptastic.app.Adapter.ProductAdapter;
import me.shoptastic.app.data.model.Product;

public class SProducts extends Activity {
    private RecyclerView.Adapter adapter, adapter2;
    private RecyclerView recyclerViewList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sproducts);

        recyclerView();
    }

    private void recyclerView(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewList = findViewById(R.id.recyclerview);
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