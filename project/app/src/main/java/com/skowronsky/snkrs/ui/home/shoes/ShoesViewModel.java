package com.skowronsky.snkrs.ui.home.shoes;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.skowronsky.snkrs.database.Shoes;
import com.skowronsky.snkrs.repository.Repository;

import java.util.List;

public class ShoesViewModel extends AndroidViewModel {

    private LiveData<List<Shoes>> allShoes;
    private Repository repository;
    private MutableLiveData<Shoes> shoe_info;
    private MutableLiveData<Boolean> InfoNav;



    public ShoesViewModel(Application application) {
        super(application);
        repository = new Repository(application);
        this.allShoes = repository.getAllShoes();
    }

    public LiveData<List<Shoes>> getAllShoes(){
        return allShoes;
    }

    public MutableLiveData<Boolean> getInfoNav(){
        if (InfoNav == null)
            InfoNav = new MutableLiveData<Boolean>();
        return InfoNav;
    }

    public MutableLiveData<Shoes> getEventShoeInfo(){
        if (shoe_info == null)
            shoe_info = new MutableLiveData<Shoes>();
        return shoe_info;
    }


    public void eventNavToInfo(){
        InfoNav.setValue(true);
    }
    public void eventNavToInfoFinished(){
        InfoNav.setValue(false);
    }
    public void eventSendShoe(Shoes shoe){shoe_info.setValue(shoe);}


}
