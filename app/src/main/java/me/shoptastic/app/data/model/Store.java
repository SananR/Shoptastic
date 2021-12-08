package me.shoptastic.app.data.model;

import com.google.firebase.database.Exclude;

import java.util.ArrayList;

import me.shoptastic.app.Interface.Callback;
import me.shoptastic.app.data.firebase.ProductRepository;

public class Store {
    private final String name;
    private String description;
    String address;

    public Store(String name, String address, String description) {
        this.name = name;
        this.address = address;
        this.description = description;
    }

    public Store() {
        name = "";
    }

    public String getName() {
        return name;
    }

    @Exclude
    public ArrayList<Product> getProducts() {
        return ProductRepository.getInstance().getProducts(this.name, new Callback() {
            @Override
            public void callback() {
            }
        });
    }

    public String getAddress() {
        return address;
    }

    public String getDescription() { return description; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Store store = (Store) o;
        if ((name == null) ? (store.getName() != null) : !name.equals(store.getName())) return false;
        return name.equals(store.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

}