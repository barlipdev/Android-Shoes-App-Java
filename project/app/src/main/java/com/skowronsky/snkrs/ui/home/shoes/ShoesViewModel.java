package com.skowronsky.snkrs.ui.home.shoes;

import android.icu.text.IDNA;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.skowronsky.snkrs.Company;
import com.skowronsky.snkrs.storage.Storage;

import java.util.ArrayList;
import java.util.List;

public class ShoesViewModel extends ViewModel {

    private MutableLiveData<ArrayList<com.skowronsky.snkrs.model.Shoes>> ShoesLiveData;
    public ArrayList<Shoes> ShoesArrayList;
    public ArrayList<com.skowronsky.snkrs.model.Shoes> shoesListTmp;
    public ArrayList<com.skowronsky.snkrs.model.Shoes> shoesList;
    public ArrayList<Shoes> ShoesArrayListTmp;
    public MutableLiveData<com.skowronsky.snkrs.model.Shoes> shoe_info;
    private MutableLiveData<Boolean> InfoNav;
    private Storage storage;
    private String name;
    private String company;


    public ShoesViewModel(Storage storage,String company) {
        this.storage = storage;
        this.company = company;
        ShoesLiveData = new MutableLiveData<>();

        shoesList = new ArrayList<>();
        shoesListTmp = new ArrayList<>();

        for(int i=0;i<storage.getShoesList().size();i++){
            shoesListTmp.add(storage.getShoesList().get(i));
        }
        for(int i=0;i<shoesListTmp.size();i++){
            this.name = shoesListTmp.get(i).getBrandName();
            if (name.equals(company)){
                shoesList.add(shoesListTmp.get(i));
            }
        }

    }

    public MutableLiveData<ArrayList<com.skowronsky.snkrs.model.Shoes>> getShoesLiveData()
    {
        if (ShoesLiveData == null)
            ShoesLiveData = new MutableLiveData<>();
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

    public void init(String company){
        //ShoesList(company);
        Log.i("NameCompany3",company);
        ShoesLiveData.setValue(shoesList);
    }

    public void eventNavToInfo(){
        InfoNav.setValue(true);
    }
    public void eventNavToInfoFinished(){
        InfoNav.setValue(false);
    }
    public void eventSendShoe(com.skowronsky.snkrs.model.Shoes shoe){shoe_info.setValue(shoe);}

    public void ShoesList(String company){
        Shoes shoes = new Shoes();
        Shoes shoes2 = new Shoes();


        shoes.setModel("AirMax");
        shoes.setNumber(40);
        shoes.setShoe_company("Nike");

        shoes2.setModel("Orginals");
        shoes2.setNumber(40);
        shoes2.setShoe_company("Adidas");

        ShoesArrayList = new ArrayList<>();
        ShoesArrayListTmp = new ArrayList<>();

        ShoesArrayListTmp.add(shoes);
        ShoesArrayListTmp.add(shoes2);

        for(int i=0;i<ShoesArrayListTmp.size();i++){
            name = ShoesArrayListTmp.get(i).getShoe_company();
            if (name.equals(company)){
                ShoesArrayList.add(ShoesArrayListTmp.get(i));
            }
        }




    }

}
