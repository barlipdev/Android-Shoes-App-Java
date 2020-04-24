package com.skowronsky.snkrs.db;

import com.skowronsky.snkrs.model.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataBase {

    private Connection connect = null;
    private Statement statement = null;
    private Statement statmentBaseShoes = null;

    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
    private ResultSet resultSetBase= null;
    private String user = "client", password = "zoiesuflb0hb5t6f";
    private String url = "jdbc:mysql://snkrs-do-user-7351749-0.a.db.ondigitalocean.com:25060/sneakers" +
            "?verifyServerCertificate=false"+
            "&useSSL=true"+
            "&requireSSL=true";

    public DataBase() throws SQLException, ClassNotFoundException {
        try {
            // This will load the MySQL driver, each DB has its own driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Setup the connection with the DBDa
            connect = DriverManager
                    .getConnection(url,user,password);

        } catch (Exception e) {
            throw e;
        } finally {
            //close();
        }
    }

    public void getBrands(List<Brand> brandList) throws SQLException {
        // Statements allow to issue SQL queries to the database
        statement = connect.createStatement();
        // Result set get the result of the SQL query
        resultSet = statement
                .executeQuery("select * from brands");

        while (resultSet.next()){
            int id = resultSet.getInt(1);
            String name = resultSet.getString(2);
            String image = resultSet.getString(3);
            brandList.add(id-1, new Brand(id,name,image));
        }
    }

    public void getShoes(List<Shoes> shoesList) throws SQLException {
        // Statements allow to issue SQL queries to the database
        statement = connect.createStatement();
        // Result set get the result of the SQL query
        resultSet = statement
            .executeQuery("select s.id_shoes, b.name, s.model_name, s.factor, s.image from shoes s join brands b on s.id_brand = b.id_brand;");

        while (resultSet.next()){
            int id = resultSet.getInt(1);
            String brandName = resultSet.getString(2);
            String modelName = resultSet.getString(3);
            double factor = resultSet.getDouble(4);
            String image = resultSet.getString(5);

            shoesList.add(id-1, new Shoes(id,brandName,modelName,factor,image));
        }
    }

    public void getUsers(List<User> userList) throws SQLException {
        statement = connect.createStatement();
        statmentBaseShoes = connect.createStatement();

        resultSet = statement
                .executeQuery("select id_user,email,password,name,photo from user;");

        while (resultSet.next()){
            int id = resultSet.getInt(1);
            String email = resultSet.getString(2);
            String password = resultSet.getString(3);
            String name = resultSet.getString(4);
            String photo = resultSet.getString(5);
            List<BaseShoes> baseShoesList = new ArrayList<>();
            List<FavoriteShoes> favoriteShoesList = new ArrayList<>();
//            System.out.println(String.format("id: %d name: %s",id,name));
            resultSetBase = statmentBaseShoes
                    .executeQuery(String.format("select id_shoes,size,hidden_size from base_shoes where id_user = %d;",id));
            while (resultSetBase.next()){
                int id_shoes = resultSetBase.getInt(1);
                double size = resultSetBase.getDouble(2);
                double hiddenSize = resultSetBase.getDouble(3);
//                System.out.println(String.format("id_shoes: %d ",id_shoes));
                baseShoesList.add(new BaseShoes(id_shoes,size,hiddenSize));
            }
            resultSetBase = statmentBaseShoes.executeQuery(String.format("select id_shoes, size from favorite_shoes where id_user = %d",id));
            while (resultSetBase.next()){
                int id_shoes = resultSetBase.getInt(1);
                double size = resultSetBase.getDouble(2);
//                System.out.println(String.format("id_shoes: %d ",id_shoes));
                favoriteShoesList.add(new FavoriteShoes(id_shoes,size));
            }

            userList.add(id-1, new User(email,name,photo,password,baseShoesList,favoriteShoesList));
        }


    }

    public void close() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }

            if(resultSetBase != null)
                resultSetBase.close();

            if (statement != null) {
                statement.close();
            }

            if(statmentBaseShoes != null)
                statmentBaseShoes.close();

            if (connect != null) {
                connect.close();
            }
        } catch (Exception e) {

        }
    }

}
