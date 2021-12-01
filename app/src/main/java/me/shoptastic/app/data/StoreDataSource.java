package me.shoptastic.app.data;


import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import me.shoptastic.app.data.model.Store;

public class StoreDataSource {

    //Reading from the database. This is just basic implementation. Will need further changes.
    DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Stores");
    ValueEventListener listener = new ValueEventListener() {


        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            Log.i("demo", "data changed");
            for (DataSnapshot child : dataSnapshot.getChildren()) {
                Store store = child.getValue(Store.class);
                Log.i("demo", store.toString());
            }
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {
            Log.w("warning", "loadPost:onCancelled", databaseError.toException());
        }
    };


}
