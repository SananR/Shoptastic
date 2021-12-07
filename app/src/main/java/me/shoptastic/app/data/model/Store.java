package me.shoptastic.app.data.model;

import java.util.ArrayList;

public class Store {

    private String name;
    private String description;
    private ArrayList<Product> products;
    private String address;

    public Store(String name, String address, String description, ArrayList<Product> products) {
        this.name = name;
        this.products = products;
        this.address = address;
        this.description = description;
    }

    public Store() {};

    public String getName() {
        return name;
    }

    public ArrayList<Product> getProducts() {
        return products;
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
        return name.equals(store.name) && products == store.products;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

}
