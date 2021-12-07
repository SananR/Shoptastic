package me.shoptastic.app.data.presenter;

import android.content.Intent;
import android.util.Log;
import android.util.Patterns;

import java.util.Arrays;
import java.util.Locale;

import me.shoptastic.app.R;
import me.shoptastic.app.data.firebase.UserRepository;
import me.shoptastic.app.data.model.Customer;
import me.shoptastic.app.data.model.Resources;
import me.shoptastic.app.data.model.Result;
import me.shoptastic.app.ui.OwnerRegisterActivity;
import me.shoptastic.app.ui.RegisterActivity;
import me.shoptastic.app.ui.StoresActivity;

public class RegisterCustomerPresenter {

    private RegisterActivity view;
    private UserRepository userRepository;

    public RegisterCustomerPresenter(RegisterActivity view) {
        this.view = view;
        this.userRepository = UserRepository.getInstance();
    }

    public RegisterCustomerPresenter(RegisterActivity v, UserRepository userRepository) {
        this.userRepository = userRepository;
        this.view = v;
    }

    public void validateUserRegister(boolean owner) {
        userRepository.validateUserRegister(this, view.getEmail(), owner);
    }

    public void errorUserExists() {
        this.view.error(null, "Email already in use", null, null);
    }

    public void complete(boolean owner) {
        if (!owner) {
            userRepository.register(new Customer(view.getEmail(), view.getName(), view.getPhone(), view.getPassword()));
            Intent i = new Intent(view, StoresActivity.class);
            view.startActivity(i);
        } else {
            Intent intent = new Intent(view, OwnerRegisterActivity.class);
            intent.putExtra(RegisterActivity.name, view.getName());
            intent.putExtra(RegisterActivity.email, view.getEmail());
            intent.putExtra(RegisterActivity.phone, view.getPhone());
            intent.putExtra(RegisterActivity.password, view.getPassword());
            view.startActivity(intent);
        }
    }

    public boolean validateInput() {
        String errorName = null, errorEmail = null, errorPhone = null, errorPassword = null;

        Result result = validateName(this.view.getName());
        if (result instanceof Result.Error) errorName = ((Result.Error) result).getError();

        result = validateEmail(this.view.getEmail());
        if (result instanceof Result.Error) errorEmail = ((Result.Error) result).getError();

        result = validatePhone(this.view.getPhone());
        if (result instanceof Result.Error) errorPhone = ((Result.Error) result).getError();

        result = validatePassword(this.view.getPassword());
        if (result instanceof Result.Error) errorPassword = ((Result.Error) result).getError();

        view.error(errorName, errorEmail, errorPhone, errorPassword);
        return (errorName == null && errorEmail == null && errorPhone == null && errorPassword == null);
    }

    /**
     * Validates username
     */
    protected Result validateName(String name) {
        if (name.length() >= 3) {
            return new Result.Success<>(true);
        } else {
            return new Result.Error(new IllegalArgumentException("User name too short"), R.string.register_invalid_name);
        }
    }

    /**
     * validates user email
     */
    protected Result validateEmail(String email) {
        if (Patterns.EMAIL_ADDRESS == null) {
            return new Result.Success<>(true);
        }
        if (Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return new Result.Success<>(true);
        } else {
            return new Result.Error(new IllegalArgumentException("Invalid Email"), "Email not valid");
        }
    }

    /**
     * validates phone
     */
    protected Result validatePhone(String phone) {
        if (Patterns.PHONE == null) {
            return new Result.Success<>(true);
        }
        if (Patterns.PHONE.matcher(phone).matches()) {
            return new Result.Success<>(true);
        } else {
            return new Result.Error(new IllegalArgumentException("Invalid phone"), "Phone not valid");
        }
    }

    /**
     * validates password
     */
    protected Result validatePassword(String password) {
        Integer password_min_length;
        String[] special_characters;
        try {
            password_min_length = Resources.getInteger(R.integer.password_min_length);
            special_characters = Resources.getStringArray(R.array.special_symbols);
        } catch (NullPointerException e) {
            password_min_length = 8;
            special_characters = new String[]{"#", "%"};
        }


        if (password.length() < password_min_length) return new
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
