package com.skowronsky.snkrs.ui.home.shoes;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.skowronsky.snkrs.R;
import com.skowronsky.snkrs.databinding.ShoesInformationFragmentBinding;

public class ShoesInformationFragment extends Fragment {

    private ShoesInformationViewModel shoesInformationViewModel;
    private ShoesInformationFragmentBinding shoesInformationFragmentBinding;
    private String shoe_company;
    private String shoe_model;

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

        shoesInformationFragmentBinding.modelInfo.setText(shoe_model);
        shoesInformationFragmentBinding.preferNumber.setText(shoe_company);




        return shoesInformationFragmentBinding.getRoot();
    }




}
