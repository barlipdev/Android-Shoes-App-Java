package com.skowronsky.snkrs.ui.home.search.shoeslist;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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
import com.skowronsky.snkrs.databinding.FragmentSearchShoesListBinding;
import com.skowronsky.snkrs.storage.NavigationStorage;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Objects;

public class ShoesListFragment extends Fragment {

    private ShoesListViewModel mViewModel;
    private FragmentSearchShoesListBinding shoesListFragmentBinding;
    private RecyclerView recyclerView;
    private ShoesListRecyclerViewAdapter<Context> recyclerViewAdapter;
    private NavigationStorage navigationStorage;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        navigationStorage = NavigationStorage.getInstance();
        mViewModel = new ViewModelProvider(this).get(ShoesListViewModel.class);

        shoesListFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_search_shoes_list, container, false);
        shoesListFragmentBinding.setShoesListViewModel(mViewModel);
        shoesListFragmentBinding.setLifecycleOwner(this);
        Picasso.with(getContext()).load(navigationStorage.getBaseShoe().brandShoes.shoes.getImage()).into(
                shoesListFragmentBinding.shoeIcon);

        recyclerView = new RecyclerView(Objects.requireNonNull(getActivity()));
        recyclerView = shoesListFragmentBinding.ShoesView;
        recyclerViewAdapter = new ShoesListRecyclerViewAdapter<>(getContext(),mViewModel);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(recyclerViewAdapter);

        mViewModel.getAllShoes().observe(getViewLifecycleOwner(), new Observer<List<com.skowronsky.snkrs.database.Shoes>>() {
            @Override
            public void onChanged(List<com.skowronsky.snkrs.database.Shoes> shoes) {
                recyclerViewAdapter.setAllShoes(shoes);
            }
        });

        shoesListFragmentBinding.searchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                    recyclerViewAdapter.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

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

    /**
     * Metoda odpowiedzialna za wykoanie nawigacji do danego fragmentu,
     * w tym przypadku do fragmentu z dokladnymi informacjami o bucie
     */
    private void navigateToShoesInfo(){
        NavHostFragment.findNavController(this).navigate(R.id.action_shoesListFragment_to_shoeInfoFragment);
    }

}
