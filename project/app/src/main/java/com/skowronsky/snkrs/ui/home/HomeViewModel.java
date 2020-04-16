package com.skowronsky.snkrs.ui.home;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.skowronsky.snkrs.Company;
import com.skowronsky.snkrs.MyApplication;
import com.skowronsky.snkrs.model.Brand;
import com.skowronsky.snkrs.storage.Storage;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentMap;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<ArrayList<Brand>> CompanyLiveData;
    private MutableLiveData<Boolean> ShoesNav;
    private MutableLiveData<String> CompanyName;
    public ArrayList<Brand> CompanyArrayList;
    private Storage storage;

    public HomeViewModel(Storage storage){
        this.storage = storage;
        CompanyLiveData = new MutableLiveData<>();
        CompanyArrayList = new ArrayList<>();

            for(int i=0;i<this.storage.getBrandList().size();i++) {
                Brand com = new Brand(this.storage.getBrandList().get(i).getId(),this.storage.getBrandList().get(i).getName(),this.storage.getBrandList().get(i).getImage());
                CompanyArrayList.add(com);
            }


    }

    public MutableLiveData<ArrayList<Brand>> getCompanyLiveData()
    {
        if (CompanyLiveData == null)
                CompanyLiveData = new MutableLiveData<>();
        return CompanyLiveData;
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

    public void init(){
        CompanyLiveData.setValue(CompanyArrayList);
    }
}