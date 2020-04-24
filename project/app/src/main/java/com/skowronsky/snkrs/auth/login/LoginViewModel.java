package com.skowronsky.snkrs.auth.login;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class LoginViewModel extends ViewModel {
    private MutableLiveData<Boolean> eventLogin;
    private MutableLiveData<Boolean> eventNavToSignup;

    public String title = "LoginFragment";

    public MutableLiveData<Boolean> getEventLogin(){
        if(eventLogin == null)
            eventLogin = new MutableLiveData<Boolean>();
        return eventLogin;
    }
    public void login(){
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
