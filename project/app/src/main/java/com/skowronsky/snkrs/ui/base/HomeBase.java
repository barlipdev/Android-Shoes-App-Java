package com.skowronsky.snkrs.ui.base;

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
import com.skowronsky.snkrs.databinding.HomeBaseFragmentBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class HomeBase extends Fragment {

    private HomeBaseViewModel homeViewModel;
    private HomeBaseFragmentBinding binding;
    private RecyclerView recyclerView;
    private RecyclerViewBaseHomeAdapter<Context> recyclerViewAdapter;
    private ArrayList<String> baseinfo;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        homeViewModel = new ViewModelProvider(this).get(HomeBaseViewModel.class);
        binding = DataBindingUtil.inflate(inflater, R.layout.home_base_fragment, container, false);
        binding.setBaseViewModel(homeViewModel);
        binding.setLifecycleOwner(this);
        recyclerView = new RecyclerView(Objects.requireNonNull(getActivity()));
        recyclerView = binding.baselist;

        recyclerViewAdapter = new RecyclerViewBaseHomeAdapter<>(getContext(),homeViewModel);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(recyclerViewAdapter);

        homeViewModel.getAllBaseShoes().observe(getViewLifecycleOwner(), new Observer<List<com.skowronsky.snkrs.database.BaseShoes>>() {
            @Override
            public void onChanged(List<com.skowronsky.snkrs.database.BaseShoes> baseShoes) {
                recyclerViewAdapter.setBaseShoes(baseShoes);
            }
        });

        homeViewModel.getBaseLiveData().observe(getViewLifecycleOwner(), new Observer<ArrayList<String>>() {
            @Override
            public void onChanged(ArrayList<String> strings) {
                baseinfo = new ArrayList<String>();
                baseinfo = strings;
            }
        });


        final LiveData<Boolean> navToShoes = homeViewModel.getEventHomeNav();
        navToShoes.observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if(aBoolean){
                    navigateToHome("set_base");
                    homeViewModel.eventNavToHomeFinished();
                }
            }
        });

        final LiveData<Boolean> navToInfo = homeViewModel.getInfoNav();
        navToInfo.observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if(aBoolean) {
                    navigateToInfo(baseinfo);
                    homeViewModel.eventNavToInfoFinished();
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

    private void navigateToInfo(ArrayList<String> baseinfo){
        Bundle bundle = new Bundle();
        bundle.putStringArrayList("base_info",baseinfo);
        NavHostFragment.findNavController(this).navigate(R.id.action_homeBase_to_brandListFragment,bundle);
    }

    private void navigateToHome(String destination){
        Bundle bundle = new Bundle();
        bundle.putString("destination",destination);
        NavHostFragment.findNavController(this).navigate(R.id.action_homeBase_to_navigation_home);
    }

}
