package me.shoptastic.app.data;

import me.shoptastic.app.data.model.Store;
import me.shoptastic.app.data.model.StoreOwner;
import me.shoptastic.app.data.model.User;

public class StoreRepository {
    private static volatile StoreRepository instance;
    private StoreDataSource dataSource;

    public static StoreRepository getInstance() {
        if (instance == null) {
            instance = new StoreRepository();
        }
        return instance;
    }

    public Result<User> register(StoreOwner storeOwner, Store store, String password) {
        // handle register
        return dataSource.register(storeOwner, store, password);
    }
}
