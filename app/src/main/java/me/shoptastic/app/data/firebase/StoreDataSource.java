package me.shoptastic.app.data.firebase;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import me.shoptastic.app.data.model.Resources;
import me.shoptastic.app.data.model.Result;
import me.shoptastic.app.data.model.Store;

public class StoreDataSource {

    private final DatabaseReference dRef;
    private ChildEventListener listener;

    public StoreDataSource(StoreRepository repository) {
        dRef = FirebaseDatabase.getInstance(Resources.FireBaseLink).getReference();

        getData(repository);
        attachPersistentListener();
    }


    private void getData(StoreRepository repository) {
        this.dRef.child("stores").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful()) {
                    for (DataSnapshot snap : task.getResult().getChildren()) {
                        Store s = snap.getValue(Store.class);
                        repository.addStore(s);
                    }
                } else throw new RuntimeException(task.getException());
            }
        });
    }

    private void attachPersistentListener() {
        this.listener = new ChildEventListener() {
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
        dRef.child("stores").addChildEventListener(this.listener);
    }

    public Result addToDatabase(Store store){
        dRef.child("stores").child(store.getName()).setValue(store);
        return new Result.Success<>(store);
    }


}