package com.skowronsky.snkrs.ui.profile.settings;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.skowronsky.snkrs.model.UserManager;

public class SettingsViewModel extends ViewModel {
    public UserManager userManager;
    public String username = "null";

    private MutableLiveData<Boolean> eventNavToProfile;

    public String siema = "siema";

    public SettingsViewModel(UserManager userManager){
        this.userManager = userManager;
        username = userManager.getUsername();
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
