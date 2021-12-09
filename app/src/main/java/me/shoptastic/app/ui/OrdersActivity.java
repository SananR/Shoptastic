package me.shoptastic.app.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import me.shoptastic.app.R;
import me.shoptastic.app.adapter.OrdersAdapter;
import me.shoptastic.app.data.firebase.UserRepository;
import me.shoptastic.app.data.model.StoreOwner;

public class OrdersActivity extends AppCompatActivity implements OrdersAdapter.OrderClickListener {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);

        this.recyclerView = findViewById(R.id.ordersRecylerView);

        OrdersAdapter adapter = new OrdersAdapter(this, this, ((StoreOwner)UserRepository.getInstance().getUser()).getStore());
        this.recyclerView.setAdapter(adapter);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onOrderClick(int position) {

    }


}