package me.shoptastic.app.data.register.presenter;

import android.content.Intent;

import me.shoptastic.app.data.LoginRepository;
import me.shoptastic.app.data.Result;
import me.shoptastic.app.data.model.Store;
import me.shoptastic.app.data.model.StoreOwner;
import me.shoptastic.app.data.model.User;
import me.shoptastic.app.data.register.RegisterRepository;
import me.shoptastic.app.ui.OwnerRegisterActivity;
import me.shoptastic.app.ui.StoresActivity;

public class RegisterOwnerPresenter extends RegisterStorePresenter {

    private final LoginRepository loginRepository;
    private final RegisterRepository registerRepository;

    public RegisterOwnerPresenter(OwnerRegisterActivity activity) {
        super(activity);
        this.loginRepository = LoginRepository.getInstance();
        this.registerRepository = RegisterRepository.getInstance();
    }

    @Override
    public void register() {
        String name = activity.getName();
        String email = activity.getEmail();
        String phone = activity.getPhone();
        String password = activity.getPassword();
        Store store = activity.getStore();

        if (!validateInput()) {
            return;
        }

        Result result = registerRepository.register(
                new StoreOwner(email, name, phone,
                        store),
                password);

        if (result instanceof Result.Success) {
            User data = ((Result.Success<User>) result).getData();
            if (data instanceof StoreOwner) {
                loginRepository.setLoggedInUser(data);
                Intent intent = new Intent(this.activity, StoresActivity.class);
                this.activity.startActivity(intent);
            } else {
                throw new RuntimeException("Registered a store owner and got a customer back.");
            }
        }
    }


}
