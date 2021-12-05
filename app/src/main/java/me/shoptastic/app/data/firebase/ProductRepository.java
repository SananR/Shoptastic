package me.shoptastic.app.data.firebase;

import me.shoptastic.app.data.model.Product;
import me.shoptastic.app.data.model.Result;

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


    public Result addtodatabase(Product p, String Store_Name) {
        // handle register
        return dataSource.addtodatabase(p, Store_Name);
    }
}

