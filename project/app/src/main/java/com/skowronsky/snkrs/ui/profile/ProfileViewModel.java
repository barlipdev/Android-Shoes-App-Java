package com.skowronsky.snkrs.ui.profile;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.skowronsky.snkrs.storage.Storage;

public class ProfileViewModel extends ViewModel {
    private Storage storage;
    private MutableLiveData<Boolean> settingsNav;
    public String brandsListText ="Brands:";
    public String shoesListText = "\n\nShoes:";

    public ProfileViewModel(Storage storage){
        this.storage = storage;

        for (int i = 0; i < this.storage.getBrandList().size(); i++) {
            brandsListText += "\n"+this.storage.getBrandList().get(i).getName()+
                " "+this.storage.getBrandList().get(i).getImage();
        }

        for (int i = 0; i < this.storage.getShoesList().size(); i++) {
            shoesListText += "\n"+ this.storage.getShoesList().get(i).getBrandName()+
                " " +this.storage.getShoesList().get(i).getModelName()+
                " "+ this.storage.getShoesList().get(i).getFactor()+
                " " + this.storage.getShoesList().get(i).getImage();
        }
    }


    public MutableLiveData<Boolean> getEventSettingsNav(){
        if(settingsNav == null)
            settingsNav = new MutableLiveData<Boolean>();
        return settingsNav;
    }

    public void eventNavToSettings(){
        settingsNav.setValue(true);
    }
    public void eventNavToSettingsFinished(){
        settingsNav.setValue(false);
    }

}