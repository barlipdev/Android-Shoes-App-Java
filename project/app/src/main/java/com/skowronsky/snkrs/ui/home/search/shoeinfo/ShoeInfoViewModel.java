package com.skowronsky.snkrs.ui.home.search.shoeinfo;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.skowronsky.snkrs.database.BaseShoes;
import com.skowronsky.snkrs.database.Favorite;
import com.skowronsky.snkrs.database.FavoriteShoes;
import com.skowronsky.snkrs.database.Shoes;
import com.skowronsky.snkrs.repository.Repository;
import com.skowronsky.snkrs.storage.NavigationStorage;

import java.util.List;

public class ShoeInfoViewModel extends AndroidViewModel {

    private LiveData<List<FavoriteShoes>> favoriteShoesLiveData;
    private Repository repository;
    public Shoes shoe;
    public BaseShoes baseShoe;
    public Double prefer_size;
    private NavigationStorage navigationStorage;
    private Favorite favorite;

    public ShoeInfoViewModel(@NonNull Application application) {
        super(application);
        this.repository = new Repository(application);
        this.favoriteShoesLiveData = repository.getAllFavoriteShoes();
        this.navigationStorage = NavigationStorage.getInstance();
        shoe = navigationStorage.getShoe();
        baseShoe = navigationStorage.getBaseShoe();
        //TODO BaseShoes 1.7
        //prefer_size = shoe.factor + baseShoe.base.size;
    }

    LiveData<List<FavoriteShoes>> getFavoriteShoesLiveData(){
        return favoriteShoesLiveData;
    }

    /**
     * Metoda usuwająca buta z ulubionych, danego buta zaciąga z instancji NavigationStorage
     */
    public void deleteFavoriteShoe(){
        favorite = new Favorite(this.shoe,prefer_size,navigationStorage.getBaseShoe().base.idBase);
        for (int i=0;i<favoriteShoesLiveData.getValue().size();i++){
            if (favoriteShoesLiveData.getValue().get(i).shoes.idShoes == favorite.idShoes && favoriteShoesLiveData.getValue().get(i).favorite.size == favorite.size){
                repository.deleteFavorite(favoriteShoesLiveData.getValue().get(i).favorite);
            }
        }
    }

    /**
     * Metoda dodająca buta do ulubionych, danego buta zaciąga z instancji NavigationStorage
     */
    public void addFavoriteShoe(){
        favorite = new Favorite(this.shoe,prefer_size,navigationStorage.getBaseShoe().base.idBase);
        repository.insertFavorite(favorite);
    }

    /**
     * Metoda sprawdzająca czy dany but o danym rozmiarze znajduje się w ulubionych
     * @param favoriteShoes lista ulubionych butów posiadanych przez użytkownika
     * @return zwraca true jeśli dany but jest w ulubionych, w innym przypadku zwróci false
     */
    public boolean checkShoe(List<FavoriteShoes> favoriteShoes){
        favorite = new Favorite(this.shoe,prefer_size,navigationStorage.getBaseShoe().base.idBase);
        for(int j=0;j<favoriteShoes.size();j++){
            if (favoriteShoes.get(j).shoes.idShoes == favorite.idShoes && favoriteShoes.get(j).favorite.size == favorite.size){
                return true;
            }
        }
        return false;
    }

    /**
     * Metoda która aktualizuje liste ulubionych butów
     * @param favoriteShoesList lista ulubionych butów
     */
    //TODO update favorite in shoes
    public void updateFavorite(List<FavoriteShoes> favoriteShoesList){
        //snkrsClient.updateFavorite(storage.getUser().getEmail(),favoriteShoesList);
    }



}
