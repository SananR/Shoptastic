package me.shoptastic.app.data.model;

import android.graphics.Bitmap;


public class Product {
    private final String product_name;
    Integer Id;
    Integer Price;
    String description;
    Bitmap image;

    public Product(String name, String description, Bitmap image, Integer Price, Integer Id) {
        product_name = name;
        this.description = description;
        this.image = image;
        this.Id = Id;
        this.Price = Price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
       Product product = (Product) o;
        return Id.equals(product.Id);
    }

    @Override
    public int hashCode() {
        return Id;
    }
}

