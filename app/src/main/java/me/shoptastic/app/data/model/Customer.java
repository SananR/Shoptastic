package me.shoptastic.app.data.model;

import java.util.ArrayList;

public class Customer extends User{
    public Customer(String email, String password, String displayName, String phone) {
        super(email, password, displayName, phone);
    }

    @Override
    public ArrayList<Order> getOrders() {
        return null;
    }

}
