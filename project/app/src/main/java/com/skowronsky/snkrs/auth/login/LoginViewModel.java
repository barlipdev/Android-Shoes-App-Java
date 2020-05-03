package com.skowronsky.snkrs.auth.login;

import android.app.Application;
import android.content.Intent;
import android.os.SystemClock;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.skowronsky.snkrs.MainActivity;
import com.skowronsky.snkrs.SnkrsClient;
import com.skowronsky.snkrs.storage.Storage;

public class LoginViewModel extends AndroidViewModel {
    private MutableLiveData<Boolean> eventLogin;
    private MutableLiveData<Boolean> eventNavToSignup;
    public String password = "";
    public String email = "";

    private Storage storage = Storage.getInstance();
    private SnkrsClient snkrsClient;

    public String title = "LoginFragment";

    public LoginViewModel(@NonNull Application application) {
        super(application);
        snkrsClient = SnkrsClient.getInstance(storage,application);
    }

    public void login(){
        Log.i("ServerUp",email);
        Log.i("ServerUp",password);

        if(email.length()>0 && password.length() > 0){
            snkrsClient.auth(email,password);
        }
        loginFinished();
    }

    public MutableLiveData<Boolean> getEventLogin(){
        if(eventLogin == null)
            eventLogin = new MutableLiveData<Boolean>();
        return eventLogin;
    }
    public void setLogin(){
        eventLogin.setValue(true);
    }
    public void loginFinished(){
        eventLogin.setValue(false);
    }

    public MutableLiveData<Boolean> getNavToSignup(){
        if(eventNavToSignup == null)
            eventNavToSignup = new MutableLiveData<Boolean>();
        return eventNavToSignup;
    }

    public void setEventNavToSignup(){
        eventNavToSignup.setValue(true);
    }
    public void eventNavToSignupFinished(){
        eventNavToSignup.setValue(false);
    }
}
