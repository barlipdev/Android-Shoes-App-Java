package com.skowronsky.snkrs.ui.profile.settings;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.skowronsky.snkrs.SnkrsClient;
import com.skowronsky.snkrs.model.User;
import com.skowronsky.snkrs.model.UserManager;
import com.skowronsky.snkrs.storage.Storage;

public class SettingsViewModel extends AndroidViewModel {
    private Storage storage = Storage.getInstance();
    private SnkrsClient snkrsClient;

    private MutableLiveData<Boolean> eventNavToProfile;
    private MutableLiveData<Boolean> eventLogout;
    private MutableLiveData<Boolean> eventSave;
    public MutableLiveData<String> email = new MutableLiveData<>();
    public MutableLiveData<String> username = new MutableLiveData<>();
    public MutableLiveData<String> password = new MutableLiveData<>();


    public SettingsViewModel(@NonNull Application application) {
        super(application);
        snkrsClient = SnkrsClient.getInstance(storage,application);
        email.setValue(storage.getUser().getEmail());
        username.setValue(storage.getUser().getName());
        password.setValue(storage.getUser().getPassword());
    }

    public void updateUserData(){
        if(password.getValue().length() > 0 && username.getValue().length() > 0){
            Log.i("ServerUp","n: "+username);
            Log.i("ServerUp","e: "+email);
            Log.i("ServerUp","p: "+password);
            snkrsClient.updateUser(email.getValue(),password.getValue(),username.getValue());
            storage.setUser(new User(email.getValue(),username.getValue(),password.getValue(),"photo"));
        }
    }

    public MutableLiveData<Boolean> getEventLogout(){
        if(eventLogout == null)
            eventLogout = new MutableLiveData<>();
        return eventLogout;
    }

    public MutableLiveData<Boolean> getEventNavToProfile(){
        if(eventNavToProfile == null)
            eventNavToProfile = new MutableLiveData<>();
        return eventNavToProfile;
    }

    public MutableLiveData<Boolean> getEventSave(){
        if(eventSave == null)
            eventSave = new MutableLiveData<>();
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
