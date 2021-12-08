package me.shoptastic.app.data.presenter;

import me.shoptastic.app.data.firebase.ProductRepository;
import me.shoptastic.app.data.firebase.UserRepository;
import me.shoptastic.app.data.model.Product;
import me.shoptastic.app.data.model.Result;
import me.shoptastic.app.data.model.StoreOwner;
import me.shoptastic.app.data.model.User;
import me.shoptastic.app.ui.ProductAddActivity;

public class ProductPresenter {
    private final ProductRepository productRepository;
    private final ProductAddActivity activity;

    public ProductPresenter(ProductAddActivity activity) {
        this.productRepository = ProductRepository.getInstance();
        this.activity = activity;
    }

    public void register() {
        Product product = new Product(activity.getProductName(), activity.getProductDescription(),
                activity.getProductPrice(), activity.getProductID(), activity.getStoreName());
        User user = UserRepository.getInstance().getUser();
        if (!(user instanceof StoreOwner)) {
            throw new IllegalArgumentException("Customer attempting to put product");
        }
        Result result = productRepository.addtodatabase(product, ((StoreOwner) user).getStore().getName());
        if (result instanceof Result.Success) {
            activity.clear();
        } else {
            throw new RuntimeException("Adding product failed");
        }
    }
}
