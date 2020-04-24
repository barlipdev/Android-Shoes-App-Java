package com.skowronsky.snkrs.auth.signup;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SignupViewModel extends ViewModel {
    public String title = "Signup Fragment";

    private MutableLiveData<Boolean> eventSignup;
    private MutableLiveData<Boolean> eventNavToLogin;


    public MutableLiveData<Boolean> getEventSignup(){
        if(eventSignup == null)
            eventSignup = new MutableLiveData<Boolean>();
        return eventSignup;
    }
    public void signup(){
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
