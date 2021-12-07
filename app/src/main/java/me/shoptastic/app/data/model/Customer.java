package me.shoptastic.app.data.model;

import java.util.ArrayList;

public class Customer extends User {

    public Customer(String email, String displayName, String phone, String password) {
        super(email, displayName, phone, password);
    }

    public Customer() {
        super();
    }

    @Override
    public ArrayList<Product> getOrder() {
        return this.anOrder;
    }

}
