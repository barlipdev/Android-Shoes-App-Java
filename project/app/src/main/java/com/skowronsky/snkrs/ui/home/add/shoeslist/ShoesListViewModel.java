package com.skowronsky.snkrs.ui.home.add.shoeslist;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.skowronsky.snkrs.database.Shoes;
import com.skowronsky.snkrs.repository.Repository;

import java.util.List;

public class ShoesListViewModel extends AndroidViewModel {

    private LiveData<List<Shoes>> allShoes;
    private Repository repository;
    private MutableLiveData<Boolean> InfoNav;



    public ShoesListViewModel(Application application) {
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

    public void eventNavToInfo(){
        InfoNav.setValue(true);
    }
    public void eventNavToInfoFinished(){
        InfoNav.setValue(false);
    }

}
