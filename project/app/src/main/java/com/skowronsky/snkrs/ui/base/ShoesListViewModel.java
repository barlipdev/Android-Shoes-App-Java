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

    private MutableLiveData<ArrayList<Shoes>> ShoesLiveData;
    public ArrayList<com.skowronsky.snkrs.model.Shoes> shoesListTmp;
    public ArrayList<com.skowronsky.snkrs.model.Shoes> shoesList;
    public MutableLiveData<com.skowronsky.snkrs.model.Shoes> shoe_info;
    private LiveData<List<com.skowronsky.snkrs.database.Shoes>> allShoes;
    private MutableLiveData<Boolean> InfoNav;
    private String name;
    private String brand;
    private Repository repository;

    public ShoesListViewModel(@NonNull Application application) {
        super(application);
        this.repository = new Repository(application);
        this.allShoes = repository.getAllShoes();
        ShoesLiveData = new MutableLiveData<>();
        shoesList = new ArrayList<>();
        shoesListTmp = new ArrayList<>();
    }

    public MutableLiveData<ArrayList<Shoes>> getShoesLiveData(){
        if(ShoesLiveData == null)
            ShoesLiveData = new MutableLiveData<ArrayList<Shoes>>();
        return ShoesLiveData;
    }

    public MutableLiveData<Boolean> getInfoNav(){
        if (InfoNav == null)
            InfoNav = new MutableLiveData<Boolean>();
        return InfoNav;
    }

    public MutableLiveData<com.skowronsky.snkrs.model.Shoes> getEventShoeInfo(){
        if (shoe_info == null)
            shoe_info = new MutableLiveData<com.skowronsky.snkrs.model.Shoes>();
        return shoe_info;
    }

    public LiveData<List<com.skowronsky.snkrs.database.Shoes>> getAllShoes(){return allShoes;}

    public void init_shoes_to_list(List<com.skowronsky.snkrs.database.Shoes> shoes,String brand){
        Shoes shoe;
        this.brand = brand;
        for(int i=0;i<shoes.size();i++){
            shoe = new Shoes(shoes.get(i).id_shoes,shoes.get(i).brand_name,shoes.get(i).modelName,shoes.get(i).factor,shoes.get(i).image);
            shoesListTmp.add(shoe);
        }
        for(int i=0;i<shoesListTmp.size();i++){
            this.name = shoesListTmp.get(i).getBrandName();
            if (name.equals(brand)){
                shoesList.add(shoesListTmp.get(i));
            }
        }
        ShoesLiveData.setValue(shoesList);
    }

    public void init(String brand){
        this.brand = brand;
    }

    public void eventNavToInfo(){
        InfoNav.setValue(true);
    }
    public void eventNavToInfoFinished(){
        InfoNav.setValue(false);
    }
    public void eventSendShoe(com.skowronsky.snkrs.model.Shoes shoe){shoe_info.setValue(shoe);}


}