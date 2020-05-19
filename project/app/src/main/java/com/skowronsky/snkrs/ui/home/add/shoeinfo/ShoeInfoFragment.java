package com.skowronsky.snkrs.ui.home.add.shoeinfo;

import android.os.Bundle;
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
import com.skowronsky.snkrs.databinding.FragmentBaseselectorShoeInfoBinding;
import com.skowronsky.snkrs.storage.NavigationStorage;
import com.squareup.picasso.Picasso;

public class ShoeInfoFragment extends Fragment {

    private ShoeInfoViewModel shoeInfoViewModel;
    private FragmentBaseselectorShoeInfoBinding shoesInformationFragmentBinding;
    private NavigationStorage navigationStorage;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        navigationStorage = NavigationStorage.getInstance();
        shoeInfoViewModel = new ViewModelProvider(this).get(ShoeInfoViewModel.class);
        shoesInformationFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_baseselector_shoe_info, container, false);
        shoesInformationFragmentBinding.setShoesInfoViewModel(shoeInfoViewModel);
        shoesInformationFragmentBinding.setLifecycleOwner(this);


        shoesInformationFragmentBinding.saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(shoesInformationFragmentBinding.rb1.isChecked()){
                    shoeInfoViewModel.addShoeToBase(38);
                }
                if(shoesInformationFragmentBinding.rb2.isChecked()){
                    shoeInfoViewModel.addShoeToBase(38.5);
                }
                if(shoesInformationFragmentBinding.rb3.isChecked()){
                    shoeInfoViewModel.addShoeToBase(39);
                }
                if(shoesInformationFragmentBinding.rb4.isChecked()){
                    shoeInfoViewModel.addShoeToBase(39.5);
                }
                if(shoesInformationFragmentBinding.rb5.isChecked()){
                    shoeInfoViewModel.addShoeToBase(40);
                }
                if(shoesInformationFragmentBinding.rb6.isChecked()){
                    shoeInfoViewModel.addShoeToBase(40.5);
                }
                if(shoesInformationFragmentBinding.rb7.isChecked()){
                    shoeInfoViewModel.addShoeToBase(41);
                }
                if(shoesInformationFragmentBinding.rb8.isChecked()){
                    shoeInfoViewModel.addShoeToBase(41.5);
                }
                if(shoesInformationFragmentBinding.rb9.isChecked()){
                    shoeInfoViewModel.addShoeToBase(42);
                }
                if(shoesInformationFragmentBinding.rb10.isChecked()){
                    shoeInfoViewModel.addShoeToBase(42.5);
                }
                if(shoesInformationFragmentBinding.rb11.isChecked()){
                    shoeInfoViewModel.addShoeToBase(43);
                }
                if(shoesInformationFragmentBinding.rb12.isChecked()){
                    shoeInfoViewModel.addShoeToBase(43.5);
                }
                if(shoesInformationFragmentBinding.rb13.isChecked()){
                    shoeInfoViewModel.addShoeToBase(44);
                }
                if(shoesInformationFragmentBinding.rb14.isChecked()){
                    shoeInfoViewModel.addShoeToBase(44.5);
                }
                if(shoesInformationFragmentBinding.rb15.isChecked()){
                    shoeInfoViewModel.addShoeToBase(45);
                }
                if(shoesInformationFragmentBinding.rb16.isChecked()){
                    shoeInfoViewModel.addShoeToBase(45.5);
                }
                if(shoesInformationFragmentBinding.rb17.isChecked()){
                    shoeInfoViewModel.addShoeToBase(46);
                }
                shoeInfoViewModel.eventNavToInfo();
            }
        });

        Picasso.with(getContext()).load(navigationStorage.getShoe().image).into(
                shoesInformationFragmentBinding.imageView);

        final LiveData<Boolean> navInfo = shoeInfoViewModel.getNavToBase();
        navInfo.observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if(aBoolean) {
                    navigateToBase();
                    shoeInfoViewModel.eventNavToInfoFinished();
                }
            }
        });

        return shoesInformationFragmentBinding.getRoot();
    }

    private void navigateToBase(){
        NavHostFragment.findNavController(this).navigate(R.id.action_shoesInformationFragment2_to_homeBase);
    }



}
