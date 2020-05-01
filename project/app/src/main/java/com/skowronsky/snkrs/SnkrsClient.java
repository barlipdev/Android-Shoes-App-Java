package com.skowronsky.snkrs;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.skowronsky.snkrs.database.Base;
import com.skowronsky.snkrs.database.Favorite;
import com.skowronsky.snkrs.model.BaseShoes;
import com.skowronsky.snkrs.model.Brand;
import com.skowronsky.snkrs.model.FavoriteShoes;
import com.skowronsky.snkrs.model.Shoes;
import com.skowronsky.snkrs.model.User;
import com.skowronsky.snkrs.repository.Repository;
import com.skowronsky.snkrs.storage.Storage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Collections;
import java.util.List;

public class SnkrsClient {
    Thread connectionThread = null;
    Thread authThread = null;
    Thread updateThread = null;

    String SERVER_IP = "192.168.1.7";
    int SERVER_PORT = 59895;

    private Repository repo;
    private Storage storage;

    private SnkrsClient(Storage storage, Context application){
        this.storage = storage;
        repo = new Repository((Application) application.getApplicationContext());
//        connect();
    }

    private static volatile SnkrsClient INSTANCE;
    public void connect(){
        connectionThread = new Thread(new ConnectionThread(storage));
        connectionThread.start();
    }

    public static SnkrsClient getInstance(Storage storage, Context context){
        if (INSTANCE == null){
            synchronized (SnkrsClient.class){
                if (INSTANCE == null)
                    INSTANCE = new SnkrsClient(storage,context);
            }
        }
        return INSTANCE;
    }

    public void auth(String login, String password){
        authThread = new Thread(new AuthThread(storage,login,password));
        authThread.start();
    }

    public void auth(String login, String password, String name){
        authThread = new Thread(new AuthThread(storage,login,password,name));
        authThread.start();
    }

    public void updateUser(String login, String password, String name){
        updateThread = new Thread(new UpdateThread(storage,login,name,password));
        updateThread.start();
    }

    class ConnectionThread implements Runnable {

        private PrintWriter output;
        private BufferedReader input;
        private ObjectInputStream objectInputStream;

        Socket socket;
        Storage storage;

        public ConnectionThread(Storage storage){
            this.storage = storage;
        }

        @RequiresApi(api = Build.VERSION_CODES.O)
        @SuppressLint("RestrictedApi")
        public void run() {
            try {
                socket = new Socket(SERVER_IP,SERVER_PORT);
                output = new PrintWriter(socket.getOutputStream(),true);
                input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                objectInputStream = new ObjectInputStream(socket.getInputStream());
                String message = "";
                String login = "root@root.com";
                String password = "root";

                Log.i("SnkrsServer","Connected");

                getBrands(output,objectInputStream,storage);
                getShoes(output,objectInputStream,storage);
//                getUser(login,password,output,objectInputStream,storage);

                output.println("QQQ");

            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            finally {
                try {
                    if(output != null)
                        output.close();
                    if(socket != null)
                        socket.close();
                    Log.i("SnkrsServer","Connection Closed");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }//run

    }//ConnectionThread

    class AuthThread implements Runnable{

        private PrintWriter output;
        private BufferedReader input;
        private ObjectInputStream objectInputStream;


        private Socket socket;
        private Storage storage;


        private String login;
        private String name;
        private String password;
        private Boolean signUp;

        public AuthThread(Storage storage, String login, String password){
            this.storage = storage;
            this.login = login;
            this.password = password;
            signUp = false;
        }

        public AuthThread(Storage storage, String login, String name, String password){
            this.storage = storage;
            this.login = login;
            this.name = name;
            this.password = password;
            signUp = true;
        }

        @RequiresApi(api = Build.VERSION_CODES.O)
        @SuppressLint("RestrictedApi")
        public void run() {
            try {
                socket = new Socket(SERVER_IP,SERVER_PORT);
                output = new PrintWriter(socket.getOutputStream(),true);
                input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                objectInputStream = new ObjectInputStream(socket.getInputStream());

                Log.i("SnkrsServer","Connected To Login");

                if(signUp)
                    createUser(login,password,name,output,objectInputStream,storage);
                else
                    getUser(login,password,output,objectInputStream,storage);

                output.println("QQQ");

            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            finally {
                try {
                    if(output != null)
                        output.close();
                    if(socket != null)
                        socket.close();
                    Log.i("SnkrsServer","Connection Closed");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }//run
    }//AuthThread

    class UpdateThread implements Runnable{

        private PrintWriter output;
        private BufferedReader input;
        private ObjectInputStream objectInputStream;

        private Socket socket;
        private Storage storage;

        private String login;
        private String name;
        private String password;

        public UpdateThread(Storage storage, String login, String name, String password){
            this.storage = storage;
            this.login = login;
            this.name = name;
            this.password = password;
        }

        @RequiresApi(api = Build.VERSION_CODES.O)
        @SuppressLint("RestrictedApi")
        public void run() {
            try {
                socket = new Socket(SERVER_IP,SERVER_PORT);
                output = new PrintWriter(socket.getOutputStream(),true);
                input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                objectInputStream = new ObjectInputStream(socket.getInputStream());

                Log.i("SnkrsServer","Connected To Login");

                updateUser(login,password,name,output);

                output.println("QQQ");

            } catch (IOException e) {
                e.printStackTrace();
            }
            finally {
                try {
                    if(output != null)
                        output.close();
                    if(socket != null)
                        socket.close();
                    Log.i("SnkrsServer","Connection Closed");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }//run
    }//AuthThread


    private void getBrands(PrintWriter output, ObjectInputStream objectInputStream, Storage storage) throws IOException, ClassNotFoundException {
        output.println("brands");
        storage.setBrandList(Collections.unmodifiableList((List<Brand>) objectInputStream.readObject()));

        insertBrandsToRoom(storage.getBrandList());
    }

    private void insertBrandsToRoom(List<Brand> brandList){
        com.skowronsky.snkrs.database.Brand brand = null;
        for (int i = 0; i < brandList.size(); i++) {
            Log.i("SnkrsBrands","Obj: "+ brandList.get(i).getName());
            brand = new com.skowronsky.snkrs.database.Brand();
            brand.id_brand = brandList.get(i).getId();
            brand.brand_name = brandList.get(i).getName();
            brand.image = brandList.get(i).getImage();
            repo.insertBrand(brand);
        }
    }

    private void getShoes(PrintWriter output, ObjectInputStream objectInputStream, Storage storage) throws IOException, ClassNotFoundException {
        output.println("shoes");
        storage.setShoesList(Collections.unmodifiableList((List<Shoes>) objectInputStream.readObject()));

        insertShoesToRoom(storage.getShoesList());
    }

    private void insertShoesToRoom(List<Shoes> shoesList){
        com.skowronsky.snkrs.database.Shoes shoes = null;
        for (int i = 0; i < shoesList.size(); i++) {
            shoes = new com.skowronsky.snkrs.database.Shoes();
            shoes.id_shoes = shoesList.get(i).getId();
            shoes.brand_name = shoesList.get(i).getBrandName();
            shoes.factor = shoesList.get(i).getFactor();
            shoes.image = shoesList.get(i).getImage();
            shoes.modelName = shoesList.get(i).getModelName();
            repo.insertShoes(shoes);
        }
    }

    private void getUser(String login, String password, PrintWriter output, ObjectInputStream objectInputStream, Storage storage) throws IOException, ClassNotFoundException {
        output.println("login");
        output.println(login);
        output.println(password);
        storage.setUser((User) objectInputStream.readObject());

        if(storage.getUser() != null){
            insertBaseShoesToRoom(storage.getUser().getBaseShoesList());
            insertFavoriteShoesToRoom(storage.getUser().getFavoriteShoesList());
        }
//        Log.i("User",storage.getUser().getEmail());
    }

    private void insertBaseShoesToRoom(List<BaseShoes> baseShoes){
        repo.deleteAllBase();
        Base base = null;
        for (int i = 0; i < baseShoes.size(); i++) {
            base = new Base();
            base.id_base = i+1;
            base.id_shoes = baseShoes.get(i).getIdShoes();
            base.size = baseShoes.get(i).getSize();
            base.hiddenSize = baseShoes.get(i).getHiddenSize();
            repo.insertBase(base);
        }
    }

    private void insertFavoriteShoesToRoom(List<FavoriteShoes> favoriteShoesList){
        repo.deleteAllFavorites();
        Favorite favorite = null;
        for (int i = 0; i < favoriteShoesList.size(); i++) {
            favorite = new Favorite();
            favorite.id_favorite_shoes = i+1;
            favorite.id_shoes = favoriteShoesList.get(i).getIdShoes();
            favorite.size = favoriteShoesList.get(i).getSize();
            repo.insertFavorite(favorite);
        }
    }

    private void createUser(String login, String password, String name, PrintWriter output,ObjectInputStream objectInputStream, Storage storage) throws IOException, ClassNotFoundException {

        output.println("signup");
        output.println(login);
        output.println(password);
        output.println(name);

        storage.setUser((User) objectInputStream.readObject());
        repo.deleteAllBase();
        repo.deleteAllFavorites();
        if(storage.getUser()!= null)
            Log.i("User",storage.getUser().getEmail());
    }

    private void updateUser(String login, String password, String name, PrintWriter out) {
        out.println("update");
        out.println(login);
        out.println(password);
        out.println(name);

//        storage.setUser(new User());
    }
}
