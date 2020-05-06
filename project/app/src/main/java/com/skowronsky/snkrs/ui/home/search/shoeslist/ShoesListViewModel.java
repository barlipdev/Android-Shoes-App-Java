package com.skowronsky.snkrs.ui.home.search.shoeslist;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.skowronsky.snkrs.database.BaseShoes;
import com.skowronsky.snkrs.repository.Repository;
import com.skowronsky.snkrs.storage.NavigationStorage;

import java.util.List;

public class ShoesListViewModel extends AndroidViewModel {

    private LiveData<List<com.skowronsky.snkrs.database.Shoes>> allShoes;
    private MutableLiveData<Boolean> InfoNav;
    private Repository repository;
    private NavigationStorage navigationStorage;
    public BaseShoes baseShoe;

    public ShoesListViewModel(@NonNull Application application) {
        super(application);
        this.repository = new Repository(application);
        this.allShoes = repository.getAllShoes();
        this.navigationStorage = NavigationStorage.getInstance();
        baseShoe = navigationStorage.getBaseShoe();
    }

    public MutableLiveData<Boolean> getInfoNav(){
        if (InfoNav == null)
            InfoNav = new MutableLiveData<Boolean>();
        return InfoNav;
    }

    public LiveData<List<com.skowronsky.snkrs.database.Shoes>> getAllShoes(){return allShoes;}


    public void eventNavToInfo(){
        InfoNav.setValue(true);
    }
    public void eventNavToInfoFinished(){
        InfoNav.setValue(false);
    }
    public void eventSendShoe(com.skowronsky.snkrs.database.Shoes shoe){
        navigationStorage.setShoe(shoe);
    }


}
