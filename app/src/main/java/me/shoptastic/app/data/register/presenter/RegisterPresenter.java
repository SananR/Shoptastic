package me.shoptastic.app.data.register.presenter;

import android.util.Patterns;

import java.util.Arrays;
import java.util.Locale;

import me.shoptastic.app.R;
import me.shoptastic.app.data.Result;
import me.shoptastic.app.data.model.Resources;

public abstract class RegisterPresenter {

    public abstract void register();

    /**
     * Validates username
     */
    protected Result validateName(String name) {
        return new Result.Success<>(true);
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
