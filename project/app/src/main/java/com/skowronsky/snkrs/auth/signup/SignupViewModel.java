package com.skowronsky.snkrs.auth.signup;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

public class SignupViewModel extends AndroidViewModel {
    public String title = "Signup Fragment";


    private MutableLiveData<Boolean> eventSignup;
    private MutableLiveData<Boolean> eventNavToLogin;
    public MutableLiveData<String> password = new MutableLiveData<>();
    public MutableLiveData<String> email = new MutableLiveData<>();
    public MutableLiveData<String> username = new MutableLiveData<>();

    public SignupViewModel(@NonNull Application application) {
        super(application);
    }


    public MutableLiveData<Boolean> getEventSignup(){
        if(eventSignup == null)
            eventSignup = new MutableLiveData<Boolean>();
        return eventSignup;
    }

    /**
     * Metoda odpowiadająca za przeprowadzenie rejestracji konta uzytkownika
     */
    public void signup(){
        if(email.getValue().length() > 0 && password.getValue().length()>0 && username.getValue().length() > 0)
            //TODO signup
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
    /**
     * Metoda zwana eventem która ustawia wartość LiveData na true w celu poinformowania obserwatora
     * aby wykonała sie nawigacja do danego fragmentu
     */
    public void setEventNavToLogin(){
        eventNavToLogin.setValue(true);
    }
    /**
     * Metoda zwana eventem która ustawia wartość LiveData na false w celu poinformowania obserwatora
     * że nawigacja do danego fragmentu się zakończyła
     */
    public void eventNavToLoginFinished(){
        eventNavToLogin.setValue(false);
    }
}
