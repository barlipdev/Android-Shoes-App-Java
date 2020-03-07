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
    private String company;

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
        company = getArguments().getString("key");

        recyclerView = new RecyclerView(Objects.requireNonNull(getActivity()));
        recyclerView = shoesFragmentBinding.ShoesView;
        shoesViewModel.init(company);
        Log.i("NameCompany2",company);
        shoesViewModel.getShoesLiveData().observe(getViewLifecycleOwner(), new Observer<ArrayList<Shoes>>() {
            @Override
            public void onChanged(ArrayList<Shoes> CompanyArrayList) {
                recyclerViewAdapter = new ShoesRecyclerViewAdapter<>(getContext(), CompanyArrayList,shoesViewModel);
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                recyclerView.setAdapter(recyclerViewAdapter);
            }
        });

        final LiveData<Boolean> navInfo = shoesViewModel.getInfoNav();
        navInfo.observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                navigateToShoesInfo();
                shoesViewModel.eventNavToInfoFinished();
            }
        });

        return shoesFragmentBinding.getRoot();
    }

    private void navigateToShoesInfo(){
        //Bundle bundle = new Bundle();
        //bundle.putString("key",ShoesCompany.toString());
        NavHostFragment.findNavController(this).navigate(R.id.action_shoesFragment2_to_shoesInformationFragment2);
    }

}
