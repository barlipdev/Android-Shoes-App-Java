package com.skowronsky.snkrs.ui.profile;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.skowronsky.snkrs.R;
import com.skowronsky.snkrs.database.Brand;
import com.skowronsky.snkrs.database.Shoes;
import com.skowronsky.snkrs.databinding.FragmentProfileBinding;

import java.util.List;

public class ProfileFragment extends Fragment {

    private ProfileViewModel viewModel;
    private FragmentProfileBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(this).get(ProfileViewModel.class);
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container, false);
        binding.setProfileViewModel(viewModel);
        binding.setLifecycleOwner(this);

        viewModel.getAllBrands().observe(getViewLifecycleOwner(), new Observer<List<Brand>>() {
            @Override
            public void onChanged(@Nullable final List<Brand> brands) {
                viewModel.showBrandsData(brands);

                Log.i("WWW", String.valueOf(brands.size()));

                for (int i = 0; i < brands.size(); i++) {
                    Log.i("WWW", String.valueOf(brands.get(i).id_brand));
                    Log.i("WWW", String.valueOf(brands.get(i).brand_name));
                }
            }
        });

        viewModel.getAllShoes().observe(getViewLifecycleOwner(), new Observer<List<Shoes>>() {
            @Override
            public void onChanged(List<Shoes> shoes) {
                viewModel.showShoesData(shoes);
            }
        });

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