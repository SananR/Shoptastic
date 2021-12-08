package me.shoptastic.app.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import me.shoptastic.app.R;
import me.shoptastic.app.adapter.EditProductAdapter;
import me.shoptastic.app.data.firebase.UserRepository;
import me.shoptastic.app.data.model.StoreOwner;

public class StoreProductsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sproducts);

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

    public String getStoreName() {
        return getIntent().getStringExtra(ProductsActivity.productStore);
    }

    private void addProductView() {
        Intent i = new Intent(this, ProductAddActivity.class);
        startActivity(i);
    }

    private void recyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        RecyclerView recyclerViewList = findViewById(R.id.recyclerView);
        recyclerViewList.setLayoutManager(linearLayoutManager);

        EditProductAdapter adapter = new EditProductAdapter(this, getStoreName());
        recyclerViewList.setAdapter(adapter);
    }

}