package com.skowronsky.snkrs.ui.home.shoes;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.skowronsky.snkrs.Company;

import java.util.ArrayList;

public class ShoesViewModel extends ViewModel {

    private MutableLiveData<ArrayList<Shoes>> ShoesLiveData;
    public ArrayList<Shoes> ShoesArrayList;
    public ArrayList<Shoes> ShoesArrayListTmp;


    public ShoesViewModel() {
        ShoesLiveData = new MutableLiveData<>();
    }

    public MutableLiveData<ArrayList<Shoes>> getShoesLiveData()
    {
        if (ShoesLiveData == null)
            ShoesLiveData = new MutableLiveData<>();
        return ShoesLiveData;
    }

    public void init(String company){
        ShoesList(company);
        ShoesLiveData.setValue(ShoesArrayList);
    }

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
