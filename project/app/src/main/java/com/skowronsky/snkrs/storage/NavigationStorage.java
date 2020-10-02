package com.skowronsky.snkrs.storage;

import androidx.lifecycle.MutableLiveData;

import com.skowronsky.snkrs.database.BaseShoes;
import com.skowronsky.snkrs.database.Brand;
import com.skowronsky.snkrs.database.FavoriteShoes;
import com.skowronsky.snkrs.database.Shoes;
import com.skowronsky.snkrs.ui.home.add.shoeinfo.sizepages.Size;

import java.util.List;

/**
 * Klasa NavigationStorage jest singletonem w aplikacji, służy ona w przekazywaniu danych pomiędzy fragmentami
 */
public class NavigationStorage {

    private Shoes shoe;
    private BaseShoes base_shoe;
    private Brand brand;
    private FavoriteShoes favoriteShoes;
    private int size_pos;
    private List<BaseShoes> baseShoes;
    private MutableLiveData<Size> sizes = new MutableLiveData<Size>();

    private NavigationStorage(){}

    private static volatile NavigationStorage INSTANCE;
    public static NavigationStorage getInstance(){
        if (INSTANCE == null){
            synchronized (NavigationStorage.class){
                if (INSTANCE == null)
                    INSTANCE = new NavigationStorage();
            }
        }
        return INSTANCE;
    }

    /**
     * Metoda która ustawia obiekt buta na ten przekazany przez parametr
     * @param shoe przekazany obiekt buta
     */
    public void setShoe(Shoes shoe){
        this.shoe = shoe;
    }

    public void setSize_pos(int size_pos) {
        this.size_pos = size_pos;
    }

    public int getSize_pos() {
        return size_pos;
    }

    /**
     * Metoda która zwraca buta, który aktualnie znajduje się w NavigationStorage
     * @return metoda zwraca obiekt buta znajdującego się aktualnie w NavigationStorage
     */
    public Shoes getShoe(){
        return shoe;
    }

    /**
     * Metoda która ustawia obiekt bazy buta na ten przekazany przez parametr
     * @param base_shoe obiekt bazy buta
     */
    public void setBaseShoe(BaseShoes base_shoe){
        this.base_shoe = base_shoe;
    }

    /**
     * Metoda która zwraca bazę buta, która aktualnie jest przechowywana w NavigationStorage
     * @return zwraca obiekt bazy buta
     */
    public BaseShoes getBaseShoe(){
        return base_shoe;
    }

    /**
     * Metoda która ustawia markę buta na tą przekazaną przez parametr
     * @param brand zmienna typu String przechowująca markę buta
     */
    public void setBrand(Brand brand){
        this.brand = brand;
    }

    /**
     * Metoda która zwraca markę buta, która jest aktualnie przechowywana w NavigationStorage
     * @return zwraca markę buta
     */
    public Brand getBrand(){
        return brand;
    }

    /**
     * Metoda która ustawia obiekt klasy Size na ten przekazany przez parametr
     * @param sizes obiekt klasy Size, przechowujący w sobie rozmiary US, UK, EU
     */
    public void setSizes(Size sizes){this.sizes.setValue(sizes);}

    /**
     * Metoda która zwraca obiekt klasy Size, która przechowuje rozmiary buta
     * @return obiekt klasy Size
     */
    public MutableLiveData<Size> getSizes(){return sizes;}

    public void setFavoriteShoes(FavoriteShoes favoriteShoes) {
        base_shoe = favoriteShoes.baseShoes;
        shoe = favoriteShoes.brandShoes.shoes;
    }
}
