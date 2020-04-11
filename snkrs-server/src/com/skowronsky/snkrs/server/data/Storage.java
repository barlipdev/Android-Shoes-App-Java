package com.skowronsky.snkrs.server.data;

import com.skowronsky.snkrs.server.db.DataBase;
import com.skowronsky.snkrs.server.model.Brand;
import com.skowronsky.snkrs.server.model.Shoes;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    private List<Brand> brandList = new ArrayList<>();
    private List<Shoes> shoesList = new ArrayList<>();;

    public Storage(DataBase dataBase) throws SQLException {
        dataBase.getBrands(this.brandList);
        dataBase.getShoes(this.shoesList);
    }

    public List<Brand> getBrandList() {
        return brandList;
    }
    public List<Shoes> getShoesList() {
        return shoesList;
    }
}
