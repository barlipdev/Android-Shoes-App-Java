package com.skowronsky.snkrs.ui.base;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.skowronsky.snkrs.database.Favorite;
import com.skowronsky.snkrs.database.FavoriteShoes;
import com.skowronsky.snkrs.database.Shoes;
import com.skowronsky.snkrs.repository.Repository;

import java.util.List;

public class ShoeInfoViewModel extends AndroidViewModel {

    private LiveData<List<FavoriteShoes>> favoriteShoesLiveData;
    private Repository repository;

    public ShoeInfoViewModel(@NonNull Application application) {
        super(application);
        this.repository = new Repository(application);
        this.favoriteShoesLiveData = repository.getAllFavoriteShoes();
    }

    LiveData<List<FavoriteShoes>> getFavoriteShoesLiveData(){
        return favoriteShoesLiveData;
    }

    public void deleteFavoriteShoe(FavoriteShoes favoriteShoes){
        repository.deleteFavorite(favoriteShoes.favorite);
    }

    public void addFavoriteShoe(Favorite favorite){
        repository.insertFavorite(favorite);
    }

}
