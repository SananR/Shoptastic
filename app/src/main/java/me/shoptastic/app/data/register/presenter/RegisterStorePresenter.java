package me.shoptastic.app.data.register.presenter;
import android.widget.EditText;

import java.util.HashSet;

import me.shoptastic.app.R;
import me.shoptastic.app.data.Result;
import me.shoptastic.app.data.StoreRepository;
import me.shoptastic.app.data.model.Product;
import me.shoptastic.app.data.model.StoreOwner;
import me.shoptastic.app.data.model.User;
import me.shoptastic.app.data.model.Store;
import me.shoptastic.app.ui.register.RegisterActivity;
import me.shoptastic.app.ui.register.RegisterStoreActivity;

public class RegisterStorePresenter extends RegisterPresenter {

    private final StoreRepository storeRepository;

    public RegisterStorePresenter() {
        this.storeRepository = StoreRepository.getInstance();
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

        // can be launched in a separate asynchronous job
        HashSet<Product> p = new HashSet<Product>();
        Store store = new Store(name, RegisterStoreActivity.storeLocation, p);
        Result<User> result = storeRepository.register(new StoreOwner(RegisterStoreActivity.storeOwnerEmail, RegisterStoreActivity.storeOwnerName, phone, store), store, password);
        return result;
    }

}
