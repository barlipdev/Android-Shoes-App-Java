package com.skowronsky.snkrs.ui.home.shoes;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.skowronsky.snkrs.database.Base;
import com.skowronsky.snkrs.model.Shoes;
import com.skowronsky.snkrs.repository.Repository;

import java.util.ArrayList;

public class ShoesInformationViewModel extends AndroidViewModel {

    private MutableLiveData<Boolean> navToBase;
    private Repository repository;

    public ShoesInformationViewModel(Application application){
        super(application);
        repository = new Repository(application);
    }

    public MutableLiveData<Boolean> getNavToBase(){
        if (navToBase == null)
            navToBase = new MutableLiveData<Boolean>();
        return navToBase;
    }

    public void addShoeToBase(Shoes shoe, double size){

        com.skowronsky.snkrs.database.Shoes dbshoe = new com.skowronsky.snkrs.database.Shoes();
        Base base;

        dbshoe.brand_name = shoe.getBrandName();
        dbshoe.factor = shoe.getFactor();
        dbshoe.id_shoes = shoe.getId();
        dbshoe.image = shoe.getImage();
        dbshoe.modelName = shoe.getModelName();

        base = new Base(dbshoe,size);
        repository.insertBase(base);
    }



    public void eventNavToInfo(){
        navToBase.setValue(true);
    }
    public void eventNavToInfoFinished(){
        navToBase.setValue(false);
    }


}
