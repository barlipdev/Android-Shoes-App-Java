package com.skowronsky.snkrs.ui.profile;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.skowronsky.snkrs.database.Brand;
import com.skowronsky.snkrs.repository.Repository;

import java.util.List;

public class ProfileViewModel extends AndroidViewModel {
    private Repository repository;

    private LiveData<List<Brand>> allBrands;

    private MutableLiveData<Boolean> settingsNav;
    public String title = "Chuj";
    public String brandsListText ="Brands:";
    public String shoesListText = "\n\nShoes:";

    public ProfileViewModel(Application application){
        super(application);
        repository = new Repository(application);
        allBrands = repository.getAllBrands();
    }

    LiveData<List<Brand>> getAllBrands() { return allBrands; }

    public MutableLiveData<Boolean> getEventSettingsNav(){
        if(settingsNav == null)
            settingsNav = new MutableLiveData<Boolean>();
        return settingsNav;
    }

    public void showData(List<Brand> brandList){
       brandsListText = "Chuj jebay";
    }
    public void deleteBrands(){
        repository.deleteAllBrands();
    }

    public void eventNavToSettings(){
        settingsNav.setValue(true);
    }
    public void eventNavToSettingsFinished(){
        settingsNav.setValue(false);
    }

}