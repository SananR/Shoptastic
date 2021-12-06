package me.shoptastic.app.data.model;

import java.util.HashSet;

public class Store {
    private final String storeName;
    HashSet<Product> storeProducts;
    String address;

    public Store(String name, String address, HashSet<Product> products) {
        storeName = name;
        for (Product s : products) {
            storeProducts.add(s);
        }
        this.address = address;
    }

    public String getName() {
        return storeName;
    }

    public HashSet<Product> getProducts() {
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
        return storeName.hashCode()+storeProducts.hashCode();
    }

}
