package com.skowronsky.snkrs.ui.profile.settings;

import android.app.Application;

import androidx.lifecycle.ViewModel;

import com.skowronsky.snkrs.model.User;
import com.skowronsky.snkrs.model.UserManager;

public class SettingsViewModel extends ViewModel {
    public UserManager userManager;
    public String username = "null";

    public SettingsViewModel(UserManager userManager){
        this.userManager = userManager;
        username = userManager.getUsername();
    }

}
