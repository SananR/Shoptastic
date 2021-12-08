package me.shoptastic.app.data.firebase;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

import me.shoptastic.app.data.model.Product;
import me.shoptastic.app.data.model.Resources;
import me.shoptastic.app.data.model.Result;

public class ProductDataSource {
    private final FirebaseAuth fAuth;
    private final DatabaseReference dRef;
    private HashMap<String, ChildEventListener> listeners;
    public static String productsKey;

    public ProductDataSource() {
        fAuth = FirebaseAuth.getInstance();
        dRef = FirebaseDatabase.getInstance().getReference(Resources.FireBaseLink);
    }
    public Result addtodatabase(Product p, String Store_Name){
        dRef.child(productsKey).child(Store_Name).child(p.getId().toString()).setValue(p);
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
                public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) { }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    System.out.println("The read has failed because of errors");
                }
            };
            listeners.put(store, listener);
            dRef.child(productsKey).child(store).addChildEventListener(listener);
        }
    }


}
