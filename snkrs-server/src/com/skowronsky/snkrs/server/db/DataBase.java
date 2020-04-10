package com.skowronsky.snkrs.server.db;

import java.sql.*;

public class DataBase {

    private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
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

            // Statements allow to issue SQL queries to the database
            statement = connect.createStatement();
            // Result set get the result of the SQL query
            resultSet = statement
                    .executeQuery("select * from shoes");

            while (resultSet.next()){
                int id = resultSet.getInt(1);
                String name = resultSet.getString(3);

                System.out.println(id + " " + name);

            }


        } catch (Exception e) {
            throw e;
        } finally {
            //close();
        }
    }

    public void close() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }

            if (statement != null) {
                statement.close();
            }

            if (connect != null) {
                connect.close();
            }
        } catch (Exception e) {

        }
    }

}
