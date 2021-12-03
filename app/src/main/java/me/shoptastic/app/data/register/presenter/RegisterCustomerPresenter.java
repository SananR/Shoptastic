package me.shoptastic.app.data.register.presenter;

import me.shoptastic.app.data.LoginRepository;
import me.shoptastic.app.data.register.RegisterRepository;
import me.shoptastic.app.data.Result;
import me.shoptastic.app.data.model.Customer;
import me.shoptastic.app.data.model.User;

public class RegisterCustomerPresenter extends RegisterPresenter {

    private final RegisterRepository registerRepository;
    private final LoginRepository loginRepository;

    public RegisterCustomerPresenter() {
        this.registerRepository = RegisterRepository.getInstance();
        this.loginRepository = LoginRepository.getInstance();
    }

    @Override
    public Result<User> register(String name, String email, String phone, String password) {
        // TODO Update UI
        if (!validateName(name)) {


        }
        if (!validateEmail(email)) {

        }
        if (!validatePhone(phone)) {

        }
        if (validatePassword(password) instanceof Result.Error) {

        }

        // can be launched in a separate asynchronous job
        Result<User> result = registerRepository.register(new Customer(email, name, phone), password);

        if (result instanceof Result.Success) {
            User data = ((Result.Success<User>) result).getData();
            loginRepository.setLoggedInUser(data);
        }
        return result;
    }

}