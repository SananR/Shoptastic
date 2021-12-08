package me.shoptastic.app.data.firebase;


import java.util.ArrayList;
import java.util.HashMap;

import me.shoptastic.app.Interface.Callback;
import me.shoptastic.app.data.model.Product;
import me.shoptastic.app.data.model.Result;


public class ProductRepository {
    private static volatile ProductRepository instance;
    private final ProductDataSource dataSource;
    public HashMap<String, ArrayList<Product>> products = new HashMap<>();

    // private constructor : singleton access
    private ProductRepository() {
        this.dataSource = new ProductDataSource();
    }

    public static ProductRepository getInstance() {
        if (instance == null) {
            instance = new ProductRepository();
        }
        return instance;
    }


    public Result addtodatabase(Product p, String Store_Name) {
        // handle register
        return dataSource.addtodatabase(p, Store_Name);
    }

    public ArrayList<Product> getProducts(String store, Callback callback) {
        if (!products.containsKey(store)) {
            products.put(store, new ArrayList<>());
            retrieveProducts(store, callback);
        }
        return products.get(store);
    }

    private void retrieveProducts(String store, Callback callback) {
        dataSource.retrieve(store, callback);
    }

    public void addProduct(Product product, String store) {
        ArrayList<Product> prods = products.get(store);
        if (prods == null) {
            prods = new ArrayList<>();
            products.put(store, prods);
        }
        if (!prods.contains(product)) {
            prods.add(product);
        }
    }

    public void removeProduct(Product product, String store){
        ArrayList<Product> prods = products.get(store);
        if (prods == null) {
            prods = new ArrayList<>();
            products.put(store, prods);
        }
        prods.remove(product);
    }
}


