package com.skowronsky.snkrs.ui.profile.settings;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.skowronsky.snkrs.model.UserManager;
import com.skowronsky.snkrs.storage.Storage;

public class SettingsViewModel extends ViewModel {
    public Storage storage;
    public String username = "null";
    public String email = "email";

    private MutableLiveData<Boolean> eventNavToProfile;


    public SettingsViewModel(Storage storage){
        this.storage = storage;
        username = storage.getUser().getName();
        email = storage.getUser().getEmail();
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
