package com.skowronsky.snkrs.ui.profile;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.skowronsky.snkrs.database.BaseShoes;
import com.skowronsky.snkrs.database.FavoriteShoes;
import com.skowronsky.snkrs.repository.Repository;

import java.util.List;

public class ProfileViewModel extends AndroidViewModel {
    private Repository repository;

    private LiveData<List<FavoriteShoes>> allFavoriteShoes;


    private MutableLiveData<Boolean> settingsNav;
    public MutableLiveData<String> title = new MutableLiveData<String>("Profile") ;


    public ProfileViewModel(Application application){
        super(application);
        repository = new Repository(application);
        allFavoriteShoes = repository.getAllFavoriteShoes();
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

    public void eventNavToSettings(){
        settingsNav.setValue(true);
    }
    public void eventNavToSettingsFinished(){
        settingsNav.setValue(false);
    }

}