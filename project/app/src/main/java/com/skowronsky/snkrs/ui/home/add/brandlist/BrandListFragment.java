package com.skowronsky.snkrs.ui.home.add.brandlist;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
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
import com.skowronsky.snkrs.database.Brand;
import com.skowronsky.snkrs.databinding.FragmentBaseselectorBrandlistBinding;

import java.util.List;
import java.util.Objects;

public class BrandListFragment extends Fragment {

    private BrandListViewModel brandListViewModel;
    private FragmentBaseselectorBrandlistBinding binding;
    private RecyclerView recyclerView;
    private BrandListRecyclerViewAdapter<Context> brandListRecyclerViewAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        brandListViewModel = new ViewModelProvider(this).get(BrandListViewModel.class);
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_baseselector_brandlist, container, false);
        binding.setBrandListViewModel(brandListViewModel);
        binding.setLifecycleOwner(this);
        recyclerView = new RecyclerView(Objects.requireNonNull(getActivity()));
        recyclerView = binding.CompanyView;
        brandListRecyclerViewAdapter = new BrandListRecyclerViewAdapter<>(getContext(), brandListViewModel);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(brandListRecyclerViewAdapter);

        brandListViewModel.getAllBrands().observe(getViewLifecycleOwner(), new Observer<List<Brand>>() {
            @Override
            public void onChanged(List<Brand> brands) {
                brandListRecyclerViewAdapter.setBrandList(brands);
            }
        });

        final LiveData<Boolean> navToShoes = brandListViewModel.getEventShoesNav();
        navToShoes.observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if(aBoolean){
                    navigateToShoes();
                    brandListViewModel.eventNavToShoesFinished();
                }
            }
        });
        return binding.getRoot();
    }

    /**
     * Metoda odpowiadająca za nawigację do odpowiedniego fragmentu,
     * w tym przypadku do listy butów danej marki
     */
    private void navigateToShoes(){
        NavHostFragment.findNavController(this).navigate(R.id.action_navigation_home_to_shoesFragment22);
    }

};