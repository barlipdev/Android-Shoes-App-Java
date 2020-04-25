package com.skowronsky.snkrs.ui.base;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.skowronsky.snkrs.database.Base;
import com.skowronsky.snkrs.database.BaseShoes;
import com.skowronsky.snkrs.model.Shoes;
import com.skowronsky.snkrs.repository.Repository;
import com.skowronsky.snkrs.storage.Storage;

import java.util.ArrayList;
import java.util.List;

public class HomeBaseViewModel extends AndroidViewModel {

    private MutableLiveData<Boolean> homeNav;
    private MutableLiveData<ArrayList<Shoes>> ShoesLiveData;
    private ArrayList<Shoes> shoesList;
    private LiveData<List<BaseShoes>> allBaseShoes;
    private Repository repository;

    public HomeBaseViewModel(Application application){
        super(application);
        repository = new Repository(application);
        allBaseShoes = repository.getAllBaseShoes();
        this.ShoesLiveData = new MutableLiveData<ArrayList<Shoes>>();
        this.shoesList = new ArrayList<Shoes>();
    }

    public MutableLiveData<Boolean> getEventHomeNav(){
        if(homeNav == null)
            homeNav = new MutableLiveData<Boolean>();
        return homeNav;
    }

    public MutableLiveData<ArrayList<Shoes>> getShoesLiveData()
    {
        if (ShoesLiveData == null)
            ShoesLiveData = new MutableLiveData<>();
        return ShoesLiveData;
    }

    public void init(List<BaseShoes> baseShoes){
        addBaseShoes(baseShoes);
        ShoesLiveData.setValue(shoesList);
    }

    LiveData<List<BaseShoes>> getAllBaseShoes() {return allBaseShoes;}

    private void addToList(Shoes shoe){
        shoesList.add(shoe);
    }

    public void eventNavToHome(){
        homeNav.setValue(true);
    }
    public void eventNavToHomeFinished(){
        homeNav.setValue(false);
    }

    public void addBaseShoes(List<BaseShoes> baseShoes) {
        Shoes shoe;
        for (int i = 0; i < baseShoes.size(); i++) {
                shoe = new Shoes(baseShoes.get(i).shoes.id_shoes,
                        baseShoes.get(i).shoes.brand_name,
                        baseShoes.get(i).shoes.modelName,
                        baseShoes.get(i).shoes.factor,
                        baseShoes.get(i).shoes.image
                );
                addToList(shoe);
        }
    }

}