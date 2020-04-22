package com.skowronsky.snkrs.ui.home.shoes;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

public class ShoesInformationViewModel extends ViewModel {

    private MutableLiveData<Boolean> navToBase;
    private MutableLiveData<ArrayList<Double>> sizes;

    public ShoesInformationViewModel(){
        sizes = new MutableLiveData<ArrayList<Double>>();
    }

    public MutableLiveData<Boolean> getNavToBase(){
        if (navToBase == null)
            navToBase = new MutableLiveData<Boolean>();
        return navToBase;
    }

    public MutableLiveData<ArrayList<Double>> getSizesData(){
        if (sizes == null)
            sizes = new MutableLiveData<ArrayList<Double>>();
        return sizes;
    }

    public void eventNavToInfo(){
        navToBase.setValue(true);
    }
    public void eventNavToInfoFinished(){
        navToBase.setValue(false);
    }

    public void init(ArrayList<Double> sizes){
        this.sizes.setValue(sizes);
    }

}
