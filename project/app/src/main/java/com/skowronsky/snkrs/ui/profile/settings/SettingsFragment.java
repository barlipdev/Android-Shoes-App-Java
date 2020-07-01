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
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.skowronsky.snkrs.R;
import com.skowronsky.snkrs.auth.StartActivity;
import com.skowronsky.snkrs.databinding.FragmentSettingsBinding;

public class SettingsFragment extends Fragment {

    private SettingsViewModel viewModel;
    private FragmentSettingsBinding binding;


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        viewModel = new ViewModelProvider(this).get(SettingsViewModel.class);
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_settings, container, false);
        binding.setSettingsViewModel(viewModel);
        binding.setLifecycleOwner(this);

        final LiveData<Boolean> logout = viewModel.getEventLogout();
        logout.observe(getViewLifecycleOwner(), nav -> {
            if(nav){
                Intent intent = new Intent(getActivity(), StartActivity.class);
                startActivity(intent);
                getActivity().finish();
                viewModel.logout();
                viewModel.eventLogoutFinished();
            }
        });

        final LiveData<Boolean> save = viewModel.getEventSave();
        save.observe(getViewLifecycleOwner(), update -> {
            if (update){
                viewModel.updateUserData();
                viewModel.eventSaveFinished();
            }
        });

        return binding.getRoot();
    }

    /**
     * Metoda odpowiadająca za nawigację do odpowiedniego fragmentu,
     * w tym przypadku do fragmentu profilu uźytkownika
     */
    private void navigateToProfile(){
        NavHostFragment.findNavController(this).navigate(R.id.action_settingsFragment_to_navigation_profile);
    }


}
