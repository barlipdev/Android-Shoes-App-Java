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
    private String _brandListText = "Brands:\n";

    private MutableLiveData<Boolean> settingsNav;
    public MutableLiveData<String> title = new MutableLiveData<String>("Chuj") ;
    public MutableLiveData<String> brandsListText = new MutableLiveData<String>("Brands:");
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
        _brandListText = "Brands:\n";
        for (int i = 0; i < brandList.size(); i++) {
            _brandListText += brandList.get(i).id_brand + " " +
                brandList.get(i).brand_name + "\n";
        }
        brandsListText.setValue(_brandListText);
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