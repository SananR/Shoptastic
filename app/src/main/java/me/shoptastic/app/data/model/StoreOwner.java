package me.shoptastic.app.data.model;

public class StoreOwner extends User {

    private Store store;

    public StoreOwner(String email, String displayName, String phone, String password, Store store) {
        super(email, displayName, phone, password);
        this.store = store;
    }

    public StoreOwner() {
        super();
    }

    public Store getStore() {
        return store;
    }

}
