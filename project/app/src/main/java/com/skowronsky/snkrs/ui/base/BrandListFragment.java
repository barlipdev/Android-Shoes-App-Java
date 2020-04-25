package com.skowronsky.snkrs.ui.base;

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

import com.skowronsky.snkrs.MyApplication;
import com.skowronsky.snkrs.R;
import com.skowronsky.snkrs.RecyclerViewAdapter;
import com.skowronsky.snkrs.databinding.BrandListFragmentBinding;
import com.skowronsky.snkrs.model.Brand;
import com.skowronsky.snkrs.ui.home.HomeViewModel;
import com.skowronsky.snkrs.ui.home.HomeViewModelFactory;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BrandListFragment extends Fragment {

    private BrandListViewModel mViewModel;
    private BrandListFragmentBinding binding;
    private RecyclerView recyclerView;
    private BrandListRecyclerViewAdapter<Context> recyclerViewAdapter;
    private String ShoesCompany = "";
    private ArrayList<String> base_info;


    public static BrandListFragment newInstance() {
        return new BrandListFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        mViewModel = new ViewModelProvider(this).get(BrandListViewModel.class);
        binding = DataBindingUtil.inflate(inflater, R.layout.brand_list_fragment, container, false);
        binding.setBrandListViewModel(mViewModel);
        binding.setLifecycleOwner(this);
        recyclerView = new RecyclerView(Objects.requireNonNull(getActivity()));
        recyclerView = binding.CompanyView;

        base_info = new ArrayList<String>();
        base_info = getArguments().getStringArrayList("base_info");
        binding.shoeModel.setText(base_info.get(1)+"\t");
        binding.shoeComp.setText(base_info.get(0)+"\t");
        binding.shoeSize.setText(base_info.get(2)+"\t");
        Picasso.with(getContext()).load(base_info.get(3)).into(
                binding.shoeIcon);

        mViewModel.getAllBrands().observe(getViewLifecycleOwner(), new Observer<List<com.skowronsky.snkrs.database.Brand>>() {
            @Override
            public void onChanged(List<com.skowronsky.snkrs.database.Brand> brands) {
                mViewModel.initBrands(brands);
            }
        });

        mViewModel.init();
        mViewModel.getCompanyLiveData().observe(getViewLifecycleOwner(), new Observer<ArrayList<Brand>>() {
            @Override
            public void onChanged(ArrayList<Brand> CompanyArrayList) {
                recyclerViewAdapter = new BrandListRecyclerViewAdapter<>(getContext(), CompanyArrayList,mViewModel);
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                recyclerView.setAdapter(recyclerViewAdapter);
            }
        });
        mViewModel.getEventCompanyName().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                ShoesCompany = s;
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

    private void navigateToShoes(){
        Bundle bundle = new Bundle();
        bundle.putString("key",ShoesCompany.toString());
        bundle.putStringArrayList("base_info",base_info);
        Log.i("NameCompany",ShoesCompany);
        NavHostFragment.findNavController(this).navigate(R.id.action_brandListFragment_to_shoesListFragment,bundle);
    }

}
