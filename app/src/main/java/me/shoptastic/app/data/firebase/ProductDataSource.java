package me.shoptastic.app.data.firebase;

import androidx.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashSet;

import me.shoptastic.app.data.model.Product;
import me.shoptastic.app.data.model.Resources;
import me.shoptastic.app.data.model.Result;

public class ProductDataSource {
    private final FirebaseAuth fAuth;
    private final DatabaseReference dRef;

    public ProductDataSource() {
        fAuth = FirebaseAuth.getInstance();
        dRef = FirebaseDatabase.getInstance().getReference(Resources.FireBaseLink);
    }
    public Result addtodatabase(Product p, String Store_Name){
        dRef.child(Store_Name).child(p.getId().toString()).setValue(p);
        return null;
    }
    public HashSet<Product> retrieve(){
        final HashSet<Product> a = new HashSet<>();
        dRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Product p = snapshot.getValue(Product.class);
                a.add(p);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.out.println("The read failed:");
            }
        });
                return a;
    }


}
