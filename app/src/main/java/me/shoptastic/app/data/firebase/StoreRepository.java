package me.shoptastic.app.data.firebase;

import me.shoptastic.app.data.firebase.ProductDataSource;
import me.shoptastic.app.data.model.Product;
import me.shoptastic.app.data.model.Result;
import me.shoptastic.app.data.model.Product;
import me.shoptastic.app.data.model.Store;

import java.util.HashMap;
import java.util.HashSet;


public class StoreRepository {
    private static volatile StoreRepository instance;
    private final StoreDataSource dataSource;
    private HashSet<Store> stores = new HashSet<Store>();

    // private constructor : singleton access
    private StoreRepository() {
        this.dataSource = new StoreDataSource();
        dataSource.getStores();
    }

    public static StoreRepository getInstance() {
        if (instance == null) {
            instance = new StoreRepository();
        }
        return instance;
    }


    public Result addtodatabase(Store store) {
        // handle register
        return dataSource.addtodatabase(store);
    }



    public HashSet<Store> getStores(String store){
        return stores;
    }


    public void addStore(Store store){
        stores.add(store);
    }

    public void removeStore(Store s){
        stores.remove(s);
    }


}