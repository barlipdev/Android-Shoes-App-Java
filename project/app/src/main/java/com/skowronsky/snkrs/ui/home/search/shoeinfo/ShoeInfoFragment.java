package com.skowronsky.snkrs.ui.home.search.shoeinfo;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.skowronsky.snkrs.R;
import com.skowronsky.snkrs.database.FavoriteShoes;
import com.skowronsky.snkrs.databinding.FragmentSearchShoeInfoBinding;
import com.skowronsky.snkrs.storage.NavigationStorage;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ShoeInfoFragment extends Fragment {

    private ShoeInfoViewModel mViewModel;
    private FragmentSearchShoeInfoBinding shoeInfoFragmentBinding;
    private NavigationStorage navigationStorage;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        navigationStorage = NavigationStorage.getInstance();
        mViewModel = new ViewModelProvider(this).get(ShoeInfoViewModel.class);
        shoeInfoFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_search_shoe_info, container, false);
        shoeInfoFragmentBinding.setShoesInfoViewModel(mViewModel);
        shoeInfoFragmentBinding.setLifecycleOwner(this);

        Picasso.with(getContext()).load(navigationStorage.getBaseShoe().brandShoes.shoes.getImage()).into(shoeInfoFragmentBinding.shoeIcon);
        Picasso.with(getContext()).load(navigationStorage.getShoe().getImage()).into(shoeInfoFragmentBinding.imageView);

        mViewModel.getFavoriteShoesLiveData().observe(getViewLifecycleOwner(), new Observer<List<FavoriteShoes>>() {
            @Override
            public void onChanged(List<FavoriteShoes> favoriteShoes) {
                if(mViewModel.checkShoe(favoriteShoes)){
                    shoeInfoFragmentBinding.favoriteStar.setChecked(true);
                }

                mViewModel.updateFavorite(favoriteShoes);
            }
        });

        shoeInfoFragmentBinding.favoriteStar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(shoeInfoFragmentBinding.favoriteStar.isChecked()){
                    mViewModel.addFavoriteShoe();
                }
                else{
                    mViewModel.deleteFavoriteShoe();
                }
            }
        });

        return shoeInfoFragmentBinding.getRoot();
    }

}
