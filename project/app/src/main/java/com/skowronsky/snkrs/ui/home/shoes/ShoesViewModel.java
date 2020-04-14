package com.skowronsky.snkrs.ui.home.shoes;

import android.icu.text.IDNA;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.skowronsky.snkrs.Company;

import java.util.ArrayList;

public class ShoesViewModel extends ViewModel {

    private MutableLiveData<ArrayList<Shoes>> ShoesLiveData;
    public ArrayList<Shoes> ShoesArrayList;
    public ArrayList<Shoes> ShoesArrayListTmp;
    public MutableLiveData<Shoes> shoe_info;
    private MutableLiveData<Boolean> InfoNav;


    public ShoesViewModel() {
        ShoesLiveData = new MutableLiveData<>();
    }

    public MutableLiveData<ArrayList<Shoes>> getShoesLiveData()
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

    public MutableLiveData<Shoes> getEventShoeInfo(){
        if (shoe_info == null)
            shoe_info = new MutableLiveData<Shoes>();
        return shoe_info;
    }

    public void init(String company){
        ShoesList(company);
        ShoesLiveData.setValue(ShoesArrayList);
    }

    public void eventNavToInfo(){
        InfoNav.setValue(true);
    }
    public void eventNavToInfoFinished(){
        InfoNav.setValue(false);
    }
    public void eventSendShoe(Shoes shoe){shoe_info.setValue(shoe);}

    public void ShoesList(String company){
        Shoes shoes = new Shoes();
        Shoes shoes2 = new Shoes();
        String name;

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
