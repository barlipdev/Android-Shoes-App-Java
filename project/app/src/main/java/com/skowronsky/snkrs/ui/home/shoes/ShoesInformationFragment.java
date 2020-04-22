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
import android.widget.ListView;

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
    private RecyclerView recyclerView;
    private ShoesInformationRecyclerViewAdapter<Context> recyclerViewAdapter;
    private HomeBaseViewModel baseViewModel;
    private String shoe_company;
    private String shoe_model;
    private String shoe_image;
    private Shoes shoe;
    private double shoe_factor;
    private int shoe_id;
    private ArrayList<Double> sizes;
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
        sizes = new ArrayList<Double>();

        for(int i=35;i<46;i++){
            sizes.add(i*1.0);
        }

        recyclerView = new RecyclerView(Objects.requireNonNull(getActivity()));
        recyclerView = shoesInformationFragmentBinding.sizeView;
        shoesInformationViewModel.init(sizes);
        shoesInformationViewModel.getSizesData().observe(getViewLifecycleOwner(), new Observer<ArrayList<Double>>() {
            @Override
            public void onChanged(ArrayList<Double> sizes) {
                recyclerViewAdapter = new ShoesInformationRecyclerViewAdapter(getContext(), sizes,shoesInformationViewModel);
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                recyclerView.setAdapter(recyclerViewAdapter);
            }
        });

        shoesInformationViewModel.getSelectedSize().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                selectedSize = integer;
            }
        });

        shoesInformationFragmentBinding.saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shoesInformationViewModel.addShoeToBase(shoe,1);
                //BaseShoes.baseList.add(shoe);
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
