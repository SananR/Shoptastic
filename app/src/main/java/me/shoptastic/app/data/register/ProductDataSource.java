package me.shoptastic.app.data.register;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import me.shoptastic.app.data.model.Resources;

public class ProductDataSource {
    private final FirebaseAuth fAuth;
    private final DatabaseReference dRef;

    public ProductDataSource() {
        fAuth = FirebaseAuth.getInstance();
        dRef = FirebaseDatabase.getInstance(Resources.FireBaseLink).getReference("Hi");
    }
    public void addtodatabase(){
        dRef.setValue("Hey!");
    }
}
