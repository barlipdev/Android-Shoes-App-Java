package com.skowronsky.snkrs.auth.signup;

import android.app.Application;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.skowronsky.snkrs.MainActivity;

import java.util.concurrent.Executor;

public class SignupViewModel extends AndroidViewModel {
    public String title = "Signup Fragment";

    private FirebaseAuth mAuth;

    private MutableLiveData<Boolean> eventSignup;
    private MutableLiveData<Boolean> eventNavToLogin;
    public MutableLiveData<String> password = new MutableLiveData<>();
    public MutableLiveData<String> email = new MutableLiveData<>();
    public MutableLiveData<String> username = new MutableLiveData<>();

    public SignupViewModel(@NonNull Application application) {
        super(application);
        mAuth = FirebaseAuth.getInstance();
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
        if (email.getValue() != null && email.getValue().length() > 0 && password.getValue() != null
                && password.getValue().length() > 0 && username.getValue() != null && username.getValue().length() > 0) {
            mAuth.createUserWithEmailAndPassword(email.getValue(), password.getValue())
                    .addOnCompleteListener((Executor) this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d("Firebase", "createUserWithEmail:success");
                                FirebaseUser user = mAuth.getCurrentUser();

                                Intent intent = new Intent(getApplication(), MainActivity.class);
                                getApplication().startActivity(intent);

                            } else {
                                // If sign in fails, display a message to the user.
                               // Log.w(Firebase, "createUserWithEmail:failure", task.getException());
                                email.setValue("");
                                password.setValue("");
                                username.setValue("");
                            }


                        }
                    });
        }
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
