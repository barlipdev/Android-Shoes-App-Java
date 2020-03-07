package com.skowronsky.snkrs.ui.profile.settings;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.skowronsky.snkrs.model.UserManager;

public class SettingViewModelFactory implements ViewModelProvider.Factory{
    private UserManager userManager;

    public SettingViewModelFactory(UserManager userManager){
        this.userManager = userManager;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new SettingsViewModel(userManager);
    }
}
