package com.skowronsky.snkrs.ui.dashboard;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.skowronsky.snkrs.database.Base;
import com.skowronsky.snkrs.database.Brand;
import com.skowronsky.snkrs.database.Shoes;
import com.skowronsky.snkrs.repository.Repository;

import java.util.List;

public class DashboardViewModel extends AndroidViewModel {

    private MutableLiveData<Boolean> eventConnect;
    private MutableLiveData<Boolean> eventDisconnect;

    private Repository repository;
    private LiveData<List<Shoes>> allShoes;
    private LiveData<List<Base>> allBases;

    public List<Shoes> shoesList = null;

    public String siema = "siema";

    public DashboardViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository(application);
        allShoes = repository.getAllShoes();
        allBases = repository.getmAllBase();

    }

    public MutableLiveData<Boolean> getEventConnect(){
        if(eventConnect == null)
            eventConnect = new MutableLiveData<Boolean>();
        return eventConnect;
    }

    public void setEventConnect(){
        eventConnect.setValue(true);
    }
    public void connectFinished(){
        eventConnect.setValue(false);
    }

    public LiveData<List<Shoes>> getAllShoes(){
        return allShoes;
    }
    public LiveData<List<Base>> getAllBases(){
        return allBases;
    }

    public MutableLiveData<Boolean> getEventDisconnect() {
        if(eventDisconnect == null)
            eventDisconnect = new MutableLiveData<Boolean>();
        return eventDisconnect;
    }

    public LiveData<Shoes> getShoes(int shoesId){
        return repository.getShoes(shoesId);
    }

    public void insert(Base base){
        repository.insertBase(base);
    }

    public void insert(Brand brand){
        repository.insertBrand(brand);
    }
    public void deleteBrand(Brand brand){
        repository.delete(brand);
    }

    public void deleteAllBrand(){
        repository.deleteAllBrands();
    }

    public void deleteAllShoes(){
        repository.deleteAllShoes();
    }

    public void setEventDisconnect(){
        eventDisconnect.setValue(true);
    }
    public void disconnectFinished(){
        eventDisconnect.setValue(false);
    }
}