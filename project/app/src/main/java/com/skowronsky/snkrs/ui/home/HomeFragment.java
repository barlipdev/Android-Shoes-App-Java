package com.skowronsky.snkrs.ui.home;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.skowronsky.snkrs.R;
import com.skowronsky.snkrs.RecyclerViewAdapter;
import com.skowronsky.snkrs.Shoes;
import com.skowronsky.snkrs.databinding.FragmentHomeBinding;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;
    private RecyclerView recyclerView;
    private RecyclerViewAdapter<Context> recyclerViewAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
            homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
            binding = DataBindingUtil.inflate(inflater,R.layout.fragment_home,container,false);
            binding.setHomeViewModel(homeViewModel);
            binding.setLifecycleOwner(this);
            recyclerView = new RecyclerView(getContext());
            recyclerView = binding.shoesView;
            homeViewModel.init();
            homeViewModel.getShoesLiveData().observe(getViewLifecycleOwner(),shoesListUpdateObserver);
            return binding.getRoot();
            }
    final Observer<ArrayList<Shoes>> shoesListUpdateObserver = new Observer<ArrayList<Shoes>>() {
        @Override
        public void onChanged(ArrayList<Shoes> shoesArrayList) {
            recyclerViewAdapter = new RecyclerViewAdapter<>(getContext(), shoesArrayList);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerView.setAdapter(recyclerViewAdapter);
        }
    };
}
