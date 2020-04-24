package com.skowronsky.snkrs.ui.profile.settings;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.skowronsky.snkrs.model.UserManager;
import com.skowronsky.snkrs.storage.Storage;

public class SettingViewModelFactory implements ViewModelProvider.Factory{
    private Storage storage;

    public SettingViewModelFactory(Storage storage){
        this.storage = storage;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new SettingsViewModel(storage);
    }
}
