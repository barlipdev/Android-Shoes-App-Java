package com.skowronsky.snkrs.ui.home;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.skowronsky.snkrs.storage.Storage;

public class HomeViewModelFactory implements ViewModelProvider.Factory {
    private Storage storage;

    public HomeViewModelFactory(Storage storage){
        this.storage = storage;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T)new HomeViewModel(storage);
    }
}
