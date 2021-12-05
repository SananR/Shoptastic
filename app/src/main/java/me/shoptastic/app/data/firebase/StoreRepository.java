package me.shoptastic.app.data.firebase;

import me.shoptastic.app.data.model.Result;
import me.shoptastic.app.data.model.Store;

public class StoreRepository {
    private static volatile StoreRepository instance;
    private StoreDataSource dataSource;

    public static StoreRepository getInstance() {
        if (instance == null) {
            instance = new StoreRepository();
        }
        return instance;
    }

    public Result register(Store store) {
        // handle register
        return dataSource.register(store);
    }
}
