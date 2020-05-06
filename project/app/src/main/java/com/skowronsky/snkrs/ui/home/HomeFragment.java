package com.skowronsky.snkrs.ui.home;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.skowronsky.snkrs.R;
import com.skowronsky.snkrs.databinding.FragmentHomeBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;
    private RecyclerView recyclerView;
    private HomeRecyclerViewAdapter<Context> recyclerViewAdapter;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        binding.setBaseViewModel(homeViewModel);
        binding.setLifecycleOwner(this);
        recyclerView = new RecyclerView(Objects.requireNonNull(getActivity()));
        recyclerView = binding.baselist;
        recyclerViewAdapter = new HomeRecyclerViewAdapter<>(getContext(),homeViewModel);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(recyclerViewAdapter);

        homeViewModel.getAllBaseShoes().observe(getViewLifecycleOwner(), new Observer<List<com.skowronsky.snkrs.database.BaseShoes>>() {
            @Override
            public void onChanged(List<com.skowronsky.snkrs.database.BaseShoes> baseShoes) {
                recyclerViewAdapter.setBaseShoes(baseShoes);
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

        final LiveData<Boolean> navToInfo = homeViewModel.getInfoNav();
        navToInfo.observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if(aBoolean) {
                    navigateToInfo();
                    homeViewModel.eventNavToInfoFinished();
                }
            }
        });

        return binding.getRoot();
    }

    private void navigateToInfo(){
        NavHostFragment.findNavController(this).navigate(R.id.action_homeBase_to_brandListFragment);
    }

    private void navigateToHome(){
        NavHostFragment.findNavController(this).navigate(R.id.action_homeBase_to_navigation_home);
    }

}
