package com.skowronsky.snkrs.storage;

import android.content.Context;

import com.skowronsky.snkrs.SnkrsClient;
import com.skowronsky.snkrs.model.Brand;
import com.skowronsky.snkrs.model.Shoes;
import com.skowronsky.snkrs.model.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Storage {
    private List<Brand> brandList;
    private List<Shoes> shoesList;
    private User user;

    private Storage(){}

    private static volatile Storage INSTANCE;
    public static Storage getInstance(){
        if (INSTANCE == null){
            synchronized (Storage.class){
                if (INSTANCE == null)
                    INSTANCE = new Storage();
            }
        }
        return INSTANCE;
    }

    public List<Brand> getBrandList() {
        return brandList;
    }
    public List<Shoes> getShoesList() {
        return shoesList;
    }

    public void setBrandList(List<Brand> brandList) {
        this.brandList = brandList;
    }

    public void setShoesList(List<Shoes> shoesList) {
        this.shoesList = shoesList;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
