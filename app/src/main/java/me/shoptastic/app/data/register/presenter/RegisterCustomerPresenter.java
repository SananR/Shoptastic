package me.shoptastic.app.data.register.presenter;

import me.shoptastic.app.data.LoginRepository;
import me.shoptastic.app.data.Result;
import me.shoptastic.app.data.model.Customer;
import me.shoptastic.app.data.model.User;
import me.shoptastic.app.data.register.RegisterRepository;
import me.shoptastic.app.ui.register.RegisterActivity;

public class RegisterCustomerPresenter extends RegisterPresenter {

    private final RegisterRepository registerRepository;
    private final LoginRepository loginRepository;
    private final RegisterActivity v;

    public RegisterCustomerPresenter(RegisterActivity v) {
        this.registerRepository = RegisterRepository.getInstance();
        this.loginRepository = LoginRepository.getInstance();
        this.v = v;
    }

    public RegisterCustomerPresenter(RegisterActivity v, RegisterRepository registerRepository,
                                     LoginRepository loginRepository) {
        this.registerRepository = registerRepository;
        this.loginRepository = loginRepository;
        this.v = v;
    }


    public boolean validate(String name, String email, String phone, String password) {
        Result[] res = {validateName(name), validateEmail(email), validatePhone(phone),
                validatePassword(password)};
        for (Result result :
                res) {
            if (result instanceof Result.Error) {
                this.v.showErrorMsg(((Result.Error) result).getError());
                return false;
            }
        }
        return true;
    }

    @Override
    public void register() {
        // TODO Update UI
        String name = this.v.getName();
        String email = this.v.getEmail();
        String phone = this.v.getPhone();
        String password = this.v.getPassword();
        if (!validate(name, email, phone, password)) {
            return;
        }

        // can be launched in a separate asynchronous job
        Result result = registerRepository.register(new Customer(email, name, phone), password);

        if (result instanceof Result.Success) {
            User data = ((Result.Success<User>) result).getData();
            loginRepository.setLoggedInUser(data);
        } else {
            this.v.showErrorMsg(((Result.Error) result).getError());
        }
    }

}