package com.skowronsky.snkrs.ui.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.skowronsky.snkrs.MyApplication;
import com.skowronsky.snkrs.R;
import com.skowronsky.snkrs.RecyclerViewAdapter;
import com.skowronsky.snkrs.Company;
import com.skowronsky.snkrs.databinding.FragmentHomeBinding;
import com.skowronsky.snkrs.model.Brand;
import com.skowronsky.snkrs.storage.Storage;
import com.skowronsky.snkrs.ui.dashboard.DashboardFragment;
import com.skowronsky.snkrs.ui.profile.ProfileViewModelFactory;

import java.util.ArrayList;
import java.util.Objects;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;
    private RecyclerView recyclerView;
    private RecyclerViewAdapter<Context> recyclerViewAdapter;
    private String ShoesCompany = "";
    private MyApplication appState;
    private Storage storage;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        appState = ((MyApplication)this.getActivity().getApplication());
        storage = appState.storage;
        homeViewModel = new ViewModelProvider(this,new HomeViewModelFactory(storage)).get(HomeViewModel.class);
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        binding.setHomeViewModel(homeViewModel);
        binding.setLifecycleOwner(this);

                recyclerView = new RecyclerView(Objects.requireNonNull(getActivity()));
                recyclerView = binding.CompanyView;
                homeViewModel.init();
                homeViewModel.getCompanyLiveData().observe(getViewLifecycleOwner(), new Observer<ArrayList<Brand>>() {
                    @Override
                    public void onChanged(ArrayList<Brand> CompanyArrayList) {
                        recyclerViewAdapter = new RecyclerViewAdapter<>(getContext(), CompanyArrayList,homeViewModel);
                        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                        recyclerView.setAdapter(recyclerViewAdapter);
                    }
                });
                homeViewModel.getEventCompanyName().observe(getViewLifecycleOwner(), new Observer<String>() {
                    @Override
                    public void onChanged(String s) {
                        ShoesCompany = s;
                    }
                });

                final LiveData<Boolean> navToShoes = homeViewModel.getEventShoesNav();
                navToShoes.observe(getViewLifecycleOwner(), new Observer<Boolean>() {
                    @Override
                    public void onChanged(Boolean aBoolean) {
                        if(aBoolean){
                            navigateToShoes();
                            homeViewModel.eventNavToShoesFinished();
                        }
                    }
                });


        return binding.getRoot();
    }

    private void navigateToShoes(){
        Bundle bundle = new Bundle();
        bundle.putString("key",ShoesCompany.toString());
        Log.i("NameCompany",ShoesCompany);
        NavHostFragment.findNavController(this).navigate(R.id.action_navigation_home_to_shoesFragment22,bundle);
    }

};