package com.skowronsky.snkrs.ui.profile;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.skowronsky.snkrs.MyApplication;
import com.skowronsky.snkrs.R;
import com.skowronsky.snkrs.databinding.FragmentProfileBinding;
import com.skowronsky.snkrs.storage.Storage;

public class ProfileFragment extends Fragment {

    private ProfileViewModel viewModel;
    private FragmentProfileBinding binding;

    private MyApplication appState;
    private Storage storage;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        appState = ((MyApplication)this.getActivity().getApplication());
        storage = appState.storage;
        viewModel = new ViewModelProvider(this,new ProfileViewModelFactory(storage)).get(ProfileViewModel.class);
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container, false);
        binding.setProfileViewModel(viewModel);
        binding.setLifecycleOwner(this);

        final LiveData<Boolean> natToSettings = viewModel.getEventSettingsNav();
        natToSettings.observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if(aBoolean){
                    navigateToSettings();
                    Log.i("MyTag","Dziala");
                    viewModel.eventNavToSettingsFinished();
                }
            }
        });


        return binding.getRoot();
    }

    private void navigateToSettings(){
        NavHostFragment.findNavController(this).navigate(R.id.action_navigation_profile_to_settingsFragment);
    }
}