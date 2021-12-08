package me.shoptastic.app.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import me.shoptastic.app.R;
import me.shoptastic.app.adapter.CartAdapter;
import me.shoptastic.app.data.firebase.CartRepository;
import me.shoptastic.app.data.model.Order;

public class Cart extends AppCompatActivity {


    private final CartRepository repository = CartRepository.getInstance();
    private final Order order = repository.getOrder();
    private CartAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_cart);
        adapter = new CartAdapter(this);
        extracted();
        calculateCart();

        findViewById(R.id.textView17).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                repository.checkoutOrder();
                adapter.checkout();
            }
        });
    }

    private void extracted() {
        RecyclerView recyclerViewList = findViewById(R.id.recyclerview);
        TextView emptyTxt = findViewById(R.id.emptyTxt);
        ScrollView scrollView = findViewById(R.id.scrollView2);

        recyclerViewList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerViewList.setAdapter(adapter);
        if (order == null || order.size() == 0) {
            emptyTxt.setVisibility(View.VISIBLE);
            scrollView.setVisibility(View.GONE);
        } else {
            emptyTxt.setVisibility(View.GONE);
            scrollView.setVisibility(View.VISIBLE);
        }
    }


    public void calculateCart() {
        TextView totalFeeTxt = findViewById(R.id.totalFeeTxt);
        Float itemTotal = order == null ? 0 : order.getSumPrice();
        totalFeeTxt.setText("$" + itemTotal);
    }

}