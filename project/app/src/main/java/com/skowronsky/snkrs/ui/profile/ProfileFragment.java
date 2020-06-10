package com.skowronsky.snkrs.ui.profile;

import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
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
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.skowronsky.snkrs.R;
import com.skowronsky.snkrs.adapter.OnSwipeButtonClickListener;
import com.skowronsky.snkrs.adapter.SwipeHelper;
import com.skowronsky.snkrs.database.FavoriteShoes;
import com.skowronsky.snkrs.databinding.FragmentProfileBinding;

import java.util.List;

public class ProfileFragment extends Fragment{

    private ProfileViewModel viewModel;
    private FragmentProfileBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        viewModel = new ViewModelProvider(this).get(ProfileViewModel.class);
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container, false);
        binding.setProfileViewModel(viewModel);
        binding.setLifecycleOwner(this);

        RecyclerView recyclerView = binding.favShoesRecyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);

        FavoriteShoesAdapter favoriteShoesAdapter = new FavoriteShoesAdapter();
        recyclerView.setAdapter(favoriteShoesAdapter);

//        ItemTouchHelperAdapter itemTouchHelperCallback = new ItemTouchHelperAdapter(0, ItemTouchHelper.START | ItemTouchHelper.END);
//        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(itemTouchHelperCallback);
//        itemTouchHelper.attachToRecyclerView(recyclerView);

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
//                                viewModel.deleteFavorite();
                                favoriteShoesAdapter.delete(pos,viewModel);
                            }
                        }));
            }
        };


        viewModel.getAllFavoriteShoes().observe(getViewLifecycleOwner(), new Observer<List<FavoriteShoes>>() {
            @Override
            public void onChanged(List<FavoriteShoes> favoriteShoes) {
                favoriteShoesAdapter.setFavoriteShoesList(favoriteShoes);

                viewModel.updateFavorite(favoriteShoes);

//                for (FavoriteShoes fav :
//                        favoriteShoes) {
//
//                    Log.i("Favorite", String.valueOf(fav.favorite.id_base));
////                    Log.i("Favorite", String.valueOf(fav.baseShoes.shoes.modelName));
//
//                }
            }
        });

        final LiveData<Boolean> natToSettings = viewModel.getEventSettingsNav();
        natToSettings.observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if(aBoolean){
                    navigateToSettings();
                    viewModel.eventNavToSettingsFinished();
                }
            }
        });

        return binding.getRoot();
    }

    private void navigateToSettings(){
        NavHostFragment.findNavController(this).navigate(R.id.action_navigation_profile_to_settingsFragment);
    }



}