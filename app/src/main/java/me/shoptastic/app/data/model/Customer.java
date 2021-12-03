package me.shoptastic.app.data.model;

import java.util.ArrayList;

public class Customer extends User {
    public Customer(String email, String displayName, String phone) {
        super(email, displayName, phone);
    }

    @Override
    public ArrayList<Product> getOrder() {
        return null;
    }

}
