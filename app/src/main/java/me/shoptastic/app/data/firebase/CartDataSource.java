package me.shoptastic.app.data.firebase;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

import me.shoptastic.app.Interface.Callback;
import me.shoptastic.app.data.model.Order;
import me.shoptastic.app.data.model.Resources;

public class CartDataSource {
    public static final String ordersKey = "orders";
    private final FirebaseAuth fAuth;
    private final DatabaseReference dRef;
    private HashMap<String, ChildEventListener> listeners;

    public CartDataSource() {
        fAuth = FirebaseAuth.getInstance();
        dRef = FirebaseDatabase.getInstance(Resources.FireBaseLink).getReference();
    }


    public void changeOrder(Order o) {
        dRef.child(ordersKey).child(o.getStoreName()).child(
                UserRepository.getInstance().getUser().getUUID()
        ).setValue(o);
    }

    public void getCustomerOrder() {
        dRef.child(ordersKey).orderByKey().equalTo(UserRepository.getInstance().getUser().getUUID()).
                addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()){
                            CartRepository.getInstance().addOrder(snapshot.getValue(Order.class));
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
    }

    public void getStoreOrders(String storename, ArrayList<Order> orders, Callback callback) {
        dRef.child(ordersKey).child(storename).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful()) {

                    DataSnapshot snap = task.getResult();

                    if (snap.exists()) {
                        for (DataSnapshot child : snap.getChildren()) {
                            Order order = child.getValue(Order.class);
                            orders.remove(order);
                            orders.add(order);
                            callback.callback();
                        }
                    }

                } else throw new RuntimeException(task.getException());
            }
        });/*
        dRef.child(ordersKey).child(storename).orderByValue().
                addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            Order order = snapshot.getValue(Order.class);
                            orders.remove(order);
                            orders.add(order);
                            callback.callback();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });*/
    }

}
