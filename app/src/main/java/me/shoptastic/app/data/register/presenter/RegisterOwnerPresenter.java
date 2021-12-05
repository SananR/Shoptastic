package me.shoptastic.app.data.register.presenter;

import android.graphics.Bitmap;

import androidx.lifecycle.ViewModel;

import java.util.HashSet;

import me.shoptastic.app.OwnerRegisterActivity;
import me.shoptastic.app.data.LoginRepository;
import me.shoptastic.app.data.register.RegisterRepository;
import me.shoptastic.app.data.Result;
import me.shoptastic.app.data.model.Store;
import me.shoptastic.app.data.model.StoreOwner;
import me.shoptastic.app.data.model.User;

public class RegisterOwnerPresenter extends RegisterPresenter {

    private final RegisterRepository repo;
    private final OwnerRegisterActivity view;

    public RegisterOwnerPresenter(OwnerRegisterActivity view) {
        this.view = view;
        this.repo = RegisterRepository.getInstance();
    }

    public Result<User> register(String name, String email, String phone, String password, String storeName, String address, Bitmap image) {
        // can be launched in a separate asynchronous job
        if (validateInput(storeName, address)) {
            Result<User> result = repo.register(
                    new StoreOwner(email, name, phone,
                            new Store(storeName, address, image, new HashSet<>())),
                    password);
            return result;
        } else return new Result.Error(new IllegalArgumentException("Invalid store information"));
    }

    public boolean validateInput(String storeName, String address) {
        boolean errorName = false, errorAddress = false;
        if (!validateName(storeName)) errorName = true;
        if (!validateAddress(address)) errorAddress = true;
        view.error(errorName, errorAddress);
        return !(errorName || errorAddress);
    }

    /**
     * Validates store address
     */
    public boolean validateAddress(String address) {
        return address.length() >= 5;
    }

}
