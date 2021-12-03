package me.shoptastic.app.data.model;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import me.shoptastic.app.data.Result;

public class ProductDataSource {
    private final FirebaseAuth fAuth;
    private final DatabaseReference dRef;

    public ProductDataSource() {
        fAuth = FirebaseAuth.getInstance();
        dRef = FirebaseDatabase.getInstance().getReference();
    }

    public Result addtodatabase(Product p, String Store_Name) {
        dRef.child(Store_Name).child(p.ID().toString()).setValue(p);
        return null;
    }

}
