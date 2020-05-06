package com.skowronsky.snkrs.ui.home.add.shoeinfo;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.skowronsky.snkrs.database.Base;
import com.skowronsky.snkrs.repository.Repository;
import com.skowronsky.snkrs.storage.NavigationStorage;

public class ShoeInfoViewModel extends AndroidViewModel {

    private MutableLiveData<Boolean> navToBase;
    private Repository repository;
    private NavigationStorage navigationStorage;
    private Base base;
    public String shoe_model;

    public ShoeInfoViewModel(Application application){
        super(application);
        repository = new Repository(application);
        this.navigationStorage = NavigationStorage.getInstance();
        shoe_model = navigationStorage.getShoe().modelName;
    }

    public MutableLiveData<Boolean> getNavToBase(){
        if (navToBase == null)
            navToBase = new MutableLiveData<Boolean>();
        return navToBase;
    }

    public void addShoeToBase(double size){
        base = new Base(navigationStorage.getShoe(),size);
        repository.insertBase(base);
    }

    public void eventNavToInfo(){
        navToBase.setValue(true);
    }
    public void eventNavToInfoFinished(){
        navToBase.setValue(false);
    }


}
