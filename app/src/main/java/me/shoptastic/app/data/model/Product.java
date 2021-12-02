package me.shoptastic.app.data.model;

import android.graphics.Bitmap;


public class Product {
    private final String product_name;
    Integer Id;
    Integer Price;
    String description;

    public Product(String name, String description, Integer Price, Integer Id) {
        product_name = name;
        this.description = description;
        this.Id = Id;
        this.Price = Price;
    }
    public Integer ID(Product p){ return p.Id;}
    public Integer price(Product p){ return p.Price;}
    public void changePrice(Product p, int Price){ p.Price = Price;}
    public void changeDescription(Product p, String description){ p.description = description;}
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

