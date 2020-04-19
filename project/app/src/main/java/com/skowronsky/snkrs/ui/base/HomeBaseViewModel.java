package com.skowronsky.snkrs.ui.base;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.skowronsky.snkrs.model.Shoes;
import com.skowronsky.snkrs.storage.Storage;

import java.util.ArrayList;

public class HomeBaseViewModel extends ViewModel {

    private Storage storage;
    private MutableLiveData<Boolean> homeNav;
    private MutableLiveData<ArrayList<Shoes>> ShoesLiveData;
    private ArrayList<Shoes> shoesList;
    private MutableLiveData<Boolean> areArguments;

    public HomeBaseViewModel(Storage storage){
        this.ShoesLiveData = new MutableLiveData<ArrayList<Shoes>>();
        this.storage = storage;
        this.shoesList = new ArrayList<Shoes>();
    }

    public MutableLiveData<Boolean> getEventHomeNav(){
        if(homeNav == null)
            homeNav = new MutableLiveData<Boolean>();
        return homeNav;
    }
    public MutableLiveData<Boolean> getAreArguments(){
        if(areArguments == null)
            areArguments = new MutableLiveData<Boolean>();
        return areArguments;
    }

    public MutableLiveData<ArrayList<Shoes>> getShoesLiveData()
    {
        if (ShoesLiveData == null)
            ShoesLiveData = new MutableLiveData<>();
        return ShoesLiveData;
    }

    public void init(ArrayList<Shoes> shoes){
        //addToList(shoe);
        ShoesLiveData.setValue(shoes);
    }

    private void addToList(Shoes shoe){
        shoesList.add(shoe);
    }

    public void eventNavToHome(){
        homeNav.setValue(true);
    }
    public void eventSetArgumentsTrue(){areArguments.setValue(true);}
    public void eventSetArgumentsFalse(){areArguments.setValue(false);}
    public void eventNavToHomeFinished(){
        homeNav.setValue(false);
    }

}
