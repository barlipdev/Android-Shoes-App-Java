package com.skowronsky.snkrs.ui.base;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.skowronsky.snkrs.database.BaseShoes;
import com.skowronsky.snkrs.model.Shoes;
import com.skowronsky.snkrs.repository.Repository;

import java.util.ArrayList;
import java.util.List;

public class HomeBaseViewModel extends AndroidViewModel {

    private MutableLiveData<Boolean> homeNav;
    private MutableLiveData<Boolean> infoNav;
    private MutableLiveData<ArrayList<Shoes>> ShoesLiveData;
    private MutableLiveData<ArrayList<String>> BaseLiveData;
    private ArrayList<Shoes> shoesList;
    private LiveData<List<BaseShoes>> allBaseShoes;
    private Repository repository;

    public HomeBaseViewModel(Application application){
        super(application);
        repository = new Repository(application);
        allBaseShoes = repository.getAllBaseShoes();
        this.ShoesLiveData = new MutableLiveData<ArrayList<Shoes>>();
        this.BaseLiveData = new MutableLiveData<ArrayList<String>>();
        this.shoesList = new ArrayList<Shoes>();
    }

    public MutableLiveData<Boolean> getInfoNav(){
        if (infoNav == null)
            infoNav = new MutableLiveData<Boolean>();
        return infoNav;
    }

    public MutableLiveData<ArrayList<String>> getBaseLiveData(){
        if (BaseLiveData == null)
            BaseLiveData = new MutableLiveData<ArrayList<String>>();
        return BaseLiveData;
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

    public void init(List<BaseShoes> baseShoes){
        addBaseShoes(baseShoes);
        ShoesLiveData.setValue(shoesList);
    }

    public void refresh(ArrayList<Shoes> shoes){
        ShoesLiveData.setValue(shoes);
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
    public void eventNavToInfo(){infoNav.setValue(true);}
    public void eventNavToInfoFinished(){infoNav.setValue(false);}
    public void eventBaseSet(ArrayList<String> base_info){BaseLiveData.setValue(base_info);}

    public void addBaseShoes(List<BaseShoes> baseShoes) {
        Shoes shoe;
        if (shoesList.size() == 0) {
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

    public void deleteBaseShoes(Shoes shoe, double size){
        String model_name = shoe.getModelName();
        for (int i = 0; i < allBaseShoes.getValue().size(); i++){
                        if (model_name.equals(allBaseShoes.getValue().get(i).shoes.modelName) && size == allBaseShoes.getValue().get(i).base.size){
                                repository.delete(allBaseShoes.getValue().get(i).base);
                                Log.i("ROOM114", String.valueOf("Shoe: "+shoe.getModelName()));
                                Log.i("ROOM114", String.valueOf("Deleted: "+allBaseShoes.getValue().get(i).shoes.modelName));
                }
        }
    }

}