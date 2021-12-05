package me.shoptastic.app.data.register.presenter;

import me.shoptastic.app.data.Result;
import me.shoptastic.app.data.model.Product;
import me.shoptastic.app.data.model.ProductRepository;
import me.shoptastic.app.data.model.StoreOwner;
import me.shoptastic.app.data.model.User;
import me.shoptastic.app.data.register.UserRepository;
import me.shoptastic.app.ui.ProductAddActivity;

public class ProductPresenter {
    private final ProductRepository productRepository;
    private final ProductAddActivity activity;

    public ProductPresenter(ProductAddActivity activity) {
        this.productRepository = ProductRepository.getInstance();
        this.activity = activity;
    }

    public void register() {
        // TODO Update UI
        Product product = new Product(activity.getProductName(), activity.getProductDescription(),
                activity.getProductPrice(), activity.getProductID());
        User user = UserRepository.getInstance().getUser();
        if (!(user instanceof StoreOwner)) {
            throw new IllegalArgumentException("Customer attempting to put product");
        }
        Result result = productRepository.addtodatabase(product, ((StoreOwner) user).getStore().getName());
    }
}
