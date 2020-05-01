package com.skowronsky.snkrs.ui.profile.settings;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.skowronsky.snkrs.SnkrsClient;
import com.skowronsky.snkrs.model.UserManager;
import com.skowronsky.snkrs.storage.Storage;

public class SettingsViewModel extends ViewModel {
    private Storage storage = Storage.getInstance();
    public MutableLiveData<String> username = new MutableLiveData<>("username");
    public MutableLiveData<String> email = new MutableLiveData<>("email");
    public MutableLiveData<String> password = new MutableLiveData<>("********");

    private MutableLiveData<Boolean> eventNavToProfile;
    private MutableLiveData<Boolean> eventLogout;
    private MutableLiveData<Boolean> eventSave;

    public SettingsViewModel(){
        username.setValue(storage.getUser().getName());
        email.setValue(storage.getUser().getEmail());
        password.setValue(storage.getUser().getPassword());
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

    public MutableLiveData<Boolean> getEventSave(){
        if(eventSave == null)
            eventSave = new MutableLiveData<Boolean>();
        return eventSave;
    }

    public void eventNavToProfile(){
        eventNavToProfile.setValue(true);
    }
    public void eventNavToProfileFinished(){
        eventNavToProfile.setValue(false);
    }

    public void setEventLogout(){eventLogout.setValue(true);}
    public void eventLogoutFinished(){eventLogout.setValue(false);}

    public void setEventSave(){
        eventSave.setValue(true);
    }
    public void eventSaveFinished(){
        eventSave.setValue(false);
    }
}
