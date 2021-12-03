package me.shoptastic.app.data.register.presenter;

import android.graphics.Bitmap;

import androidx.lifecycle.ViewModel;

import java.util.HashSet;

import me.shoptastic.app.data.LoginRepository;
import me.shoptastic.app.data.register.RegisterRepository;
import me.shoptastic.app.data.Result;
import me.shoptastic.app.data.model.Store;
import me.shoptastic.app.data.model.StoreOwner;
import me.shoptastic.app.data.model.User;

public class RegisterOwnerPresenter {

    private final RegisterRepository registerRepository;
    private final LoginRepository loginRepository;

    public RegisterOwnerPresenter() {
        this.loginRepository = LoginRepository.getInstance();
        this.registerRepository = RegisterRepository.getInstance();
    }


    public Result<User> register(String email, String name, String phone, String password, String storeName, String address, Bitmap Image) {
        // can be launched in a separate asynchronous job
        Result<User> result = registerRepository.register(
                new StoreOwner(email, name, phone,
                        new Store(storeName, address, Image, new HashSet<>())),
                password);

        if (result instanceof Result.Success) {
            User data = ((Result.Success<User>) result).getData();
            if (data instanceof StoreOwner) {
                loginRepository.login(data.getEmail(), "");
                return result;
            } else {
                throw new RuntimeException("Registered a store owner and got a customer back.");
            }
        } else {
            return result;
        }
    }

    /**
     * Validates store name
     */
    public boolean validateName(String name) {
        return true;
    }

    /**
     * Validates store address
     */
    public boolean validateAddress(String address) {
        return true;
    }

}
