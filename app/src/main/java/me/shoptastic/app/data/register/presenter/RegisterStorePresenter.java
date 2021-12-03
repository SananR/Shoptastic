package me.shoptastic.app.data.register.presenter;

import java.util.HashSet;

import me.shoptastic.app.data.Result;
import me.shoptastic.app.data.StoreRepository;
import me.shoptastic.app.data.model.Product;
import me.shoptastic.app.data.model.Store;
import me.shoptastic.app.ui.register.RegisterStoreActivity;

public class RegisterStorePresenter {

    private final StoreRepository storeRepository;
    private final RegisterStoreActivity activity;

    public RegisterStorePresenter(RegisterStoreActivity activity) {
        this.storeRepository = StoreRepository.getInstance();
        this.activity = activity;
    }

    private Result validateStoreName(String storeName) {
        return new Result.Success<>(true);
    }

    private Result validateStoreLocation(String location) {
        return new Result.Success<>(true);
    }

    public boolean validate(String storeName, String location) {
        Result[] results = {validateStoreName(storeName), validateStoreLocation(location)};
        for (Result result :
                results) {
            if (result instanceof Result.Error) {
                activity.showErrorMsg(((Result.Error) result).getError());
                return false;
            }
        }
        return true;
    }

    public void register() {
        String name = activity.getStoreName();
        String location = activity.getLocation();
        if (!validate(name, location)) {
            return;
        }
        // TODO Update UI
        // can be launched in a separate asynchronous job
        Store store = new Store(name, location, new HashSet<Product>());
        Result result = storeRepository.register(store);
        if (result instanceof Result.Error) {
            activity.showErrorMsg(((Result.Error) result).getError());
        }
    }

}
