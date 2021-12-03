package me.shoptastic.app.data.register.presenter;

import me.shoptastic.app.R;
import me.shoptastic.app.data.LoginRepository;
import me.shoptastic.app.data.model.Resources;
import me.shoptastic.app.data.register.RegisterRepository;
import me.shoptastic.app.data.Result;
import me.shoptastic.app.data.model.Customer;
import me.shoptastic.app.data.model.User;
import me.shoptastic.app.ui.register.RegisterActivity;

public class RegisterCustomerPresenter extends RegisterPresenter {

    private final RegisterRepository registerRepository;
    private final LoginRepository loginRepository;
    private final RegisterActivity view;

    public RegisterCustomerPresenter(RegisterActivity view) {
        this.view = view;
        this.registerRepository = RegisterRepository.getInstance();
        this.loginRepository = LoginRepository.getInstance();
    }

    @Override
    public Result<User> register(String name, String email, String phone, String password) {
        // TODO Update UI
        boolean errorName = false, errorEmail = false, errorPhone = false, errorPassword = false;
        if (!validateName(name)) errorName = true;
        if (!validateEmail(email)) errorEmail = true;
        if (!validatePhone(phone)) errorPhone = true;
        if (validatePassword(password) instanceof Result.Error) errorPassword = true;
        if (errorName || errorEmail || errorPhone || errorPassword) {
            view.error(errorName, errorEmail, errorPhone, errorPassword);
            return new Result.Error(new IllegalArgumentException("Invalid user input"));
        } else view.error(false, false, false, false);

        // can be launched in a separate asynchronous job
        Result<User> result = registerRepository.register(new Customer(email, name, phone), password);

        if (result instanceof Result.Success) {
            User data = ((Result.Success<User>) result).getData();
            loginRepository.setLoggedInUser(data);
        }
        return result;
    }

}