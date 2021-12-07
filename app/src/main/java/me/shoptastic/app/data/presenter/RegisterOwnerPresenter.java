package me.shoptastic.app.data.presenter;

import android.content.Intent;

import me.shoptastic.app.R;
import me.shoptastic.app.data.firebase.StoreRepository;
import me.shoptastic.app.data.firebase.UserRepository;
import me.shoptastic.app.data.model.Result;
import me.shoptastic.app.data.model.Store;
import me.shoptastic.app.data.model.StoreOwner;
import me.shoptastic.app.ui.OwnerRegisterActivity;
import me.shoptastic.app.ui.StoresActivity;

public class RegisterOwnerPresenter {

    private final UserRepository userRepository;
    private final StoreRepository storeRepository;
    private final OwnerRegisterActivity view;

    public RegisterOwnerPresenter(OwnerRegisterActivity activity) {
        this.userRepository = UserRepository.getInstance();
        this.storeRepository = StoreRepository.getInstance();
        this.view = activity;
    }

    public RegisterOwnerPresenter(OwnerRegisterActivity activity, UserRepository userRepository, StoreRepository storeRepository) {
        this.view = activity;
        this.userRepository = userRepository;
        this.storeRepository = storeRepository;
    }

    public void register() {
        String name = view.getName();
        String email = view.getEmail();
        String phone = view.getPhone();
        String password = view.getPassword();
        Store store = view.getStore();
        if (validateInput()) {
            userRepository.register(new StoreOwner(email, name, phone, password,
                    store));
            storeRepository.addToDatabase(store);
        }
        Intent i = new Intent(view, StoresActivity.class);
        view.startActivity(i);
    }


    public boolean validateInput() {
        String errorName = null, errorAddress = null;

        Result result = validateStoreName(this.view.getStoreName());
        if (result instanceof Result.Error) errorName = ((Result.Error) result).getError();

        result = validateAddress(this.view.getAddress());
        if (result instanceof Result.Error) errorAddress = ((Result.Error) result).getError();

        view.error(errorName, errorAddress);
        return (errorName == null && errorAddress == null);
    }


    /**
     * Validates store name
     */
    protected Result validateStoreName(String name) {
        if (name.length() >= 3) {
            return new Result.Success<>(true);
        } else {
            return new Result.Error(new IllegalArgumentException("Store name too short"), R.string.register_invalid_name);
        }
    }

    /**
     * Validates address
     */
    protected Result validateAddress(String address) {
        if (address.length() >= 5) {
            return new Result.Success<>(true);
        } else {
            return new Result.Error(new IllegalArgumentException("Address too short"), R.string.register_invalid_address);
        }
    }
}
