package me.shoptastic.app.data.model;


public class Customer extends User {

    public Customer(String email, String displayName, String phone, String password) {
        super(email, displayName, phone, password);
    }

    public Customer() {
        super();
    }

}
