package com.skowronsky.snkrs.auth.login;

import android.content.Intent;
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

import com.skowronsky.snkrs.MainActivity;
import com.skowronsky.snkrs.R;
import com.skowronsky.snkrs.databinding.FragmentLoginBinding;

public class LoginFragment extends Fragment {

    private LoginViewModel viewModel;
    private FragmentLoginBinding binding;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        viewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_login,container,false);
        binding.setLoginViewModel(viewModel);
        binding.setLifecycleOwner(this);

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
                        viewModel.login();
                        Intent intent = new Intent(getActivity(), MainActivity.class);
                        startActivity(intent);
                        getActivity().finish();
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
