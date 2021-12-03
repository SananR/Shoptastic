package me.shoptastic.app.data.register;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ProductDataSource {
    private final FirebaseAuth fAuth;
    private final DatabaseReference dRef;

    public ProductDataSource() {
        fAuth = FirebaseAuth.getInstance();
        dRef = FirebaseDatabase.getInstance().getReference("Hi");
    }
    public void addtodatabase(){
        dRef.setValue("Hey!");
    };
}
