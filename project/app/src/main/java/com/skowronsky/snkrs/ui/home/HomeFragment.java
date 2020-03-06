package com.skowronsky.snkrs.ui.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.skowronsky.snkrs.R;
import com.skowronsky.snkrs.RecyclerViewAdapter;
import com.skowronsky.snkrs.Company;
import com.skowronsky.snkrs.databinding.FragmentHomeBinding;
import com.skowronsky.snkrs.ui.dashboard.DashboardFragment;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;
    private RecyclerView recyclerView;
    private RecyclerViewAdapter<Context> recyclerViewAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        binding.setHomeViewModel(homeViewModel);
        binding.setLifecycleOwner(this);
        recyclerView = new RecyclerView(getContext());
        recyclerView = binding.CompanyView;
        homeViewModel.init();
        homeViewModel.getCompanyLiveData().observe(getViewLifecycleOwner(), new Observer<ArrayList<Company>>() {
            @Override
            public void onChanged(ArrayList<Company> CompanyArrayList) {
                recyclerViewAdapter = new RecyclerViewAdapter<>(getContext(), CompanyArrayList);
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                recyclerView.setAdapter(recyclerViewAdapter);
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
        NavHostFragment.findNavController(this).navigate(R.id.action_navigation_home_to_navigation_profile);
    }

};
