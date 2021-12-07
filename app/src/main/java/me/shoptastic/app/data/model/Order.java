package me.shoptastic.app.data.model;

import java.util.ArrayList;
import java.util.HashMap;

public class Order {
    private final String storeName;
    private final ArrayList<Product> products;
    private final HashMap<Product, Integer> quantities;
    private boolean done;

    public Order(String storeName) {
        this.storeName = storeName;
        this.products = new ArrayList<>();
        this.quantities = new HashMap<>();
    }

    public void addProduct(Product product) {
        if (quantities.containsKey(product)) {
            quantities.put(product, quantities.get(product) + 1);
        } else {
            products.add(product);
            quantities.put(product, 1);
        }
    }

    public void removeProduct(Product product) {
        if (quantities.containsKey(product)) {
            if (quantities.get(product) == 1) {
                quantities.remove(product);
                products.remove(product);
            } else {
                quantities.put(product, quantities.get(product) - 1);
            }
        }
    }

    public ArrayList<Product> getListCart() {
        //Testing(demo)
        Product p1 = new Product("Pizza", "delicous", 1.0f, 1);
        Product p2 = new Product("Apple", "fresh", 1.0f, 2);
        ArrayList<Product> anOrder = new ArrayList<Product>();
        anOrder.add(p1);
        anOrder.add(p2);
        return anOrder;
    }


    public Float getSumPrice() {
        Float sum = 0f;
        for (Product product :
                products) {
            sum += product.getPrice() * quantities.getOrDefault(product, 0);
        }
        return sum;
    }


    public Product get(int position) {
        return products.get(position);
    }

    public Integer getQuantity(Product product) {
        return quantities.getOrDefault(product, 0);
    }

    public Integer size() {
        return products.size();
    }

    public Integer totalQuantity() {
        Integer sum = 0;
        for (Integer i :
                quantities.values()) {
            sum += i;
        }
        return sum;
    }

    public String getStoreName() {
        return storeName;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
}
