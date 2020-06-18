package com.skowronsky.snkrs.auth.login;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

public class LoginViewModel extends AndroidViewModel {
    private MutableLiveData<Boolean> eventLogin;
    private MutableLiveData<Boolean> eventNavToSignup;
    public MutableLiveData<String> password = new MutableLiveData<>();
    public MutableLiveData<String> email = new MutableLiveData<>();


    public String title = "LoginFragment";

    public LoginViewModel(@NonNull Application application) {
        super(application);
    }

    /**
     * Metoda odpowiadająca za wykonanie logowania
     */
    public void login(){
//        if(email.getValue().length()>0 && password.getValue().length() > 0){
//            //TODO login
//        }
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
    /**
     * Metoda zwana eventem która ustawia wartość LiveData na true w celu poinformowania obserwatora
     * aby wykonała sie nawigacja do danego fragmentu
     */
    public void setEventNavToSignup(){
        eventNavToSignup.setValue(true);
    }
    /**
     * Metoda zwana eventem która ustawia wartość LiveData na false w celu poinformowania obserwatora
     * że nawigacja do danego fragmentu się zakończyła
     */
    public void eventNavToSignupFinished(){
        eventNavToSignup.setValue(false);
    }
}
