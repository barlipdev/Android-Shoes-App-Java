package com.skowronsky.snkrs.ui.base;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.skowronsky.snkrs.R;
import com.skowronsky.snkrs.databinding.ShoeInfoFragmentBinding;
import com.skowronsky.snkrs.model.Shoes;
import com.skowronsky.snkrs.ui.home.shoes.ShoesInformationViewModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ShoeInfoFragment extends Fragment {

    private ShoeInfoViewModel mViewModel;
    private ShoeInfoFragmentBinding shoeInfoFragmentBinding;
    private ArrayList<String> base_info;
    private String shoe_model;
    private String shoe_image;
    private double shoe_factor;
    private int id_base;
    private int shoe_id;
    private double prefer_size;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        mViewModel = new ViewModelProvider(this).get(ShoeInfoViewModel.class);
        shoeInfoFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.shoe_info_fragment, container, false);
        shoeInfoFragmentBinding.setShoesInfoViewModel(mViewModel);
        shoeInfoFragmentBinding.setLifecycleOwner(this);

        shoe_model = getArguments().getString("model");
        shoe_image = getArguments().getString("image");
        shoe_factor = getArguments().getDouble("factor");
        shoe_id = getArguments().getInt("id");
        base_info = getArguments().getStringArrayList("base_info");
        id_base = Integer.valueOf(base_info.get(4));
        Log.i("0011",base_info.get(4));

        prefer_size = Double.valueOf(base_info.get(2)) + Double.valueOf(shoe_factor);

        shoeInfoFragmentBinding.shoeComp.setText(base_info.get(0));
        shoeInfoFragmentBinding.shoeModel.setText(base_info.get(1));
        shoeInfoFragmentBinding.shoeSize.setText(base_info.get(2));
        Picasso.with(getContext()).load(base_info.get(3)).into(shoeInfoFragmentBinding.shoeIcon);
        shoeInfoFragmentBinding.modelInfo.setText(shoe_model);
        shoeInfoFragmentBinding.size.setText(String.valueOf(prefer_size));
        Picasso.with(getContext()).load(shoe_image).into(
                shoeInfoFragmentBinding.imageView);

        return shoeInfoFragmentBinding.getRoot();
    }


}
