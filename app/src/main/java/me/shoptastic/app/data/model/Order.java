package me.shoptastic.app.data.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

public class Order {

    public void insertProduct(Product product){
        ArrayList<Product> listProduct = getListCart();
        boolean existAlready = false;
        int n = 0;
        for(int i = 0; i < listProduct.size(); i ++){
            if(listProduct.get(i).getId() == (product.getId())){
                existAlready = true;
                n = i;
                break;
            }
        }
        if(!existAlready){
            listProduct.add(product);
        }
    }

    public ArrayList<Product> getListCart(){
        //Testing(demo)
        Product p1 = new Product("Pizza", "delicous", 1, 1);
        Product p2 = new Product("Apple", "fresh", 1,2);
        ArrayList<Product> anOrder = new ArrayList<Product>();
        anOrder.add(p1);
        anOrder.add(p2);
        return anOrder;
    }
/*
    public void plusNumberFood(ArrayList<Product> listProduct, int position, ){

    }
    */


    public int getSumPrice(){
        ArrayList<Product> listProduct2 = getListCart();
        int fee = 0;
        for(int i = 0; i < listProduct2.size(); i ++){
            fee = fee + (listProduct2.get(i).getPrice() * listProduct2.get(i).getNumberInCart());
        }
        return fee;
    }



    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(o == null || this.getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return this.getSumPrice() == order.getSumPrice();
    }

    @Override
    public int hashCode(){
        return getSumPrice();
    }
}
