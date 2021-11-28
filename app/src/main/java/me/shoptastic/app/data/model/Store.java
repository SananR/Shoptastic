package me.shoptastic.app.data.model;

import java.util.HashSet;
import java.util.Objects;

public class Store {
    private String store_name;
    HashSet<Product> store_products;

    public Store(String name, HashSet<Product> products){
        store_name = name;
        for(Product s: products){
            store_products.add(s);
        }
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
}
