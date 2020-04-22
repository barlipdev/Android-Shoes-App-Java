package com.skowronsky.snkrs.ui.base;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.skowronsky.snkrs.storage.Storage;

public class HomeBaseViewModelFactory implements ViewModelProvider.Factory {
    private Storage storage;

    public HomeBaseViewModelFactory(Storage storage){
        this.storage = storage;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T)new HomeBaseViewModel(storage);
    }
}
