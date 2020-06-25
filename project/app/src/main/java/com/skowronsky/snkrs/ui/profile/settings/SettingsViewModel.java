package com.skowronsky.snkrs.ui.profile.settings;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.auth.FirebaseAuth;
import com.skowronsky.snkrs.repository.Repository;

public class SettingsViewModel extends AndroidViewModel {


    private Repository repository;

    private FirebaseAuth mAuth;

    private MutableLiveData<Boolean> eventLogout;
    private MutableLiveData<Boolean> eventSave;

    public MutableLiveData<String> email = new MutableLiveData<>();
    public MutableLiveData<String> username = new MutableLiveData<>();
    public MutableLiveData<String> password = new MutableLiveData<>();


    public SettingsViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository(application);

        mAuth = FirebaseAuth.getInstance();

        email.setValue(mAuth.getCurrentUser().getEmail());
        username.setValue("2");
        password.setValue("password");
    }

    /**
     * Metoda odpowiadająca za usunięcie z bazy lokalnej ulubionych butów i baz butów podczas wylogowywania
     */
    //TODO logout method
    public void logout(){
        mAuth.signOut();
    }

    /**
     * Metoda odpowiadająca za udpate informacji o użytkowniku
     */

    //TODO update user data
    public void updateUserData(){
        if(password.getValue().length() > 0 && username.getValue().length() > 0){
//            snkrsClient.updateUser(email.getValue(),password.getValue(),username.getValue());
//            storage.setUser(new User(email.getValue(),username.getValue(),password.getValue(),"photo"));
        }
    }

    public MutableLiveData<Boolean> getEventLogout(){
        if(eventLogout == null)
            eventLogout = new MutableLiveData<>();
        return eventLogout;
    }


    public MutableLiveData<Boolean> getEventSave(){
        if(eventSave == null)
            eventSave = new MutableLiveData<>();
        return eventSave;
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
