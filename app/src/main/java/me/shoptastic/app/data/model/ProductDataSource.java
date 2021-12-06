package me.shoptastic.app.data.model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import me.shoptastic.app.data.model.Product;
import me.shoptastic.app.data.model.Customer;
import me.shoptastic.app.data.model.Product;
import me.shoptastic.app.data.model.Store;
import me.shoptastic.app.data.model.StoreOwner;
import me.shoptastic.app.data.model.User;


import java.io.IOException;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.HashSet;

import me.shoptastic.app.data.Result;

public class ProductDataSource {
    private final FirebaseAuth fAuth;
    private final DatabaseReference dRef;
    private HashMap<String, ChildEventListener> listeners;

    public ProductDataSource() {
        fAuth = FirebaseAuth.getInstance();
        dRef = FirebaseDatabase.getInstance().getReference(Resources.FireBaseLink);
    }
    public Result<User> addtodatabase(Product p, String Store_Name){
        dRef.child(Store_Name).child(p.ID().toString()).setValue(p);
        return null;
    }
    public void retrieve(String store){
        if(!listeners.containsKey(store)){
            ChildEventListener listener = new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                    Product p = snapshot.getValue(Product.class);
                    ProductRepository repository = ProductRepository.getInstance();
                    repository.addProduct(p,store);
                }

                @Override
                public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                    Product p = snapshot.getValue(Product.class);
                    ProductRepository repository = ProductRepository.getInstance();
                    repository.removeProduct(p, store);
                    repository.addProduct(p,store);
                }

                @Override
                public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                    Product p = snapshot.getValue(Product.class);
                    ProductRepository repository = ProductRepository.getInstance();
                    repository.removeProduct(p, store);
                }

                @Override
                public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    System.out.println("The read has failed");
                }
            };
            listeners.put(store, listener);
            dRef.child(store).addChildEventListener(listener);
        }
    }


}
