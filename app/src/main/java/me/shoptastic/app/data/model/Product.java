package me.shoptastic.app.data.model;

import android.graphics.Bitmap;


public class Product {

    private String product_name;
    private Integer id;
    private Integer price;
    private String description;
    private int numberInCart;


    public Product(String name, String description, Integer Price, Integer Id) {
        product_name = name;
        this.description = description;
        this.id = Id;
        this.price = Price;
    }

    public String getName() { return this.product_name; }

    public Integer getId() { return this.id; }

    public Integer getPrice(){
        return this.price;
    }

    public int getNumberInCart(){
        return this.numberInCart;
    }

    public void setPrice(int Price){ this.price = Price; }

    public void setDescription(String description){ this.description = description; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
       Product product = (Product) o;
        return id.equals(product.id);
    }

    @Override
    public int hashCode() {
        return id;
    }
}

