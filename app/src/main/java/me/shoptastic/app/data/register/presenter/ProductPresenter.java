package me.shoptastic.app.data.register.presenter;
import android.widget.EditText;

import java.util.HashSet;

import me.shoptastic.app.R;
import me.shoptastic.app.data.Result;
import me.shoptastic.app.data.model.ProductRepository;
import me.shoptastic.app.data.model.Product;
import me.shoptastic.app.data.model.StoreOwner;
import me.shoptastic.app.data.model.User;
import me.shoptastic.app.data.model.Store;
import me.shoptastic.app.ui.Product.ProductAddActivity;
import me.shoptastic.app.ui.register.RegisterActivity;
import me.shoptastic.app.ui.register.RegisterStoreActivity;

public class ProductPresenter extends RegisterPresenter {
    private final ProductRepository productRepository;

    public ProductPresenter() {
        this.productRepository = ProductRepository.getInstance();
    }



    @Override
    public Result<User> register(String name, String email, String phone, String password) {
        // TODO Update UI
        if (!validateName(name)) {


        }
        if (!validateEmail(email)) {

        }
        if (!validatePhone(phone)) {

        }
        if (validatePassword(password) instanceof Result.Error) {

        }
        Product product = new Product(ProductAddActivity.ProductName, ProductAddActivity.ProductDescription, ProductAddActivity.ProductPrice, ProductAddActivity.ProductID);
        Result<User> result = productRepository.addtodatabase(product, RegisterStoreActivity.storeOwnerName);
        return result;
    }
}
