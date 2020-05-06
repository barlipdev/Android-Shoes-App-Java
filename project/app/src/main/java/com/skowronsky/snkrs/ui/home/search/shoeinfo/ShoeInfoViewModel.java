package com.skowronsky.snkrs.ui.home.search.shoeinfo;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.skowronsky.snkrs.SnkrsClient;
import com.skowronsky.snkrs.database.BaseShoes;
import com.skowronsky.snkrs.database.Favorite;
import com.skowronsky.snkrs.database.FavoriteShoes;
import com.skowronsky.snkrs.database.Shoes;
import com.skowronsky.snkrs.repository.Repository;
import com.skowronsky.snkrs.storage.NavigationStorage;
import com.skowronsky.snkrs.storage.Storage;

import java.util.List;

public class ShoeInfoViewModel extends AndroidViewModel {

    private LiveData<List<FavoriteShoes>> favoriteShoesLiveData;
    private Repository repository;
    public Shoes shoe;
    public BaseShoes baseShoe;
    public Double prefer_size;
    private NavigationStorage navigationStorage;
    private Favorite favorite;

    private Storage storage;
    private SnkrsClient snkrsClient;

    public ShoeInfoViewModel(@NonNull Application application) {
        super(application);
        this.repository = new Repository(application);
        this.favoriteShoesLiveData = repository.getAllFavoriteShoes();
        this.navigationStorage = NavigationStorage.getInstance();
        shoe = navigationStorage.getShoe();
        baseShoe = navigationStorage.getBaseShoe();
        prefer_size = shoe.factor + baseShoe.base.size;

        storage = Storage.getInstance();
        snkrsClient = SnkrsClient.getInstance(storage,application);
    }

    LiveData<List<FavoriteShoes>> getFavoriteShoesLiveData(){
        return favoriteShoesLiveData;
    }

    public void deleteFavoriteShoe(){
        favorite = new Favorite(this.shoe,prefer_size);
        for (int i=0;i<favoriteShoesLiveData.getValue().size();i++){
            if (favoriteShoesLiveData.getValue().get(i).shoes.id_shoes == favorite.id_shoes && favoriteShoesLiveData.getValue().get(i).favorite.size == favorite.size){
                repository.deleteFavorite(favoriteShoesLiveData.getValue().get(i).favorite);
            }
        }
    }

    public void addFavoriteShoe(){
        favorite = new Favorite(this.shoe,prefer_size);
        repository.insertFavorite(favorite);
    }

    public boolean checkShoe(List<FavoriteShoes> favoriteShoes){
        favorite = new Favorite(this.shoe,prefer_size);
        for(int j=0;j<favoriteShoes.size();j++){
            if (favoriteShoes.get(j).shoes.id_shoes == favorite.id_shoes && favoriteShoes.get(j).favorite.size == favorite.size){
                return true;
            }
        }
        return false;
    }

    public void updateFavorite(List<FavoriteShoes> favoriteShoesList){
        snkrsClient.updateFavorite(storage.getUser().getEmail(),favoriteShoesList);
    }



}
