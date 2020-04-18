package com.skowronsky.snkrs.ui.dashboard;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.skowronsky.snkrs.database.Brand;
import com.skowronsky.snkrs.repository.Repository;

public class DashboardViewModel extends AndroidViewModel {

    private MutableLiveData<Boolean> eventConnect;
    private MutableLiveData<Boolean> eventDisconnect;

    private Repository repository;
    public String siema = "siema";

    public DashboardViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository(application);


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

    public MutableLiveData<Boolean> getEventDisconnect() {
        if(eventDisconnect == null)
            eventDisconnect = new MutableLiveData<Boolean>();
        return eventDisconnect;
    }

    public void insert(Brand brand){
        repository.insertBrand(brand);
    }

    public void setEventDisconnect(){
        eventDisconnect.setValue(true);
    }
    public void disconnectFinished(){
        eventDisconnect.setValue(false);
    }
}