package com.skowronsky.snkrs.storage;

import com.skowronsky.snkrs.database.BaseShoes;
import com.skowronsky.snkrs.database.Shoes;

public class NavigationStorage {

    private Shoes shoe;
    private BaseShoes base_shoe;
    private String brand;

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

    public void setShoe(Shoes shoe){
        this.shoe = shoe;
    }
    public Shoes getShoe(){
        return shoe;
    }
    public void setBaseShoe(BaseShoes base_shoe){
        this.base_shoe = base_shoe;
    }
    public BaseShoes getBaseShoe(){
        return base_shoe;
    }
    public void setBrand(String brand){
        this.brand = brand;
    }
    public String getBrand(){
        return brand;
    }
}
