package com.skowronsky.snkrs.auth.signup;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.skowronsky.snkrs.SnkrsClient;
import com.skowronsky.snkrs.storage.Storage;

public class SignupViewModel extends AndroidViewModel {
    public String title = "Signup Fragment";

    private Storage storage;
    private SnkrsClient snkrsClient;

    private MutableLiveData<Boolean> eventSignup;
    private MutableLiveData<Boolean> eventNavToLogin;
    public MutableLiveData<String> password = new MutableLiveData<>();
    public MutableLiveData<String> email = new MutableLiveData<>();
    public MutableLiveData<String> username = new MutableLiveData<>();

    public SignupViewModel(@NonNull Application application) {
        super(application);
        storage = Storage.getInstance();
        snkrsClient = SnkrsClient.getInstance(storage,application);
    }


    public MutableLiveData<Boolean> getEventSignup(){
        if(eventSignup == null)
            eventSignup = new MutableLiveData<Boolean>();
        return eventSignup;
    }
    public void signup(){
        if(email.getValue().length() > 0 && password.getValue().length()>0 && username.getValue().length() > 0)
            snkrsClient.auth(email.getValue(),password.getValue(),username.getValue());
        signupFinished();
    }
    public void setSignup(){
        eventSignup.setValue(true);
    }
    public void signupFinished(){
        eventSignup.setValue(false);
    }

    public MutableLiveData<Boolean> getNavToLogin(){
        if(eventNavToLogin == null)
            eventNavToLogin = new MutableLiveData<Boolean>();
        return eventNavToLogin;
    }

    public void setEventNavToLogin(){
        eventNavToLogin.setValue(true);
    }
    public void eventNavToLoginFinished(){
        eventNavToLogin.setValue(false);
    }
}
