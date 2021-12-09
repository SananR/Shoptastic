package me.shoptastic.app.data.firebase;

import java.util.ArrayList;

import me.shoptastic.app.Interface.Callback;
import me.shoptastic.app.data.model.Order;
import me.shoptastic.app.data.model.Product;
import me.shoptastic.app.data.model.Result;

public class CartRepository {
    private static CartRepository instance;
    private final CartDataSource dataSource;
    private Order order;

    // private constructor : singleton access
    private CartRepository() {
        this.dataSource = new CartDataSource();
    }

    public static CartRepository getInstance() {
        if (instance == null) {
            instance = new CartRepository();
        }
        instance.getCustomerOrder();
        return instance;
    }


    public Result addOrder(Order o) {
        // handle register
        if (!(order == null)){
            return new Result.Error(new IllegalStateException("Order already exists"),
                    "Cannot have an order when one already exists");
        }
        order = o;
        dataSource.changeOrder(o);
        return new Result.Success<>(order);
    }

    public Result addProduct(Product p) {
        if (order == null) {
            addOrder(new Order(p.getStoreName()));
        }
        order.addProduct(p);
        dataSource.changeOrder(order);
        return new Result.Success<>(p);
    }

    public void checkoutOrder() {
        order.setCheckout(true);
        dataSource.changeOrder(order);
    }

    public void pickupOrder() {
        order.setPickup(true);
        dataSource.changeOrder(order);
    }

    public void getCustomerOrder() {
        if (order == null) {
            dataSource.getCustomerOrder();
        }
    }

    public void getStoreOrders(String storename, ArrayList<Order> orders, Callback callback) {
        dataSource.getStoreOrders(storename, orders, callback);
    }


    public Result removeProduct(Product product) {
        order.removeProduct(product);
        dataSource.changeOrder(order);
        return new Result.Success<>(product);
    }

    public Order getOrder() {
        return order;
    }
}
