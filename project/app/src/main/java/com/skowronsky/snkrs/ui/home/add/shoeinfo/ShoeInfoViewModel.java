package com.skowronsky.snkrs.ui.home.add.shoeinfo;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.skowronsky.snkrs.database.Base;
import com.skowronsky.snkrs.repository.Repository;
import com.skowronsky.snkrs.storage.NavigationStorage;
import com.skowronsky.snkrs.ui.home.add.shoeinfo.sizepages.Size;

public class ShoeInfoViewModel extends AndroidViewModel {

    private MutableLiveData<Boolean> navToBase;
    private Repository repository;
    private NavigationStorage navigationStorage;
    private Base base;
    private MutableLiveData<Size> size;
    public String shoe_model;
    public String shoe_brand;

    public ShoeInfoViewModel(Application application){
        super(application);
        repository = new Repository(application);
        this.navigationStorage = NavigationStorage.getInstance();
        shoe_model = navigationStorage.getShoe().getModelName();
        shoe_brand = navigationStorage.getShoe().getBrandName();
    }



    public MutableLiveData<Boolean> getNavToBase(){
        if (navToBase == null)
            navToBase = new MutableLiveData<Boolean>();
        return navToBase;
    }

    /**
     * Metoda dodająca nową bazę buta o danym rozmiarze
     * @param size rozmiar bazy buta
     */

    //TODO TODO BaseShoes 1.3
//    public void addShoeToBase(double size){
//        base = new Base(navigationStorage.getShoe(),size);
//        repository.insertBase(base);
//    }

    /**
     * Metoda zwana eventem która ustawia wartość LiveData na true w celu poinformowania obserwatora
     * aby wykonała sie nawigacja do danego fragmentu
     */
    public void eventNavToInfo(){
        navToBase.setValue(true);
    }
    /**
     * Metoda zwana eventem która ustawia wartość LiveData na false w celu poinformowania obserwatora
     * że nawigacja do danego fragmentu się zakończyła
     */
    public void eventNavToInfoFinished(){
        navToBase.setValue(false);
    }


}
