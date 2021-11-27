package me.shoptastic.app.ui.register;

import android.arch.lifecycle.ViewModel;
import android.graphics.Bitmap;

import me.shoptastic.app.data.LoginRepository;
import me.shoptastic.app.data.RegisterRepository;
import me.shoptastic.app.data.Result;
import me.shoptastic.app.data.model.StoreOwner;
import me.shoptastic.app.data.model.User;

public class RegisterStoreOwnerViewModel extends ViewModel {
    private final RegisterRepository registerRepository;
    private final LoginRepository loginRepository;

    RegisterStoreOwnerViewModel(LoginRepository loginRepository, RegisterRepository registerRepository) {
        this.loginRepository = loginRepository;
        this.registerRepository = registerRepository;
    }


    public Result<User> register(String email, String name, String address, Bitmap Image) {
        // can be launched in a separate asynchronous job
        Result<User> result = registerRepository.register(null);

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
