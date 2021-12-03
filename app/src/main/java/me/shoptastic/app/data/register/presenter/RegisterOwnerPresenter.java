package me.shoptastic.app.data.register.presenter;

import me.shoptastic.app.data.LoginRepository;
import me.shoptastic.app.data.Result;
import me.shoptastic.app.data.model.Store;
import me.shoptastic.app.data.model.StoreOwner;
import me.shoptastic.app.data.model.User;
import me.shoptastic.app.data.register.RegisterRepository;
import me.shoptastic.app.ui.register.RegisterStoreActivity;

public class RegisterOwnerPresenter extends RegisterPresenter {

    private final RegisterRepository registerRepository;
    private final LoginRepository loginRepository;
    private final RegisterStoreActivity activity;

    public RegisterOwnerPresenter(RegisterStoreActivity activity) {
        this.loginRepository = LoginRepository.getInstance();
        this.registerRepository = RegisterRepository.getInstance();
        this.activity = activity;
    }

    public boolean validate(String name, String email, String phone, String password) {
        Result[] res = {validateName(name), validateEmail(email), validatePhone(phone),
                validatePassword(password)};
        for (Result result :
                res) {
            if (result instanceof Result.Error) {
                this.activity.showErrorMsg(((Result.Error) result).getError());
                return false;
            }
        }
        return true;
    }

    @Override
    public void register() {
        String name = activity.getName();
        String email = activity.getEmail();
        String phone = activity.getPhone();
        String password = activity.getPassword();
        Store store = activity.getStore();

        if (!validate(name, email, phone, password)) {
            return;
        }
        RegisterStorePresenter p = new RegisterStorePresenter(this.activity);
        if (!p.validate(store.getStore_name(), store.getAddress())) {
            return;
        }

        // can be launched in a separate asynchronous job
        Result result = registerRepository.register(
                new StoreOwner(email, name, phone, store), password);

        if (result instanceof Result.Success) {
            User data = ((Result.Success<User>) result).getData();
            if (data instanceof StoreOwner) {
                loginRepository.setLoggedInUser(data);
            } else {
                throw new RuntimeException("Registered a store owner and got a customer back.");
            }
        } else {
            this.activity.showErrorMsg(((Result.Error) result).getError());
        }
    }
}
