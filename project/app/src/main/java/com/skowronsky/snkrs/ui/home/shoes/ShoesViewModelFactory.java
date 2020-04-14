package com.skowronsky.snkrs.ui.home.shoes;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.skowronsky.snkrs.storage.Storage;

public class ShoesViewModelFactory implements ViewModelProvider.Factory{
    Storage storage;
    String company;

    public ShoesViewModelFactory(Storage storage,String company){
        this.storage = storage;
        this.company = company;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T)new ShoesViewModel(storage,company);
    }
}
