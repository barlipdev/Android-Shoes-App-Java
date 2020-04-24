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
import com.skowronsky.snkrs.database.Base;
import com.skowronsky.snkrs.database.BaseShoes;
import com.skowronsky.snkrs.database.Brand;
import com.skowronsky.snkrs.database.Favorite;
import com.skowronsky.snkrs.database.FavoriteShoes;
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

        viewModel.getAllBase().observe(getViewLifecycleOwner(), new Observer<List<Base>>() {
            @Override
            public void onChanged(List<Base> bases) {
                for (int i = 0; i < bases.size(); i++) {
                    Log.i("ROOM123",bases.get(i).id_base+ " "+bases.get(i).id_shoes);
                }
            }
        });

        viewModel.getAllBaseShoes().observe(getViewLifecycleOwner(), new Observer<List<BaseShoes>>() {
            @Override
            public void onChanged(List<BaseShoes> baseShoes) {
                Log.i("ROOM111", "\t\t\tBaseShoes");
                for (int i = 0; i < baseShoes.size(); i++) {
                    Log.i("ROOM111", String.valueOf("Size: " + baseShoes.get(i).base.size));
                    Log.i("ROOM111", String.valueOf(baseShoes.get(i).base.hiddenSize));
                    for (int j = 0; j < baseShoes.get(i).shoes.size(); j++) {
                        Log.i("ROOM111", String.valueOf("Brand Name: " + baseShoes.get(i).shoes.get(j).brand_name));
                        Log.i("ROOM111", String.valueOf("Model Name: " + baseShoes.get(i).shoes.get(j).modelName));
                        Log.i("ROOM111", String.valueOf("Factor: " + baseShoes.get(i).shoes.get(j).factor));
                        Log.i("ROOM111", String.valueOf("Image: " + baseShoes.get(i).shoes.get(j).image));
                    }
                }
            }
        });

        viewModel.getAllFavorites().observe(getViewLifecycleOwner(), new Observer<List<Favorite>>() {
            @Override
            public void onChanged(List<Favorite> favoriteList) {
//                for (int i = 0; i < favoriteList.size(); i++) {
//                    Log.i("ROOM111", String.valueOf("Id: "+favoriteList.get(i).id_favorite_shoes));
//                    Log.i("ROOM111", String.valueOf("\tId_shoes: "+favoriteList.get(i).id_shoes));
//                    Log.i("ROOM111", String.valueOf("\tSize: "+favoriteList.get(i).size));
//                }
            }
        });

        viewModel.getAllFavoriteShoes().observe(getViewLifecycleOwner(), new Observer<List<FavoriteShoes>>() {
            @Override
            public void onChanged(List<FavoriteShoes> favoriteShoes) {
                Log.i("ROOM111", "\t\t\tFavoriteShoes");
                for (int i = 0; i < favoriteShoes.size(); i++) {
                    Log.i("ROOM111", String.valueOf("Id FavoriteShoes: "+favoriteShoes.get(i).favorite.id_favorite_shoes));
                    for (int j = 0; j < favoriteShoes.get(i).shoes.size(); j++) {
                        Log.i("ROOM111", String.valueOf("Id Shoes: "+favoriteShoes.get(i).shoes.get(j).modelName));
                        Log.i("ROOM111", String.valueOf("Id Shoes: "+favoriteShoes.get(i).shoes.get(j).brand_name));
                        Log.i("ROOM111", String.valueOf("Id Shoes: "+favoriteShoes.get(i).shoes.get(j).factor));
                    }
                }
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