package com.skowronsky.snkrs.ui.base;

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
import com.skowronsky.snkrs.databinding.HomeBaseFragmentBinding;
import com.skowronsky.snkrs.databinding.ShoesInformationFragmentBinding;
import com.skowronsky.snkrs.model.BaseShoes;
import com.skowronsky.snkrs.model.Shoes;
import com.skowronsky.snkrs.storage.Storage;
import com.skowronsky.snkrs.ui.home.HomeViewModel;
import com.skowronsky.snkrs.ui.home.HomeViewModelFactory;
import com.skowronsky.snkrs.ui.home.shoes.ShoesRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.Objects;

public class HomeBase extends Fragment {

    private HomeBaseViewModel homeViewModel;
    private MyApplication appState;
    private Storage storage;
    private HomeBaseFragmentBinding binding;
    private RecyclerView recyclerView;
    private RecyclerViewBaseHomeAdapter<Context> recyclerViewAdapter;
    private String shoe_company;
    private String shoe_model;
    private String shoe_image;
    private double shoe_factor;
    private int shoe_id;
    private Shoes shoe;


    public static HomeBase newInstance() {
        return new HomeBase();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        appState = ((MyApplication)this.getActivity().getApplication());
        storage = appState.storage;
        homeViewModel = new ViewModelProvider(this,new HomeBaseViewModelFactory(storage)).get(HomeBaseViewModel.class);
        binding = DataBindingUtil.inflate(inflater, R.layout.home_base_fragment, container, false);
        binding.setBaseViewModel(homeViewModel);
        binding.setLifecycleOwner(this);
        recyclerView = new RecyclerView(Objects.requireNonNull(getActivity()));
        recyclerView = binding.baselist;
        if (BaseShoes.baseList.size()>0)
        {
            homeViewModel.init(BaseShoes.baseList);
        }
        homeViewModel.getShoesLiveData().observe(getViewLifecycleOwner(), new Observer<ArrayList<Shoes>>() {
            @Override
            public void onChanged(ArrayList<com.skowronsky.snkrs.model.Shoes> CompanyArrayList) {
                recyclerViewAdapter = new RecyclerViewBaseHomeAdapter<>(getContext(), CompanyArrayList,homeViewModel);
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                recyclerView.setAdapter(recyclerViewAdapter);

            }
        });

        final LiveData<Boolean> navToShoes = homeViewModel.getEventHomeNav();
        navToShoes.observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if(aBoolean){
                    navigateToHome();
                    homeViewModel.eventNavToHomeFinished();
                }
            }
        });

        binding.addBase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                homeViewModel.eventNavToHome();
            }
        });

        return binding.getRoot();
    }

    private void navigateToHome(){
        //Bundle bundle = new Bundle();
        //bundle.putString("key",ShoesCompany.toString());
        Log.i("Array size",String.valueOf(BaseShoes.baseList.size()));
        NavHostFragment.findNavController(this).navigate(R.id.action_homeBase_to_navigation_home);
    }

}
