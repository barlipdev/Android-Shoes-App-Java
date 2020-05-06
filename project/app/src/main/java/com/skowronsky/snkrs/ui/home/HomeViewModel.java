package com.skowronsky.snkrs.ui.home;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.skowronsky.snkrs.SnkrsClient;
import com.skowronsky.snkrs.database.BaseShoes;
import com.skowronsky.snkrs.model.Shoes;
import com.skowronsky.snkrs.repository.Repository;
import com.skowronsky.snkrs.storage.Storage;

import java.util.ArrayList;
import java.util.List;

public class HomeViewModel extends AndroidViewModel {

    private MutableLiveData<Boolean> homeNav;
    private MutableLiveData<Boolean> infoNav;
    private MutableLiveData<ArrayList<Shoes>> ShoesLiveData;
    private MutableLiveData<ArrayList<String>> BaseLiveData;
    private ArrayList<Shoes> shoesList;
    private LiveData<List<BaseShoes>> allBaseShoes;
    private Repository repository;

    private Storage storage;
    private SnkrsClient snkrsClient;

    public HomeViewModel(Application application){
        super(application);
        repository = new Repository(application);
        allBaseShoes = repository.getAllBaseShoes();
        this.BaseLiveData = new MutableLiveData<ArrayList<String>>();
        this.shoesList = new ArrayList<Shoes>();

        storage = Storage.getInstance();
        snkrsClient = SnkrsClient.getInstance(storage,application);
    }

    public void updateBaseShoes(List<BaseShoes> baseShoesList){
        snkrsClient.updateBase(storage.getUser().getEmail(),baseShoesList);
    }

    public MutableLiveData<Boolean> getInfoNav(){
        if (infoNav == null)
            infoNav = new MutableLiveData<Boolean>();
        return infoNav;
    }

    public MutableLiveData<Boolean> getEventHomeNav(){
        if(homeNav == null)
            homeNav = new MutableLiveData<Boolean>();
        return homeNav;
    }

    public LiveData<ArrayList<Shoes>> getShoesLiveData()
    {
        if (ShoesLiveData == null)
            ShoesLiveData = new MutableLiveData<>();
        return ShoesLiveData;
    }

    LiveData<List<BaseShoes>> getAllBaseShoes() {return allBaseShoes;}


    public void eventNavToHome(){ homeNav.setValue(true);
    }
    public void eventNavToHomeFinished(){
        homeNav.setValue(false);
    }
    public void eventNavToInfo(){infoNav.setValue(true);}
    public void eventNavToInfoFinished(){infoNav.setValue(false);}
    public void eventBaseSet(ArrayList<String> base_info){BaseLiveData.setValue(base_info);}

    public void deleteBaseShoes(com.skowronsky.snkrs.database.Shoes shoe, double size){
        String model_name = shoe.modelName;
        for (int i = 0; i < allBaseShoes.getValue().size(); i++){
                        if (model_name.equals(allBaseShoes.getValue().get(i).shoes.modelName) && size == allBaseShoes.getValue().get(i).base.size){
                                repository.delete(allBaseShoes.getValue().get(i).base);
                }
        }
    }

}