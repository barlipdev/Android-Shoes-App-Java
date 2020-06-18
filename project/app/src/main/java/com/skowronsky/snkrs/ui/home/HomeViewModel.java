package com.skowronsky.snkrs.ui.home;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.skowronsky.snkrs.database.BaseShoes;
import com.skowronsky.snkrs.repository.Repository;

import java.util.List;

/**
 * ViewModel fragmentu Home, tutaj odbywa się cała logika biznesowa dla fragmentu Home
 */
public class HomeViewModel extends AndroidViewModel {

    private MutableLiveData<Boolean> homeNav;
    private MutableLiveData<Boolean> infoNav;
    private LiveData<List<BaseShoes>> allBaseShoes;
    private Repository repository;


    public HomeViewModel(Application application){
        super(application);
        repository = new Repository(application);
        allBaseShoes = repository.getAllBaseShoes();
    }

    /**
     * Metoda odpowiadająca za update list baz użytkownika, ustawia ona listę baz przekazaną przez parametr danemu uzytkownikowi
     * @param baseShoesList lista baz butów użytkownika
     */
    //TODO update base
    public void updateBaseShoes(List<BaseShoes> baseShoesList){
        //snkrsClient.updateBase(storage.getUser().getEmail(),baseShoesList);
    }

    public MutableLiveData<Boolean> getInfoNav(){
        if (infoNav == null)
            infoNav = new MutableLiveData<Boolean>();
        return infoNav;
    }

    public MutableLiveData<Boolean> getEventHomeNav(){
        if(homeNav == null)
            homeNav = new MutableLiveData<Boolean>();
        return homeNav;
    }

    LiveData<List<BaseShoes>> getAllBaseShoes() {return allBaseShoes;}

    /**
     * Metoda zwana eventem która ustawia wartość LiveData na true w celu poinformowania obserwatora
     * aby wykonała sie nawigacja do danego fragmentu
     */
    public void eventNavToHome(){ homeNav.setValue(true);
    }

    /**
     * Metoda zwana eventem która ustawia wartość LiveData na false w celu poinformowania obserwatora
     * że nawigacja do danego fragmentu się zakończyła
     */
    public void eventNavToHomeFinished(){
        homeNav.setValue(false);
    }
    /**
     * Metoda zwana eventem która ustawia wartość LiveData na true w celu poinformowania obserwatora
     * aby wykonała sie nawigacja do danego fragmentu
     */
    public void eventNavToInfo(){infoNav.setValue(true);}
    /**
     * Metoda zwana eventem która ustawia wartość LiveData na false w celu poinformowania obserwatora
     * że nawigacja do danego fragmentu się zakończyła
     */
    public void eventNavToInfoFinished(){infoNav.setValue(false);}

    /**
     * Metoda która usuwa bazę która zawiera danego buta oraz dany rozmiar
     * @param shoe obiekt buta który znajduje się w bazie
     * @param size rozmiar buta który znajduje się w bazie
     */
    public void deleteBaseShoes(com.skowronsky.snkrs.database.Shoes shoe, double size){
        String model_name = shoe.modelName;
        for (int i = 0; i < allBaseShoes.getValue().size(); i++){
                        if (model_name.equals(allBaseShoes.getValue().get(i).shoes.modelName) && size == allBaseShoes.getValue().get(i).base.size){
                                repository.delete(allBaseShoes.getValue().get(i).base);

                }
        }
    }

}