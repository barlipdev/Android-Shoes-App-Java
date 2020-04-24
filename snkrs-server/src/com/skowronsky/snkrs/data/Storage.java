package com.skowronsky.snkrs.data;

import com.skowronsky.snkrs.db.DataBase;
import com.skowronsky.snkrs.model.Brand;
import com.skowronsky.snkrs.model.Shoes;
import com.skowronsky.snkrs.model.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    private List<Brand> brandList = new ArrayList<>();
    private List<Shoes> shoesList = new ArrayList<>();
    private List<User> userList = new ArrayList<>();

    public Storage(DataBase dataBase) throws SQLException {
        dataBase.getBrands(this.brandList);
        dataBase.getShoes(this.shoesList);
        dataBase.getUsers(this.userList);

    }

    public List<Brand> getBrandList() {
        return brandList;
    }
    public List<Shoes> getShoesList() {
        return shoesList;
    }

    public User getUser(String email, String password){
        for (var item :
                userList) {
            if(item.getEmail().equals(email)){
                if (item.checkPassword(password))
                    return item;
            }
        }
        return null;
    }
}
