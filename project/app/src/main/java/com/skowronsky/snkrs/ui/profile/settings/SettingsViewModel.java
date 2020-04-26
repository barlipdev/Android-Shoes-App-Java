package com.skowronsky.snkrs.ui.profile.settings;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.skowronsky.snkrs.model.UserManager;
import com.skowronsky.snkrs.storage.Storage;

public class SettingsViewModel extends ViewModel {
    private Storage storage = Storage.getInstance();
    public String username = "null";
    public String email = "email";

    private MutableLiveData<Boolean> eventNavToProfile;
    private MutableLiveData<Boolean> eventLogout;

    public SettingsViewModel(){
        username = storage.getUser().getName();
        email = storage.getUser().getEmail();
    }

    public void logout(){
        storage.setUser(null);
    }

    public MutableLiveData<Boolean> getEventLogout(){
        if(eventLogout == null)
            eventLogout = new MutableLiveData<Boolean>();
        return eventLogout;
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

    public void setEventLogout(){eventLogout.setValue(true);}
    public void eventLogoutFinished(){eventLogout.setValue(false);}

}
