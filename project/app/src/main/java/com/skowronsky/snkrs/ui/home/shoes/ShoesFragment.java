package com.skowronsky.snkrs.ui.home.shoes;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.skowronsky.snkrs.R;
import com.skowronsky.snkrs.databinding.ShoesFragmentBinding;
import com.skowronsky.snkrs.ui.home.HomeViewModel;

import java.util.ArrayList;
import java.util.Objects;

public class ShoesFragment extends Fragment {

    private ShoesViewModel shoesViewModel;
    private ShoesFragmentBinding shoesFragmentBinding;
    private RecyclerView recyclerView;
    private ShoesRecyclerViewAdapter<Context> recyclerViewAdapter;

    public static ShoesFragment newInstance() {
        return new ShoesFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        shoesViewModel = new ViewModelProvider(this).get(ShoesViewModel.class);
        shoesFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.shoes_fragment, container, false);
        shoesFragmentBinding.setShoesViewModel(shoesViewModel);
        shoesFragmentBinding.setLifecycleOwner(this);

        recyclerView = new RecyclerView(Objects.requireNonNull(getActivity()));
        recyclerView = shoesFragmentBinding.ShoesView;
        shoesViewModel.init("Nike");
        shoesViewModel.getShoesLiveData().observe(getViewLifecycleOwner(), new Observer<ArrayList<Shoes>>() {
            @Override
            public void onChanged(ArrayList<Shoes> CompanyArrayList) {
                recyclerViewAdapter = new ShoesRecyclerViewAdapter<>(getContext(), CompanyArrayList);
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                recyclerView.setAdapter(recyclerViewAdapter);
            }
        });

        return shoesFragmentBinding.getRoot();
    }

}
