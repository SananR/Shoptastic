package me.shoptastic.app.data.model;

import android.graphics.Bitmap;

import java.util.HashSet;

public class Store {
    private final String store_name;
    HashSet<Product> store_products;
    String address;
    Bitmap logo;

    public Store(String name, String address, Bitmap logo, HashSet<Product> products) {
        store_name = name;
        for (Product s : products) {
            store_products.add(s);
        }
        this.address = address;
        this.logo = logo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Store store = (Store) o;
        return store_name.equals(store.store_name) && store_products==store.store_products;
    }

    @Override
    public int hashCode() {
        return store_name.hashCode()+store_products.hashCode();
    }

    public String getStore_name() {
        return store_name;
    }

    public String getAddress(){return address;}
}
