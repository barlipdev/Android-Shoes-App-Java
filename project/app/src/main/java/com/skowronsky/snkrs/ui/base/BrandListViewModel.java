package com.skowronsky.snkrs.ui.base;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.skowronsky.snkrs.database.BaseShoes;
import com.skowronsky.snkrs.model.Brand;
import com.skowronsky.snkrs.repository.Repository;
import com.skowronsky.snkrs.storage.Storage;

import java.util.ArrayList;
import java.util.List;

public class BrandListViewModel extends AndroidViewModel {
    private MutableLiveData<Boolean> ShoesNav;
    private MutableLiveData<String> CompanyName;
    private LiveData<List<com.skowronsky.snkrs.database.Brand>> allBrands;
    private Repository repository;


    public BrandListViewModel(Application application){
        super(application);
        repository = new Repository(application);
        allBrands = repository.getAllBrands();
    }

    public MutableLiveData<Boolean> getEventShoesNav(){
        if(ShoesNav == null)
            ShoesNav = new MutableLiveData<Boolean>();
        return ShoesNav;
    }

    public MutableLiveData<String> getEventCompanyName(){
        if (CompanyName == null)
            CompanyName = new MutableLiveData<String>();
        return CompanyName;
    }

    LiveData<List<com.skowronsky.snkrs.database.Brand>> getAllBrands() {return allBrands;}

    public void eventNavToShoes(){
        ShoesNav.setValue(true);
    }
    public void eventNavToShoesFinished(){
        ShoesNav.setValue(false);
    }

    public void eventCompanyName(String name){ CompanyName.setValue(name);}

}
