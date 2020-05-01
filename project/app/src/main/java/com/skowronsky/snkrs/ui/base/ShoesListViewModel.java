package com.skowronsky.snkrs.ui.base;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.skowronsky.snkrs.model.Shoes;
import com.skowronsky.snkrs.repository.Repository;
import com.skowronsky.snkrs.storage.Storage;

import java.util.ArrayList;
import java.util.List;

public class ShoesListViewModel extends AndroidViewModel {

    public MutableLiveData<com.skowronsky.snkrs.database.Shoes> shoe_info;
    private LiveData<List<com.skowronsky.snkrs.database.Shoes>> allShoes;
    private MutableLiveData<Boolean> InfoNav;
    private Repository repository;

    public ShoesListViewModel(@NonNull Application application) {
        super(application);
        this.repository = new Repository(application);
        this.allShoes = repository.getAllShoes();
    }


    public MutableLiveData<Boolean> getInfoNav(){
        if (InfoNav == null)
            InfoNav = new MutableLiveData<Boolean>();
        return InfoNav;
    }

    public MutableLiveData<com.skowronsky.snkrs.database.Shoes> getEventShoeInfo(){
        if (shoe_info == null)
            shoe_info = new MutableLiveData<com.skowronsky.snkrs.database.Shoes>();
        return shoe_info;
    }

    public LiveData<List<com.skowronsky.snkrs.database.Shoes>> getAllShoes(){return allShoes;}


    public void eventNavToInfo(){
        InfoNav.setValue(true);
    }
    public void eventNavToInfoFinished(){
        InfoNav.setValue(false);
    }
    public void eventSendShoe(com.skowronsky.snkrs.database.Shoes shoe){shoe_info.setValue(shoe);}


}
