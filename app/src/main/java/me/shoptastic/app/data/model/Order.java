package me.shoptastic.app.data.model;

import com.google.firebase.database.Exclude;

import java.util.ArrayList;
import java.util.HashMap;

import me.shoptastic.app.data.firebase.UserRepository;

public class Order {

    private final String storeName;
    private final ArrayList<Product> products;
    private final HashMap<String, Integer> quantities;
    private boolean checkout = false;
    private boolean pickup = false;
    private final String user;


    public Order(String storeName) {
        this.storeName = storeName;
        this.products = new ArrayList<>();
        this.quantities = new HashMap<>();
        this.user = UserRepository.getInstance().getUser().getEmail();
    }

    public void addProduct(Product product) {
        if (quantities.containsKey(product.getName())) {
            quantities.put(product.getName(), quantities.get(product.getName()) + 1);
        } else {
            products.add(product);
            quantities.put(product.getName(), 1);
        }
    }

    public void removeProduct(Product product) {
        if (quantities.containsKey(product.getName())) {
            if (quantities.get(product.getName()) == 1) {
                quantities.remove(product.getName());
                products.remove(product);
            } else {
                quantities.put(product.getName(), quantities.get(product.getName()) - 1);
            }
        }
    }


    @Exclude
    public Float getSumPrice() {
        Float sum = 0f;
        for (Product product :
                products) {
            sum += product.getPrice() * quantities.getOrDefault(product.getName(), 0);
        }
        return sum;
    }


    public Product get(int position) {
        return products.get(position);
    }

    @Exclude
    public Integer getQuantity(Product product) {
        return quantities.getOrDefault(product.getName(), 0);
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

    public boolean getCheckout() {
        return checkout;
    }

    public void setCheckout(boolean done) {
        this.checkout = done;
    }

    public boolean getPickup() {
        return pickup;
    }

    public void setPickup(boolean pickup) {
        this.pickup = pickup;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public HashMap<String, Integer> getQuantities() {
        return quantities;
    }

    public int getTotalQuantity() {
        int sum = 0;
        for (Integer i : this.quantities.values()) sum+=i;
        return sum;
    }


    @Override
    public String toString() {
        return "Order{" +
                "storeName='" + storeName + '\'' +
                ", products=" + products +
                ", quantities=" + quantities +
                ", checkout=" + checkout +
                ", pickup=" + pickup +
                '}';
    }

    public String getUser() {
        return user;
    }
}
