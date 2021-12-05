package me.shoptastic.app.data.model;

import android.graphics.Bitmap;


public class Product {
    private final String product_name;
    String Id;
    String Price;
    String description;

    public Product(String name, String description, String Price, String Id) {
        product_name = name;
        this.description = description;
        this.Id = Id;
        this.Price = Price;
    }
    public String ID(){ return this.Id;}
    public String price(){ return this.Price;}
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
       Product product = (Product) o;
        return Id.equals(product.Id);
    }

    @Override
    public int hashCode() {
        return Integer.parseInt(Id);
    }
}

