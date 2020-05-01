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

import com.skowronsky.snkrs.MyApplication;
import com.skowronsky.snkrs.R;
import com.skowronsky.snkrs.database.Shoes;
import com.skowronsky.snkrs.databinding.ShoesFragmentBinding;
import com.skowronsky.snkrs.storage.Storage;
import com.skowronsky.snkrs.ui.home.HomeViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ShoesFragment extends Fragment {

    private ShoesViewModel shoesViewModel;
    private ShoesFragmentBinding shoesFragmentBinding;
    private RecyclerView recyclerView;
    private ShoesRecyclerViewAdapter<Context> recyclerViewAdapter;
    private Shoes shoe_info;
    private String brand;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        shoesViewModel = new ViewModelProvider(this).get(ShoesViewModel.class);
        shoesFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.shoes_fragment, container, false);
        shoesFragmentBinding.setShoesViewModel(shoesViewModel);
        shoesFragmentBinding.setLifecycleOwner(this);

        recyclerView = new RecyclerView(Objects.requireNonNull(getActivity()));
        recyclerView = shoesFragmentBinding.ShoesView;
        recyclerViewAdapter = new ShoesRecyclerViewAdapter<>(getContext(),shoesViewModel);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(recyclerViewAdapter);

        shoesViewModel.getAllShoes().observe(getViewLifecycleOwner(), new Observer<List<Shoes>>() {
            @Override
            public void onChanged(List<Shoes> shoes) {
                brand = getArguments().getString("key");
                recyclerViewAdapter.setShoesList(shoes,brand);
            }
        });

        shoesViewModel.getEventShoeInfo().observe(getViewLifecycleOwner(), new Observer<com.skowronsky.snkrs.database.Shoes>() {
            @Override
            public void onChanged(com.skowronsky.snkrs.database.Shoes s) {
                shoe_info = s;
            }
        });

        final LiveData<Boolean> navInfo = shoesViewModel.getInfoNav();
        navInfo.observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if(aBoolean) {
                    navigateToShoesInfo();
                    shoesViewModel.eventNavToInfoFinished();
                }
            }
        });

        return shoesFragmentBinding.getRoot();
    }

    private void navigateToShoesInfo(){
        Bundle bundle_info = new Bundle();
        bundle_info.putString("model",shoe_info.modelName.toString());
        bundle_info.putString("company",shoe_info.brand_name.toString());
        bundle_info.putString("image",shoe_info.image.toString());
        bundle_info.putInt("id",shoe_info.id_shoes);
        bundle_info.putDouble("factor",shoe_info.factor);
        bundle_info.putBoolean("is",true);
        NavHostFragment.findNavController(this).navigate(R.id.action_shoesFragment2_to_shoesInformationFragment2,bundle_info);
    }

}
