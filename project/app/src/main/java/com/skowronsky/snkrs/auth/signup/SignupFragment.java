package com.skowronsky.snkrs.auth.signup;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.skowronsky.snkrs.R;
import com.skowronsky.snkrs.databinding.FragmentSignupBinding;


public class SignupFragment extends Fragment {

    private SignupViewModel viewModel;
    private FragmentSignupBinding binding;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        viewModel = new ViewModelProvider(this).get(SignupViewModel.class);
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_signup,container,false);
        binding.setSignupViewModel(viewModel);
        binding.setLifecycleOwner(this);

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


        return binding.getRoot();
    }
    private void navigateToLogin(){
        NavHostFragment.findNavController(this).navigate(R.id.action_signupFragment_to_loginFragment);
    }
}