package com.skowronsky.snkrs.ui.home.search.brandlist;

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
import com.skowronsky.snkrs.databinding.FragmentSearchBrandlistBinding;
import com.skowronsky.snkrs.storage.NavigationStorage;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Objects;

public class BrandListFragment extends Fragment {

    private BrandListViewModel mViewModel;
    private FragmentSearchBrandlistBinding binding;
    private RecyclerView recyclerView;
    private BrandListRecyclerViewAdapter<Context> recyclerViewAdapter;
    private NavigationStorage navigationStorage;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        navigationStorage = NavigationStorage.getInstance();
        mViewModel = new ViewModelProvider(this).get(BrandListViewModel.class);
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_search_brandlist, container, false);
        binding.setBrandListViewModel(mViewModel);
        binding.setLifecycleOwner(this);
        recyclerView = new RecyclerView(Objects.requireNonNull(getActivity()));
        recyclerView = binding.CompanyView;
        recyclerViewAdapter = new BrandListRecyclerViewAdapter<>(getContext(),mViewModel);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(recyclerViewAdapter);

        Picasso.with(getContext()).load(navigationStorage.getBaseShoe().brandShoes.shoes.getImage()).into(
                binding.shoeIcon);

        mViewModel.getAllBrands().observe(getViewLifecycleOwner(), new Observer<List<com.skowronsky.snkrs.database.Brand>>() {
            @Override
            public void onChanged(List<com.skowronsky.snkrs.database.Brand> brands) {
                recyclerViewAdapter.setBrandList(brands);
            }
        });

        final LiveData<Boolean> navToShoes = mViewModel.getEventShoesNav();
        navToShoes.observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if(aBoolean){
                    navigateToShoes();
                    mViewModel.eventNavToShoesFinished();
                }
            }
        });

        return binding.getRoot();
    }

    /**
     * Metoda odpowiadająca za nawigacje do odpowiedniego fragmentu, w tym przypadku do listy butów danej marki
     */
    private void navigateToShoes(){
        NavHostFragment.findNavController(this).navigate(R.id.action_brandListFragment_to_shoesListFragment);
    }

}
