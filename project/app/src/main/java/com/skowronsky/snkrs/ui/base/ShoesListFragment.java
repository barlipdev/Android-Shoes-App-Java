package com.skowronsky.snkrs.ui.base;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.skowronsky.snkrs.R;
import com.skowronsky.snkrs.databinding.ShoesListFragmentBinding;
import com.skowronsky.snkrs.model.Shoes;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ShoesListFragment extends Fragment {

    private ShoesListViewModel mViewModel;
    private ShoesListFragmentBinding shoesListFragmentBinding;
    private RecyclerView recyclerView;
    private ShoesListRecyclerViewAdapter<Context> recyclerViewAdapter;
    private com.skowronsky.snkrs.model.Shoes shoe_info;
    private String brand;
    private ArrayList<String> base_info;

    public static ShoesListFragment newInstance() {
        return new ShoesListFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        brand = getArguments().getString("key");
        base_info = getArguments().getStringArrayList("base_info");
        mViewModel = new ViewModelProvider(this).get(ShoesListViewModel.class);
        shoesListFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.shoes_list_fragment, container, false);
        shoesListFragmentBinding.setShoesViewModel(mViewModel);
        shoesListFragmentBinding.setLifecycleOwner(this);
        shoesListFragmentBinding.shoeComp.setText(base_info.get(0)+"\t");
        shoesListFragmentBinding.shoeModel.setText(base_info.get(1)+"\t");
        shoesListFragmentBinding.shoeSize.setText(base_info.get(2)+"\t");
        Picasso.with(getContext()).load(base_info.get(3)).into(
                shoesListFragmentBinding.shoeIcon);

        recyclerView = new RecyclerView(Objects.requireNonNull(getActivity()));
        recyclerView = shoesListFragmentBinding.ShoesView;

        mViewModel.getAllShoes().observe(getViewLifecycleOwner(), new Observer<List<com.skowronsky.snkrs.database.Shoes>>() {
            @Override
            public void onChanged(List<com.skowronsky.snkrs.database.Shoes> shoes) {
                mViewModel.init_shoes_to_list(shoes,brand);
                mViewModel.init(brand);
            }
        });

        mViewModel.getShoesLiveData().observe(getViewLifecycleOwner(), new Observer<ArrayList<Shoes>>() {
            @Override
            public void onChanged(ArrayList<com.skowronsky.snkrs.model.Shoes> CompanyArrayList) {
                recyclerViewAdapter = new ShoesListRecyclerViewAdapter<>(getContext(), CompanyArrayList,mViewModel);
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                recyclerView.setAdapter(recyclerViewAdapter);
            }
        });

        mViewModel.getEventShoeInfo().observe(getViewLifecycleOwner(), new Observer<com.skowronsky.snkrs.model.Shoes>() {
            @Override
            public void onChanged(Shoes s) {
                shoe_info = s;
            }
        });

        final LiveData<Boolean> navInfo = mViewModel.getInfoNav();
        navInfo.observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if(aBoolean) {
                    navigateToShoesInfo();
                    mViewModel.eventNavToInfoFinished();
                }
            }
        });

        return shoesListFragmentBinding.getRoot();
    }

    private void navigateToShoesInfo(){
        Bundle bundle_info = new Bundle();
        bundle_info.putString("model",shoe_info.getModelName().toString());
        bundle_info.putString("company",shoe_info.getBrandName().toString());
        bundle_info.putString("image",shoe_info.getImage().toString());
        bundle_info.putInt("id",shoe_info.getId());
        bundle_info.putDouble("factor",shoe_info.getFactor());
        bundle_info.putBoolean("is",true);
        bundle_info.putStringArrayList("base_info",base_info);
        NavHostFragment.findNavController(this).navigate(R.id.action_shoesListFragment_to_shoeInfoFragment,bundle_info);
    }

}
