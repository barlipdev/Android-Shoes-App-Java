package com.skowronsky.snkrs.data;

import com.skowronsky.snkrs.db.DataBase;
import com.skowronsky.snkrs.model.Brand;
import com.skowronsky.snkrs.model.Shoes;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    private List<Brand> brandList = new ArrayList<>();
    private List<Shoes> shoesList = new ArrayList<>();;

    public Storage(DataBase dataBase) throws SQLException {
        dataBase.getBrands(this.brandList);
        dataBase.getShoes(this.shoesList);

        brandList.forEach((i) -> System.out.println(i.getImage()));
    }

    public List<Brand> getBrandList() {
        return brandList;
    }
    public List<Shoes> getShoesList() {
        return shoesList;
    }
}
