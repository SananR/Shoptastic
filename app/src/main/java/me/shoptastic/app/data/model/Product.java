package me.shoptastic.app.data.model;

import android.graphics.Bitmap;


public class Product {
    private String product_name;
    Integer Id;
    Integer Price;
    private String description;
    //Bitmap image;
    private int numberInCart;

    public Product(String name, String description, Integer Price, Integer Id) {
        product_name = name;
        this.description = description;
        //this.image = image;
        this.Id = Id;
        this.Price = Price;
    }

    public Product(String name, String description, Integer Id, Integer Price, int numberInCart){
        this.product_name = name;
        this.description = description;
        this.Id = Id;
        this.Price = Price;
        this.numberInCart = numberInCart;
    }

    public String getName(){
        return product_name;
    }

    public int getNumberInCart(){
        return numberInCart;
    }

    public void setName(String name){
        this.product_name = name;
    }

    public Integer getPrice(){
        return Price;
    }

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

