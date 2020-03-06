package com.skowronsky.snkrs.ui.profile.settings;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.skowronsky.snkrs.MyApplication;
import com.skowronsky.snkrs.R;
import com.skowronsky.snkrs.databinding.FragmentSettingsBinding;
import com.skowronsky.snkrs.model.UserManager;

public class SettingsFragment extends Fragment {

    private SettingsViewModel viewModel;
    private FragmentSettingsBinding binding;

    private MyApplication appState;
    private UserManager userManager;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(this).get(SettingsViewModel.class);
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_settings, container, false);
        binding.setSettingsViewModel(viewModel);
        binding.setLifecycleOwner(this);

        appState = ((MyApplication)this.getActivity().getApplication());
        userManager = appState.userManager;

        viewModel.username = userManager.getUsername();

        return binding.getRoot();
    }



}
