package com.skowronsky.snkrs.ui.home.add.shoeslist;

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
import com.skowronsky.snkrs.database.Shoes;
import com.skowronsky.snkrs.databinding.FragmentBaseselectorShoesListBinding;

import java.util.List;
import java.util.Objects;

public class ShoesListFragment extends Fragment {

    private ShoesListViewModel shoesListViewModel;
    private FragmentBaseselectorShoesListBinding shoesFragmentBinding;
    private RecyclerView recyclerView;
    private ShoesListRecyclerViewAdapter<Context> recyclerViewAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        shoesListViewModel = new ViewModelProvider(this).get(ShoesListViewModel.class);
        shoesFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_baseselector_shoes_list, container, false);
        shoesFragmentBinding.setShoesListViewModel(shoesListViewModel);
        shoesFragmentBinding.setLifecycleOwner(this);

        recyclerView = new RecyclerView(Objects.requireNonNull(getActivity()));
        recyclerView = shoesFragmentBinding.ShoesView;
        recyclerViewAdapter = new ShoesListRecyclerViewAdapter<>(getContext(), shoesListViewModel);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(recyclerViewAdapter);

        shoesListViewModel.getAllShoes().observe(getViewLifecycleOwner(), new Observer<List<Shoes>>() {
            @Override
            public void onChanged(List<Shoes> shoes) {
                recyclerViewAdapter.setShoesList(shoes);
            }
        });

        final LiveData<Boolean> navInfo = shoesListViewModel.getInfoNav();
        navInfo.observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if(aBoolean) {
                    navigateToShoesInfo();
                    shoesListViewModel.eventNavToInfoFinished();
                }
            }
        });
        return shoesFragmentBinding.getRoot();
    }

    private void navigateToShoesInfo(){
        NavHostFragment.findNavController(this).navigate(R.id.action_shoesFragment2_to_shoesInformationFragment2);
    }

}
