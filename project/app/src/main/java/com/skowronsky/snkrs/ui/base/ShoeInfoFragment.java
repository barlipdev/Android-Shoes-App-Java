package com.skowronsky.snkrs.ui.base;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import com.like.LikeButton;
import com.like.OnLikeListener;
import com.skowronsky.snkrs.R;
import com.skowronsky.snkrs.database.Favorite;
import com.skowronsky.snkrs.database.FavoriteShoes;
import com.skowronsky.snkrs.database.Shoes;
import com.skowronsky.snkrs.databinding.ShoeInfoFragmentBinding;
import com.skowronsky.snkrs.ui.home.shoes.ShoesInformationViewModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ShoeInfoFragment extends Fragment {

    private ShoeInfoViewModel mViewModel;
    private ShoeInfoFragmentBinding shoeInfoFragmentBinding;
    private ArrayList<String> base_info;
    private Favorite favorite;
    private FavoriteShoes favoriteShoes;
    private List<FavoriteShoes> allFavoriteShoes;
    private Shoes shoes;
    private int shoe_id;
    private String shoe_model;
    private String shoe_image;
    private String shoe_brand;
    private double shoe_factor;
    private double prefer_size;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        mViewModel = new ViewModelProvider(this).get(ShoeInfoViewModel.class);
        shoeInfoFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.shoe_info_fragment, container, false);
        shoeInfoFragmentBinding.setShoesInfoViewModel(mViewModel);
        shoeInfoFragmentBinding.setLifecycleOwner(this);

        shoe_model = getArguments().getString("model");
        shoe_image = getArguments().getString("image");
        shoe_brand = getArguments().getString("company");
        shoe_factor = getArguments().getDouble("factor");
        shoe_id = getArguments().getInt("id");
        base_info = getArguments().getStringArrayList("base_info");
        Integer id_base = Integer.valueOf(base_info.get(4));
        Log.i("0011",base_info.get(4));

        prefer_size = Double.valueOf(base_info.get(2)) + Double.valueOf(shoe_factor);

        shoeInfoFragmentBinding.shoeComp.setText(base_info.get(0));
        shoeInfoFragmentBinding.shoeModel.setText(base_info.get(1));
        shoeInfoFragmentBinding.shoeSize.setText(base_info.get(2));
        Picasso.with(getContext()).load(base_info.get(3)).into(shoeInfoFragmentBinding.shoeIcon);
        shoeInfoFragmentBinding.modelInfo.setText(shoe_model);
        shoeInfoFragmentBinding.size.setText(String.valueOf(prefer_size));
        Picasso.with(getContext()).load(shoe_image).into(
                shoeInfoFragmentBinding.imageView);

        mViewModel.getFavoriteShoesLiveData().observe(getViewLifecycleOwner(), new Observer<List<FavoriteShoes>>() {
            @Override
            public void onChanged(List<FavoriteShoes> favoriteShoes) {
                allFavoriteShoes = favoriteShoes;
                checkShoe();
            }
        });

        setShoe();

        shoeInfoFragmentBinding.starButton.setOnLikeListener(new OnLikeListener() {
            @Override
            public void liked(LikeButton likeButton) {
                mViewModel.addFavoriteShoe(favorite);
            }

            @Override
            public void unLiked(LikeButton likeButton) {
                for (int i=0;i<allFavoriteShoes.size();i++){
                    if (allFavoriteShoes.get(i).shoes.modelName.equals(favoriteShoes.shoes.modelName)&&allFavoriteShoes.get(i).favorite.size == favoriteShoes.favorite.size){
                        mViewModel.deleteFavoriteShoe(allFavoriteShoes.get(i));
                    }
                }
            }
        });

        return shoeInfoFragmentBinding.getRoot();
    }

    public void setShoe(){
        shoes = new Shoes();
        shoes.id_shoes = shoe_id;
        shoes.modelName = shoe_model;
        shoes.brand_name = shoe_brand;
        shoes.image = shoe_image;
        shoes.factor = shoe_factor;
        favorite = new Favorite(shoes,prefer_size);

    }

    public void checkShoe(){
        for(int j=0;j<allFavoriteShoes.size();j++){
            if (allFavoriteShoes.get(j).shoes.modelName.equals(favoriteShoes.shoes.modelName)&&allFavoriteShoes.get(j).favorite.size == favoriteShoes.favorite.size){
                shoeInfoFragmentBinding.starButton.setLiked(true);
            }
        }
    }


}
