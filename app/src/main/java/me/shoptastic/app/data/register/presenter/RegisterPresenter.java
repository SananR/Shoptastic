package me.shoptastic.app.data.register.presenter;

import android.util.Patterns;

import java.util.Arrays;
import java.util.Locale;

import me.shoptastic.app.R;
import me.shoptastic.app.data.Result;
import me.shoptastic.app.data.model.Resources;
import me.shoptastic.app.data.model.User;

public abstract class RegisterPresenter {

    public abstract Result<User> register(String name, String email, String phone, String password);

    /**
     * Validates username
     */
    protected boolean validateName(String name) {
        return true;
    }

    /**
     * validates user email
     */
    protected boolean validateEmail(String email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    /**
     * validates phone
     */
    protected boolean validatePhone(String phone) {
        return Patterns.PHONE.matcher(phone).matches();
    }

    /**
     * validates password
     */
    protected Result<Boolean> validatePassword(String password) {
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
