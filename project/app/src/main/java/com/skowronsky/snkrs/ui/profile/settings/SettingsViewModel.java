package com.skowronsky.snkrs.ui.profile.settings;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.skowronsky.snkrs.model.UserManager;

public class SettingsViewModel extends ViewModel {
    public UserManager userManager;
    public String username = "null";
    public String email = "email";

    private MutableLiveData<Boolean> eventNavToProfile;


    public SettingsViewModel(UserManager userManager){
        this.userManager = userManager;
        username = userManager.getUsername();
        email = userManager.getEmail();
    }

    public MutableLiveData<Boolean> getEventNavToProfile(){
        if(eventNavToProfile == null)
            eventNavToProfile = new MutableLiveData<Boolean>();
        return eventNavToProfile;
    }

    public void eventNavToProfile(){
        eventNavToProfile.setValue(true);
    }
    public void eventNavToProfileFinished(){
        eventNavToProfile.setValue(false);
    }


}
