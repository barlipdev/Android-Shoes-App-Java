package com.skowronsky.snkrs.ui.home;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.graphics.Color;
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
import com.skowronsky.snkrs.adapter.OnSwipeButtonClickListener;
import com.skowronsky.snkrs.adapter.SwipeHelper;
import com.skowronsky.snkrs.database.BaseShoes;
import com.skowronsky.snkrs.databinding.FragmentHomeBinding;

import java.util.List;

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
        recyclerView = new RecyclerView(requireActivity());
        recyclerView = binding.baselist;
        recyclerViewAdapter = new HomeRecyclerViewAdapter<>(getContext(),homeViewModel);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(recyclerViewAdapter);

        homeViewModel.getAllBaseShoes().observe(getViewLifecycleOwner(), new Observer<List<BaseShoes>>() {
            @Override
            public void onChanged(List<BaseShoes> baseShoes) {
                recyclerViewAdapter.setBaseShoes(baseShoes);
                homeViewModel.updateBaseShoes(baseShoes);
            }
        });

        SwipeHelper swipeHelper = new SwipeHelper(getContext(),recyclerView,300){
            public void instantiateOnSwipeButton(RecyclerView.ViewHolder viewHolder, List<SwipeHelper.OnSwipeButton> buffer) {
                buffer.add(new OnSwipeButton(requireContext(),
                        "Delete",
                        30,
                        R.drawable.ic_delete,
                        Color.parseColor("#D81B60"),
                        new OnSwipeButtonClickListener(){
                            @Override
                            public void onClick(int pos) {
                                recyclerViewAdapter.delete(pos);
                            }
                        }));
            }
        };

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
