package com.skowronsky.snkrs.ui.home.shoes;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.skowronsky.snkrs.R;
import com.skowronsky.snkrs.databinding.HomeBaseFragmentBinding;
import com.skowronsky.snkrs.databinding.ShoesInformationFragmentBinding;
import com.skowronsky.snkrs.model.BaseShoes;
import com.skowronsky.snkrs.model.Shoes;
import com.skowronsky.snkrs.ui.base.HomeBaseViewModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ShoesInformationFragment extends Fragment {

    private ShoesInformationViewModel shoesInformationViewModel;
    private ShoesInformationFragmentBinding shoesInformationFragmentBinding;
    private String shoe_company;
    private String shoe_model;
    private String shoe_image;
    private Shoes shoe;
    private double shoe_factor;
    private int shoe_id;
    private int selectedSize;

    public static ShoesInformationFragment newInstance() {
        return new ShoesInformationFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        shoesInformationViewModel = new ViewModelProvider(this).get(ShoesInformationViewModel.class);
        shoesInformationFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.shoes_information_fragment, container, false);
        shoesInformationFragmentBinding.setShoesInfoViewModel(shoesInformationViewModel);
        shoesInformationFragmentBinding.setLifecycleOwner(this);

        shoe_model = getArguments().getString("model");
        shoe_company = getArguments().getString("company");
        shoe_image = getArguments().getString("image");
        shoe_factor = getArguments().getDouble("factor");
        shoe_id = getArguments().getInt("id");

        shoe = new Shoes(shoe_id,shoe_company,shoe_model,shoe_factor,shoe_image);


        shoesInformationFragmentBinding.saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(shoesInformationFragmentBinding.rb1.isChecked()){
                    shoesInformationViewModel.addShoeToBase(shoe,38);
                }
                if(shoesInformationFragmentBinding.rb2.isChecked()){
                    shoesInformationViewModel.addShoeToBase(shoe,38.5);
                }
                if(shoesInformationFragmentBinding.rb3.isChecked()){
                    shoesInformationViewModel.addShoeToBase(shoe,39);
                }
                if(shoesInformationFragmentBinding.rb4.isChecked()){
                    shoesInformationViewModel.addShoeToBase(shoe,39.5);
                }
                if(shoesInformationFragmentBinding.rb5.isChecked()){
                    shoesInformationViewModel.addShoeToBase(shoe,40);
                }
                if(shoesInformationFragmentBinding.rb6.isChecked()){
                    shoesInformationViewModel.addShoeToBase(shoe,40.5);
                }
                if(shoesInformationFragmentBinding.rb7.isChecked()){
                    shoesInformationViewModel.addShoeToBase(shoe,41);
                }
                if(shoesInformationFragmentBinding.rb8.isChecked()){
                    shoesInformationViewModel.addShoeToBase(shoe,41.5);
                }
                if(shoesInformationFragmentBinding.rb9.isChecked()){
                    shoesInformationViewModel.addShoeToBase(shoe,42);
                }
                if(shoesInformationFragmentBinding.rb10.isChecked()){
                    shoesInformationViewModel.addShoeToBase(shoe,42.5);
                }
                if(shoesInformationFragmentBinding.rb11.isChecked()){
                    shoesInformationViewModel.addShoeToBase(shoe,43);
                }
                if(shoesInformationFragmentBinding.rb12.isChecked()){
                    shoesInformationViewModel.addShoeToBase(shoe,43.5);
                }
                if(shoesInformationFragmentBinding.rb13.isChecked()){
                    shoesInformationViewModel.addShoeToBase(shoe,44);
                }
                if(shoesInformationFragmentBinding.rb14.isChecked()){
                    shoesInformationViewModel.addShoeToBase(shoe,44.5);
                }
                if(shoesInformationFragmentBinding.rb15.isChecked()){
                    shoesInformationViewModel.addShoeToBase(shoe,45);
                }
                if(shoesInformationFragmentBinding.rb16.isChecked()){
                    shoesInformationViewModel.addShoeToBase(shoe,45.5);
                }
                if(shoesInformationFragmentBinding.rb17.isChecked()){
                    shoesInformationViewModel.addShoeToBase(shoe,46);
                }
                shoesInformationViewModel.eventNavToInfo();
            }
        });

        shoesInformationFragmentBinding.modelInfo.setText(shoe_model);
        Picasso.with(getContext()).load(shoe_image).into(
                shoesInformationFragmentBinding.imageView);

        final LiveData<Boolean> navInfo = shoesInformationViewModel.getNavToBase();
        navInfo.observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if(aBoolean) {
                    navigateToBase();
                    shoesInformationViewModel.eventNavToInfoFinished();
                }
            }
        });

        return shoesInformationFragmentBinding.getRoot();
    }

    private void navigateToBase(){
        NavHostFragment.findNavController(this).navigate(R.id.action_shoesInformationFragment2_to_homeBase);
    }



}
