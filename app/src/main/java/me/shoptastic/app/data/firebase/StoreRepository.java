package me.shoptastic.app.data.firebase;

import java.util.ArrayList;

import me.shoptastic.app.data.model.Result;
import me.shoptastic.app.data.model.Store;

public class StoreRepository {

    private static volatile StoreRepository instance;
    private final StoreDataSource dataSource;
    private final ArrayList<Store> stores = new ArrayList<>();

    // private constructor : singleton access
    private StoreRepository() {
        this.dataSource = new StoreDataSource(this);
    }

    public static StoreRepository getInstance() {
        if (instance == null) {
            instance = new StoreRepository();
        }
        return instance;
    }


    public Result addToDatabase(Store store) {
        // handle register
        return dataSource.addToDatabase(store);
    }

    public ArrayList<Store> getStores(){
        return stores;
    }

    public void addStore(Store s){
        if (!stores.contains(s)) stores.add(s);
    }

    public void removeStore(Store s){
        stores.remove(s);
    }


}