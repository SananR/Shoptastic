package me.shoptastic.app;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

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

    private void recyclerView(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewList = findViewById(R.id.recyclerView);
        recyclerViewList.setLayoutManager(linearLayoutManager);

        ArrayList<Product> products = new ArrayList<>();
        //demo
        products.add(new Product("", "", 1,1));


    }

}