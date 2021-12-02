package me.shoptastic.app.data.model;

import me.shoptastic.app.data.Result;
import me.shoptastic.app.data.register.RegisterDataSource;
import me.shoptastic.app.data.model.ProductRepository;
import me.shoptastic.app.data.model.Product;

public class ProductRepository {
    private static volatile ProductRepository instance;
    private final ProductDataSource dataSource;


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


    public Result<Product> addtodatabase(Product p, String Store_Name) {
        // handle register
        return dataSource.addtodatabase(p, Store_Name);
    }
}


