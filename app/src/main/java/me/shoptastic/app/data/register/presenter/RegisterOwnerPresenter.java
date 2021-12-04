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

public class RegisterOwnerPresenter extends RegisterPresenter {

    private final RegisterRepository registerRepository;
    private final LoginRepository loginRepository;
    private final OwnerRegisterActivity view;

    public RegisterOwnerPresenter(OwnerRegisterActivity view) {
        this.loginRepository = LoginRepository.getInstance();
        this.registerRepository = RegisterRepository.getInstance();
        this.view = view;
    }

    public boolean validateInput() {
        boolean errorName = false, errorAddress = false;
        if (!validateStoreName(view)) errorName = true;
        if (!validateAddress(view)) errorAddress = true;
        view.error(errorName, errorAddress);
        return !(errorName || errorAddress);
    }


    public void register() {
        String name = activity.getName();
        String email = activity.getEmail();
        String phone = activity.getPhone();
        String password = activity.getPassword();
        Store store = activity.getStore();

        if (!p.validateStoreInput(store.getStore_name(), store.getAddress())) {
            return;
        }
        // can be launched in a separate asynchronous job
        Result result = registerRepository.register(
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
    public boolean validateStoreName(String name) {
        return true;
    }

    /**
     * Validates store address
     */
    public boolean validateAddress(String address) {
        return return address.length() >= 5;
    }

}
