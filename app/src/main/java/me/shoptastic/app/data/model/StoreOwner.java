package me.shoptastic.app.data.model;

import java.util.ArrayList;

public class StoreOwner extends User{
    private Store store;

    public StoreOwner(String email, String password, String displayName, String phone) {
        super(email, password, displayName, phone);
    }

    @Override
    public ArrayList<Order> getOrders() {
        return null;
    }

    public Store getStore(){
        if (store == null){
            // Get store
            store = null;
        }
        return store;
    }

}
