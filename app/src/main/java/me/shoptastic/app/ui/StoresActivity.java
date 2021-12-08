package me.shoptastic.app.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import me.shoptastic.app.R;
import me.shoptastic.app.adapter.StoresAdapter;
import me.shoptastic.app.data.firebase.UserRepository;
import me.shoptastic.app.data.model.StoreOwner;
import me.shoptastic.app.data.model.User;
import me.shoptastic.app.data.firebase.StoreRepository;
import me.shoptastic.app.data.model.Store;

public class StoresActivity extends Activity implements StoresAdapter.StoreClickListener {

    private static final String NAME = "me.Shoptastic.app.NAME";
    private RecyclerView recyclerView;

    private BottomNavigationView.OnNavigationItemSelectedListener navListener;

    private StoresActivity instance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        instance=this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stores);

        this.recyclerView = findViewById(R.id.storeRecyclerView);

        StoresAdapter adapter = new StoresAdapter(this, this);
        this.recyclerView.setAdapter(adapter);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(this));

        setupNavigation();
    }

    private void setupNavigation() {
        this.navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.nav_account:
                        accountView();
                        return true;
                    case R.id.nav_cart:
                        cartView();
                        return true;
                    case 1:
                        Intent i = new Intent(instance, ProductsActivity.class);
                        StoreOwner s = (StoreOwner)UserRepository.getInstance().getUser();
                        i.putExtra(ProductsActivity.productStore, s.getStore().getName());
                        startActivity(i);
                        return true;
                }
                return false;
            }
        };
        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);

        if (UserRepository.getInstance().getUser() instanceof StoreOwner) {
            bottomNav.getMenu().add(Menu.NONE, 1, Menu.NONE, "My Store").setIcon(R.drawable.ic_menu_store);
        }
        bottomNav.setItemIconTintList(null);
        bottomNav.setOnNavigationItemSelectedListener(this.navListener);
    }

    public void cartView(){
        // this button is for both store owner and user, as store owner is able to oder too
        User user = UserRepository.getInstance().getUser();

        if (user instanceof StoreOwner) {
            Intent s_intent = new Intent(this, ProductsActivity.class);
            startActivity(s_intent);
        }else{
            Intent c_intent = new Intent(this, Cart.class);
            startActivity(c_intent);
        }


    }

    public void accountView() {
        // this button is for both store owner and user
        User user = UserRepository.getInstance().getUser();
        Intent intent = new Intent(this, Account.class);
        String name = user.getDisplayName();
        intent.putExtra(NAME, name);
        startActivity(intent);
    }

    public void display_store(View v){
        Intent intent = new Intent(this, ProductsActivity.class);
        startActivity(intent);
    }

    public void list_of_orders(View v){
        Intent intent = new Intent(this, ListOfOrders.class);
        startActivity(intent);
    }

    @Override
    public void onStoreClick(int position) {
        Store s = StoreRepository.getInstance().getStores().get(position);
        Intent i = new Intent(this, ProductsActivity.class);
        i.putExtra(ProductsActivity.productStore, s.getName());
        i.putExtra("me.shoptastic.app.storeDescription", s.getDescription());
        i.putExtra("me.shoptastic.app.storeAddress", s.getAddress());

        startActivity(i);
    }
  
}