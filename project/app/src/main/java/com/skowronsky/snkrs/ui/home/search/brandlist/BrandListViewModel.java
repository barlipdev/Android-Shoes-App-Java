package com.skowronsky.snkrs.ui.home.search.brandlist;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.skowronsky.snkrs.database.BaseShoes;
import com.skowronsky.snkrs.repository.Repository;
import com.skowronsky.snkrs.storage.NavigationStorage;

import java.util.List;

public class BrandListViewModel extends AndroidViewModel {
    private MutableLiveData<Boolean> ShoesNav;
    private LiveData<List<com.skowronsky.snkrs.database.Brand>> allBrands;
    private Repository repository;
    private NavigationStorage navigationStorage;
    public BaseShoes baseShoe;


    public BrandListViewModel(Application application){
        super(application);
        repository = new Repository(application);
        allBrands = repository.getAllBrands();
        this.navigationStorage = NavigationStorage.getInstance();
        baseShoe = navigationStorage.getBaseShoe();
    }

    public MutableLiveData<Boolean> getEventShoesNav(){
        if(ShoesNav == null)
            ShoesNav = new MutableLiveData<Boolean>();
        return ShoesNav;
    }

    LiveData<List<com.skowronsky.snkrs.database.Brand>> getAllBrands() {return allBrands;}

    public void eventNavToShoes(){
        ShoesNav.setValue(true);
    }
    public void eventNavToShoesFinished(){
        ShoesNav.setValue(false);
    }


}
