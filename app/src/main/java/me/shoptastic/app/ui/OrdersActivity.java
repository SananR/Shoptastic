package me.shoptastic.app.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

import me.shoptastic.app.R;
import me.shoptastic.app.adapter.OrdersAdapter;
import me.shoptastic.app.data.firebase.CartRepository;
import me.shoptastic.app.data.firebase.UserRepository;
import me.shoptastic.app.data.model.Order;
import me.shoptastic.app.data.model.Product;
import me.shoptastic.app.data.model.StoreOwner;

public class OrdersActivity extends AppCompatActivity implements OrdersAdapter.OrderClickListener {

    private RecyclerView recyclerView;
    private ArrayList<Order> orders = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);

        this.recyclerView = findViewById(R.id.ordersRecylerView);

        OrdersAdapter adapter = new OrdersAdapter(this, this, ((StoreOwner)UserRepository.getInstance().getUser()).getStore(), orders);
        this.recyclerView.setAdapter(adapter);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onOrderClick(int position) {
        Order order = orders.get(position);
        Intent i = new Intent(this, OrderDetailsActivity.class);
        ArrayList<String> details = new ArrayList<>();

        for (Product p : order.getProducts()) {
            details.add(p.getName() + " x" + order.getQuantity(p));
        }
        i.putExtra("user", order.getUser());
        i.putStringArrayListExtra("details", details);
        startActivity(i);
    }
}