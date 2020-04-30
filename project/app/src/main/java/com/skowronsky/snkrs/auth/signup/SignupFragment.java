package com.skowronsky.snkrs.auth.signup;

import android.content.Intent;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.skowronsky.snkrs.MainActivity;
import com.skowronsky.snkrs.R;
import com.skowronsky.snkrs.SnkrsClient;
import com.skowronsky.snkrs.databinding.FragmentSignupBinding;
import com.skowronsky.snkrs.storage.Storage;


public class SignupFragment extends Fragment {

    private SignupViewModel viewModel;
    private FragmentSignupBinding binding;
    private Storage storage;
    private SnkrsClient snkrsClient;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        viewModel = new ViewModelProvider(this).get(SignupViewModel.class);
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_signup,container,false);
        binding.setSignupViewModel(viewModel);
        binding.setLifecycleOwner(this);

        storage = Storage.getInstance();
        snkrsClient = SnkrsClient.getInstance(storage,getContext());

        final LiveData<Boolean> loginNavigation = viewModel.getNavToLogin();
        loginNavigation.observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean){
                    navigateToLogin();
                    viewModel.eventNavToLoginFinished();
                }
            }
        });

        final LiveData<Boolean> signup = viewModel.getEventSignup();
        signup.observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if(aBoolean){
                    if (binding.username.length() > 0 && binding.email.length() > 0 && binding.password.length() > 0){
                        snkrsClient.auth(binding.email.getText().toString()
                                ,binding.password.getText().toString()
                                ,binding.username.getText().toString());
                        SystemClock.sleep(3000);
                        if(storage.getUser() != null){
                            Intent intent = new Intent(getActivity(), MainActivity.class);
                            startActivity(intent);
                            getActivity().finish();
                        }
                    }
                    viewModel.signupFinished();
                }
            }
        });


        return binding.getRoot();
    }
    private void navigateToLogin(){
        NavHostFragment.findNavController(this).navigate(R.id.action_signupFragment_to_loginFragment);
    }
}
