package com.skowronsky.snkrs.ui.profile;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.skowronsky.snkrs.SnkrsClient;
import com.skowronsky.snkrs.database.BaseShoes;
import com.skowronsky.snkrs.database.Favorite;
import com.skowronsky.snkrs.database.FavoriteShoes;
import com.skowronsky.snkrs.repository.Repository;
import com.skowronsky.snkrs.storage.Storage;

import java.util.List;

public class ProfileViewModel extends AndroidViewModel {
    private Repository repository;

    private LiveData<List<FavoriteShoes>> allFavoriteShoes;


    private MutableLiveData<Boolean> settingsNav;
    public MutableLiveData<String> title = new MutableLiveData<String>("PROFILE") ;

    private Storage storage;
    private SnkrsClient snkrsClient;

    public ProfileViewModel(Application application){
        super(application);
        repository = new Repository(application);
        allFavoriteShoes = repository.getAllFavoriteShoes();

        storage = Storage.getInstance();
        snkrsClient = SnkrsClient.getInstance(storage,application);
    }

    LiveData<List<FavoriteShoes>> getAllFavoriteShoes() {return allFavoriteShoes;}

    public MutableLiveData<Boolean> getEventSettingsNav(){
        if(settingsNav == null)
            settingsNav = new MutableLiveData<Boolean>();
        return settingsNav;
    }

    public LiveData<BaseShoes> getBaseShoes(int idBase){
        return repository.getBaseShoes(idBase);
    }

    /**
     * Metoda zwana eventem która ustawia wartość LiveData na true w celu poinformowania obserwatora
     * aby wykonała sie nawigacja do danego fragmentu
     */
    public void eventNavToSettings(){
        settingsNav.setValue(true);
    }
    /**
     * Metoda zwana eventem która ustawia wartość LiveData na false w celu poinformowania obserwatora
     * że nawigacja do danego fragmentu się zakończyła
     */
    public void eventNavToSettingsFinished(){
        settingsNav.setValue(false);
    }

    public void deleteFavorite(Favorite favorite){
        repository.deleteFavorite(favorite);
    }

    public void updateFavorite(List<FavoriteShoes> favoriteShoesList){
        snkrsClient.updateFavorite(storage.getUser().getEmail(),favoriteShoesList);
    }
}