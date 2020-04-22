package com.skowronsky.snkrs.ui.profile;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.skowronsky.snkrs.database.Base;
import com.skowronsky.snkrs.database.BaseShoes;
import com.skowronsky.snkrs.database.Brand;
import com.skowronsky.snkrs.database.Favorite;
import com.skowronsky.snkrs.database.FavoriteShoes;
import com.skowronsky.snkrs.database.Shoes;
import com.skowronsky.snkrs.repository.Repository;

import java.util.List;

public class ProfileViewModel extends AndroidViewModel {
    private Repository repository;

    private LiveData<List<Brand>> allBrands;
    private LiveData<List<Shoes>> allShoes;
    private LiveData<List<Base>> allBase;
    private LiveData<List<BaseShoes>> allBaseShoes;
    private LiveData<List<Favorite>> allFavorites;
    private LiveData<List<FavoriteShoes>> allFavoriteShoes;

    private String _brandListText = "Brands:\n";
    private String _shoesListText = "Shoes:\n";

    private MutableLiveData<Boolean> settingsNav;
    public MutableLiveData<String> title = new MutableLiveData<String>("Chuj") ;
    public MutableLiveData<String> brandsListText = new MutableLiveData<String>("Brands:");

    public MutableLiveData<String> shoesListText = new MutableLiveData<String>("Shoes:");

    public ProfileViewModel(Application application){
        super(application);
        repository = new Repository(application);
        allBrands = repository.getAllBrands();
        allShoes = repository.getAllShoes();
        allBase = repository.getmAllBase();
        allBaseShoes = repository.getAllBaseShoes();
        allFavorites = repository.getAllFavorite();
        allFavoriteShoes = repository.getAllFavoriteShoes();
    }

    LiveData<List<Brand>> getAllBrands() { return allBrands; }
    LiveData<List<Shoes>> getAllShoes() {return allShoes;}
    LiveData<List<Base>> getAllBase() {return allBase;}
    LiveData<List<BaseShoes>> getAllBaseShoes() {return allBaseShoes;}
    LiveData<List<Favorite>> getAllFavorites() {return allFavorites;}
    LiveData<List<FavoriteShoes>> getAllFavoriteShoes() {return allFavoriteShoes;}

    public MutableLiveData<Boolean> getEventSettingsNav(){
        if(settingsNav == null)
            settingsNav = new MutableLiveData<Boolean>();
        return settingsNav;
    }

    public void showBrandsData(List<Brand> brandList){
        _brandListText = "Brands:\n";
        for (int i = 0; i < brandList.size(); i++) {
            _brandListText += brandList.get(i).id_brand + " " +
                brandList.get(i).brand_name + " " +
                    brandList.get(i).image+"\n";
        }
        brandsListText.setValue(_brandListText);
    }


    public void showShoesData(List<Shoes> shoesList){
        _shoesListText = "Shoes:\n";
        for (int i = 0; i < shoesList.size(); i++) {
            _shoesListText += shoesList.get(i).id_shoes + " " +
                    shoesList.get(i).brand_name + " " +
                    shoesList.get(i).modelName + " " +
                    shoesList.get(i).factor + " " +
                    shoesList.get(i).image+"\n";
        }
        shoesListText.setValue(_shoesListText);
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