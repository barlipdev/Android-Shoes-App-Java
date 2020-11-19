package com.skowronsky.snkrs.ui.home.search.shoeslist;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.skowronsky.snkrs.database.BaseShoes;
import com.skowronsky.snkrs.repository.Repository;
import com.skowronsky.snkrs.storage.NavigationStorage;

import java.util.List;

public class ShoesListViewModel extends AndroidViewModel {

    private LiveData<List<com.skowronsky.snkrs.database.Shoes>> allShoes;
    private MutableLiveData<Boolean> InfoNav;
    private Repository repository;
    private NavigationStorage navigationStorage;
    public BaseShoes baseShoe;

    public ShoesListViewModel(@NonNull Application application) {
        super(application);
        this.repository = new Repository(application);
        this.allShoes = repository.getAllShoes();
        this.navigationStorage = NavigationStorage.getInstance();
        baseShoe = navigationStorage.getBaseShoe();
    }

    public MutableLiveData<Boolean> getInfoNav(){
        if (InfoNav == null)
            InfoNav = new MutableLiveData<Boolean>();
        return InfoNav;
    }

    public LiveData<List<com.skowronsky.snkrs.database.Shoes>> getAllShoes(){return repository.getShoesByIdBrand(navigationStorage.getBrand().getIdBrand());}

    /**
     * Metoda zwana eventem która ustawia wartość LiveData na true w celu poinformowania obserwatora
     * aby wykonała sie nawigacja do danego fragmentu
     */
    public void eventNavToInfo(){
        InfoNav.setValue(true);
    }
    /**
     * Metoda zwana eventem która ustawia wartość LiveData na false w celu poinformowania obserwatora
     * że nawigacja do danego fragmentu się zakończyła
     */
    public void eventNavToInfoFinished(){
        InfoNav.setValue(false);
    }

    /**
     * Metoda która przekazuje buta do przechowania w NavigationStorage, przekazanego przez parametr
     * @param shoe obiekt buta który ma być przekazany do NavigationStorage
     */
    public void eventSendShoe(com.skowronsky.snkrs.database.Shoes shoe){
        navigationStorage.setShoe(shoe);
    }


}
