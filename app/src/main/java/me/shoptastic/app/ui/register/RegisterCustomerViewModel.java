package me.shoptastic.app.ui.register;

import android.arch.lifecycle.ViewModel;
import android.util.Patterns;

import me.shoptastic.app.data.LoginRepository;
import me.shoptastic.app.data.RegisterRepository;
import me.shoptastic.app.data.Result;
import me.shoptastic.app.data.model.Customer;
import me.shoptastic.app.data.model.User;

public class RegisterCustomerViewModel extends ViewModel {

    private final RegisterRepository registerRepository;
    private final LoginRepository loginRepository;

    RegisterCustomerViewModel(LoginRepository loginRepository, RegisterRepository registerRepository) {
        this.loginRepository = loginRepository;
        this.registerRepository = registerRepository;
    }

    public Result<User> register(String name, String email, String phone, String password) {
        // Validate items.


        // can be launched in a separate asynchronous job
        Result<User> result = registerRepository.register(null);

        if (result instanceof Result.Success) {
            User data = ((Result.Success<User>) result).getData();
            if (data instanceof Customer) {
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
     * Validates username
     */
    private boolean validateName(String name) {
        return true;
    }

    /**
     * validates user email
     */
    private boolean validateEmail(String email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    /**
     * validates phone
     */
    private boolean validatePhone(String phone) {
        return Patterns.PHONE.matcher(phone).matches();
    }

    /**
     * validates password
     */
    private boolean validatePassword(String password) {
        return false;
    }
}