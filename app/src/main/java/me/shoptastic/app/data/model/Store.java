package me.shoptastic.app.data.model;

import java.util.ArrayList;
import java.util.HashSet;

public class Store {

    private final String storeName;
    ArrayList<Product> storeProducts;
    String address;

    public Store(String name, String address, ArrayList<Product> products) {
        storeName = name;
        this.storeProducts = products;
        this.address = address;
    }

    public String getName() {
        return storeName;
    }

    public ArrayList<Product> getProducts() {
        return storeProducts;
    }

    public String getAddress() {
        return address;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Store store = (Store) o;
        return storeName.equals(store.storeName) && storeProducts == store.storeProducts;
    }

    @Override
    public int hashCode() {
        return storeName.hashCode();
    }

}
