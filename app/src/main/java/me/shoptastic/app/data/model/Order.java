package me.shoptastic.app.data.model;

import java.util.HashSet;
import java.util.Iterator;

public class Order {
    HashSet<Product> Items;

    public Order(HashSet<Product> orderedItems){
        this.Items = orderedItems;
    }

    public boolean checkStatus(HashSet<Product> Items){
        return false;
    }

    public int getSumPrice(){
        int orderPrice = 0;
        Iterator iter = Items.iterator();
        while(iter.hasNext()){
            Product p = (Product)iter.next();
            orderPrice += p.Price;
        }
        return orderPrice;
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
