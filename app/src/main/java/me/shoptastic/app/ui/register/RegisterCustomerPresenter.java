package me.shoptastic.app.ui.register;

import android.util.Patterns;

import androidx.lifecycle.ViewModel;

import java.util.Arrays;
import java.util.Locale;

import me.shoptastic.app.R;
import me.shoptastic.app.data.LoginRepository;
import me.shoptastic.app.data.RegisterRepository;
import me.shoptastic.app.data.Result;
import me.shoptastic.app.data.model.Customer;
import me.shoptastic.app.data.model.Resources;
import me.shoptastic.app.data.model.User;

public class RegisterCustomerPresenter extends ViewModel {

    private final RegisterRepository registerRepository;
    private final LoginRepository loginRepository;

    RegisterCustomerPresenter(LoginRepository loginRepository, RegisterRepository registerRepository) {
        this.loginRepository = loginRepository;
        this.registerRepository = registerRepository;
    }

    public Result<User> register(String name, String email, String phone, String password) {
        // Validate items.


        // can be launched in a separate asynchronous job
        Result<User> result = registerRepository.register(new Customer(email, name, phone), password);

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
    private Result<Boolean> validatePassword(String password) {
        Integer password_min_length = Resources.getInteger(R.integer.password_min_length);
        String[] special_characters = Resources.getStringArray(R.array.special_symbols);
        if (password.length() < R.integer.password_min_length) return new
                Result.Error(new IllegalArgumentException("Invalid password"),
                String.format(Locale.CANADA, "Password must be longer than %d",
                        password_min_length));
        if (!Arrays.stream(special_characters).anyMatch(password::contains)) return new
                Result.Error(new IllegalArgumentException("Invalid password"),
                String.format(Locale.CANADA, "Password must contain one of %s",
                        String.join(" ", special_characters)));

        return new Result.Success<Boolean>(true);

    }
}