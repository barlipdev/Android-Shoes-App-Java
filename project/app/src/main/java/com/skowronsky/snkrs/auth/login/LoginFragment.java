package com.skowronsky.snkrs.auth.login;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.skowronsky.snkrs.MainActivity;
import com.skowronsky.snkrs.R;
import com.skowronsky.snkrs.databinding.FragmentLoginBinding;

public class LoginFragment extends Fragment {

    private LoginViewModel viewModel;
    private FragmentLoginBinding binding;
    private FirebaseAuth mAuth;


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        viewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_login,container,false);
        binding.setLoginViewModel(viewModel);
        binding.setLifecycleOwner(this);
        mAuth = FirebaseAuth.getInstance();


        final LiveData<Boolean> navToSignup = viewModel.getNavToSignup();
        navToSignup.observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if(aBoolean){
                    navigateToSignup();
                    viewModel.eventNavToSignupFinished();
                }
            }
        });

        final LiveData<Boolean> login = viewModel.getEventLogin();
        login.observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean){
                    String email = viewModel.email.getValue();
                    String password = viewModel.password.getValue();

                    if(email != null && email.length() > 0 && password != null && password.length() > 0)
                    {
                        mAuth.signInWithEmailAndPassword(email, password)
                                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if (task.isSuccessful()) {
                                            // Sign in success, update UI with the signed-in user's information
                                            Log.d("Firebase", "signInWithEmail:success");
//                                        FirebaseUser user = mAuth.getCurrentUser();
                                            Intent intent = new Intent(getActivity(), MainActivity.class);
                                            startActivity(intent);
                                            getActivity().finish();
                                        } else {
                                            // If sign in fails, display a message to the user.
                                            Log.w("Firebase", "signInWithEmail:failure", task.getException());
                                            Toast.makeText(getActivity(), "Authentication failed.",
                                                    Toast.LENGTH_SHORT).show();
                                            viewModel.email.setValue("");
                                            viewModel.password.setValue("");
                                        }
                                        viewModel.loginFinished();

                                    }
                                });
                    }
                }
            }
        });


        return binding.getRoot();
    }
    /**
     * Metoda odpowiadająca za wykonanie nawigacji do odpowiedniego fragmentu,
     * w tym przypadku do fragmentu zakladania konta
     */
    private void navigateToSignup(){
        NavHostFragment.findNavController(this).navigate(R.id.action_loginFragment_to_signupFragment);
    }
}
