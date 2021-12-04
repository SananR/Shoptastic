package me.shoptastic.app.data.model;

import android.graphics.Bitmap;


public class Product {
    private String product_name;
    private Integer Id;
    private Integer Price;
    private String description;
    private int numberInCart;


    public Product(String name, String description, Integer Price, Integer Id) {
        product_name = name;
        this.description = description;
        this.Id = Id;
        this.Price = Price;
    }

    public Integer getId(){
        return this.Id;
    }

    public Integer getPrice(){
        return this.Price;
    }

    public int getNumberInCart(){
        return this.numberInCart;
    }

    public void changePrice(int Price){ this.Price = Price;}
    public void changeDescription(String description){ this.description = description;}
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

