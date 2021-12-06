package me.shoptastic.app.data.firebase;


import java.util.HashMap;
import java.util.HashSet;

import me.shoptastic.app.data.model.Product;
import me.shoptastic.app.data.model.Result;


public class ProductRepository {
    private static volatile ProductRepository instance;
    private final ProductDataSource dataSource;
    public HashMap<String, HashSet<Product>> products = new HashMap<>();

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

    public HashSet<Product> getProducts(String store){
        if(!products.containsKey(store)){
            retrieveProducts(store);
        }
        return products.get(store);
    }

    private void retrieveProducts(String store){
        dataSource.retrieve(store);
    }

    public void addProduct(Product product, String store){
        HashSet<Product> prods = products.get(store);
        if (prods == null){
            prods = new HashSet<>();
        }
        prods.add(product);
    }

    public void removeProduct(Product product, String store){
        HashSet<Product> prods = products.get(store);
        if (prods == null){
            prods = new HashSet<>();
        }
        prods.remove(product);
    }

}


