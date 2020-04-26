package com.skowronsky.snkrs.ui.profile.settings;

import android.content.Intent;
import android.os.Bundle;
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

import com.skowronsky.snkrs.MainActivity;
import com.skowronsky.snkrs.MyApplication;
import com.skowronsky.snkrs.R;
import com.skowronsky.snkrs.auth.StartActivity;
import com.skowronsky.snkrs.databinding.FragmentSettingsBinding;
import com.skowronsky.snkrs.model.UserManager;
import com.skowronsky.snkrs.storage.Storage;

public class SettingsFragment extends Fragment {

    private SettingsViewModel viewModel;
    private FragmentSettingsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        viewModel = new ViewModelProvider(this).get(SettingsViewModel.class);
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_settings, container, false);
        binding.setSettingsViewModel(viewModel);
        binding.setLifecycleOwner(this);

        final LiveData<Boolean> natToProfile = viewModel.getEventNavToProfile();
        natToProfile.observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean nav) {
                if(nav) {
                    navigateToProfile();
                    viewModel.eventNavToProfileFinished();
                }
            }
        });

        final LiveData<Boolean> logout = viewModel.getEventLogout();
        logout.observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if(aBoolean){
                    Intent intent = new Intent(getActivity(), StartActivity.class);
                    startActivity(intent);
                    getActivity().finish();
                    viewModel.eventLogoutFinished();
                }
            }
        });

        return binding.getRoot();
    }
    private void navigateToProfile(){
        NavHostFragment.findNavController(this).navigate(R.id.action_settingsFragment_to_navigation_profile);
    }


}
