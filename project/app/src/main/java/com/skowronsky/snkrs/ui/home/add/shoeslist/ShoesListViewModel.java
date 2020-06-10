package com.skowronsky.snkrs.ui.home.add.shoeslist;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.skowronsky.snkrs.database.Shoes;
import com.skowronsky.snkrs.repository.Repository;

import java.util.List;

public class ShoesListViewModel extends AndroidViewModel {

    private LiveData<List<Shoes>> allShoes;
    private Repository repository;
    private MutableLiveData<Boolean> InfoNav;



    public ShoesListViewModel(Application application) {
        super(application);
        repository = new Repository(application);
        this.allShoes = repository.getAllShoes();
    }

    public LiveData<List<Shoes>> getAllShoes(){
        return allShoes;
    }

    public MutableLiveData<Boolean> getInfoNav(){
        if (InfoNav == null)
            InfoNav = new MutableLiveData<Boolean>();
        return InfoNav;
    }
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

}
