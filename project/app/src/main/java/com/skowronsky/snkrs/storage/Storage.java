package com.skowronsky.snkrs.storage;

import com.skowronsky.snkrs.model.Brand;
import com.skowronsky.snkrs.model.Shoes;

import java.util.ArrayList;
import java.util.List;

public class Storage {
    private List<Brand> brandList;
    private List<Shoes> shoesList;

    public List<Brand> getBrandList() {
        return brandList;
    }
    public List<Shoes> getShoesList() {
        return shoesList;
    }

    public void setBrandList(List<Brand> brandList) {
        this.brandList = new ArrayList<Brand>(brandList);
    }

    public void setShoesList(List<Shoes> shoesList) {
        this.shoesList = new ArrayList<Shoes>(shoesList);
    }

}
