package me.shoptastic.app.data.model;

import java.util.ArrayList;

public class StoreOwner extends User {
    private Store store;

    public StoreOwner(String email, String displayName, String phone, Store store) {
        super(email, displayName, phone);
        this.store = store;
    }

    @Override
    public ArrayList<Product> getOrder() {
        return null;
    }

    public Store getStore() {
        if (store == null) {
            // Get store
            store = null;
        }
        return store;
    }

}
