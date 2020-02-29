package com.skowronsky.snkrs.ui.home;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.skowronsky.snkrs.Shoes;

import java.util.ArrayList;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<ArrayList<Shoes>> shoesLiveData;
    private ArrayList<Shoes> shoesArrayList;

    public HomeViewModel() {
        shoesLiveData = new MutableLiveData<>();
    }
    public MutableLiveData<ArrayList<Shoes>> getShoesLiveData()
    {
        if (shoesLiveData == null)
                shoesLiveData = new MutableLiveData<>();
        return shoesLiveData;
    }
    public void init(){
        shoesList();
        shoesLiveData.setValue(shoesArrayList);
    }
    public void shoesList(){
        Shoes shoes = new Shoes();
        shoes.setCompany_name("Nike");
        shoes.setModel("Airmax 97");
        shoes.setSize("42");
        shoes.setPhoto_url("Brak");

        shoesArrayList = new ArrayList<>();
        shoesArrayList.add(shoes);
        shoesArrayList.add(shoes);
        shoesArrayList.add(shoes);
        shoesArrayList.add(shoes);
        shoesArrayList.add(shoes);
        shoesArrayList.add(shoes);
        shoesArrayList.add(shoes);
        shoesArrayList.add(shoes);
    }
}