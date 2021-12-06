package me.shoptastic.app.data.presenter;

import android.content.Intent;

import me.shoptastic.app.R;
import me.shoptastic.app.data.firebase.StoreRepository;
import me.shoptastic.app.data.model.Result;
import me.shoptastic.app.data.model.Store;
import me.shoptastic.app.ui.OwnerRegisterActivity;
import me.shoptastic.app.ui.StoresActivity;

public class RegisterStorePresenter {

    private final StoreRepository storeRepository;
    protected final OwnerRegisterActivity activity;

    public RegisterStorePresenter() {
        activity = null;
        storeRepository = StoreRepository.getInstance();
    }

    public RegisterStorePresenter(OwnerRegisterActivity activity) {
        this.storeRepository = StoreRepository.getInstance();
        this.activity = activity;
    }

    /**
     * Validates store name
     */
    public Result validateStoreName(String name) {
        if (name.length() >= 3) {
            return new Result.Success<>(true);
        } else {
            return new Result.Error(new IllegalArgumentException("Store name too short"), R.string.register_invalid_name);
        }
    }

    /**
     * Validates store address
     */
    public Result validateAddress(String address) {
        if (address.length() >= 5) {
            return new Result.Success<>(true);
        } else {
            return new Result.Error(new IllegalArgumentException("Address too short"), R.string.register_invalid_email);
        }

    }

    public boolean validateInput() {
        String errorName = null, errorAddress = null;
        Result result = validateStoreName(this.activity.getStoreName());
        if (result instanceof Result.Error) errorName = ((Result.Error) result).getError();

        result = validateAddress(this.activity.getAddress());
        if (result instanceof Result.Error) errorAddress = ((Result.Error) result).getError();
        activity.error(errorName, errorAddress);
        return (errorName == null && errorAddress == null);
    }


    public void register() {
        String name = activity.getStoreName();
        String location = activity.getAddress();
        if (!validateInput()) {
            return;
        }


        Store store = new Store(name, location);
        Result result = storeRepository.addtodatabase(store);
        if (result instanceof Result.Error) {
            Intent intent = new Intent(this.activity, StoresActivity.class);
            this.activity.startActivity(intent);
        } else {
            throw new RuntimeException();
        }
    }

}
