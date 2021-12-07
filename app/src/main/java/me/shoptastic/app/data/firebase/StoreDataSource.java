package me.shoptastic.app.data.firebase;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.HashSet;

import me.shoptastic.app.data.model.Product;
import me.shoptastic.app.data.model.Resources;
import me.shoptastic.app.data.model.Result;
import me.shoptastic.app.data.model.Store;

public class StoreDataSource {
    private final FirebaseAuth fAuth;
    private final DatabaseReference dRef;
    private ChildEventListener listeners;

    public StoreDataSource() {
        fAuth = FirebaseAuth.getInstance();
        dRef = FirebaseDatabase.getInstance(Resources.FireBaseLink).getReference();
    }
    public Result addtodatabase(Store store){
        dRef.child("Stores").child(store.getName().toString()).setValue(store);
        return null;
    }
    public void getStores(){
        if (listeners==null) {
            ChildEventListener listener = new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                    Store s = snapshot.getValue(Store.class);
                    StoreRepository repository = StoreRepository.getInstance();
                    repository.addStore(s);
                }

                @Override
                public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                    Store s = snapshot.getValue(Store.class);
                    StoreRepository repository = StoreRepository.getInstance();
                    repository.removeStore(s);
                    repository.addStore(s);
                }

                @Override
                public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                    Store s = snapshot.getValue(Store.class);
                    StoreRepository repository = StoreRepository.getInstance();
                    repository.removeStore(s);
                }

                @Override
                public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    System.out.println("The read failed:");
                }
            };
            listeners = listener;
            dRef.child("Stores").addChildEventListener(listener);
        }

    }


}