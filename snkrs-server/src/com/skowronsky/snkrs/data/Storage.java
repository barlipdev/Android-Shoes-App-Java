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
    private DataBase dataBase;

    public Storage(DataBase dataBase) throws SQLException {
        this.dataBase = dataBase;
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

    public boolean checkUserData(String email){
        for (var item :
                userList) {
            if (item.getEmail().equals(email))
                return false;
        }
            return true;
    }
    public void insertUser(User user){
        try {
            dataBase.insertUser(user.getEmail(),user.getPassword(),user.getName());
            userList.add(user);
            for (var item :
                    userList) {
                System.out.println(item.getEmail());
            }
        } catch (SQLException throwables) {
        }
    }
}
