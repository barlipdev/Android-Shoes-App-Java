package com.skowronsky.snkrs.ui.home;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.skowronsky.snkrs.database.Brand;
import com.skowronsky.snkrs.repository.Repository;

import java.util.List;

public class HomeViewModel extends AndroidViewModel {

    private LiveData<List<Brand>> allBrands;
    private MutableLiveData<Boolean> ShoesNav;
    private MutableLiveData<String> CompanyName;
    private Repository repository;

    public HomeViewModel(Application application){
        super(application);
        repository = new Repository(application);
        allBrands= repository.getAllBrands();
    }

    public LiveData<List<Brand>> getAllBrands(){
        return allBrands;
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

    public void eventNavToShoes(){
        ShoesNav.setValue(true);
    }
    public void eventNavToShoesFinished(){
        ShoesNav.setValue(false);
    }
    public void eventCompanyName(String name){ CompanyName.setValue(name);}
}