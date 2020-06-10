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

/**
 * Klasa do obsługi połączenia aplikacji z serwerem
 */
public class SnkrsClient {
    Thread connectionThread = null;
    Thread authThread = null;
    Thread updateThread = null;

    String SERVER_IP = "192.168.1.12";
    int SERVER_PORT = 59896;

    private Repository repo;
    private Storage storage;

    private SnkrsClient(Storage storage, Context application){
        this.storage = storage;
        repo = new Repository((Application) application.getApplicationContext());
//        connect();
    }

    private static volatile SnkrsClient INSTANCE;
    /**
     * Metoda odpowiadająca za nawiązanie połączenia z serwerem,
     * pobranie podstawowych danych z serwera
     */
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

    /**
     * Metoda odpowiadająca za logowanie się użytkownika na swój profil
     * @param login zmienna String przechowująca login użytkownika
     * @param password zmienna String przechowująca hasło uzytkownika
     */
    public void auth(String login, String password){
        authThread = new Thread(new AuthThread(storage,login,password));
        authThread.start();
    }

    /**
     * Metoda odpowiadająca za tworzenie nowego użytkownika
     * @param login zmienna String przechowująca login użytkownika
     * @param password zmienna String przechowująca hasło uzytkownika
     * @param name zmienna String przechowująca nazwę użytkownika
     */
    public void auth(String login, String password, String name){
        authThread = new Thread(new AuthThread(storage,login,password,name));
        authThread.start();
    }

    /**
     * Metoda odpowiadająca za edytowanie danych użytkownika
     * @param login zmienna String przechowująca login użytkownika
     * @param password zmienna String przechowująca hasło uzytkownika
     * @param name zmienna String przechowująca nazwę użytkownika
     */
    public void updateUser(String login, String password, String name){
        updateThread = new Thread(new UpdateThread(storage,login,name,password));
        updateThread.start();
    }

    /**
     * Metoda odpowiadająca za aktualizację ulubionych butów użytkownika
     * @param email zmienna String przechowująca email użytkownika
     * @param favoriteShoesList lista ulubionych butów użytkownika
     */
    public void updateFavorite(String email,List<com.skowronsky.snkrs.database.FavoriteShoes> favoriteShoesList){
        updateThread = new Thread(new UpdateThread(storage,email,favoriteShoesList));
        updateThread.start();
    }

    /**
     * Metoda odpowiadająca za aktualizację listy butów bazowych
     * @param email zmienna String przechowująca email użytkownika
     * @param baseShoesList lista baz butów użytkownika
     */
    public void updateBase(String email, List<com.skowronsky.snkrs.database.BaseShoes> baseShoesList){
        updateThread = new Thread(new UpdateThread(email,baseShoesList));
        updateThread.start();
    }

    /**
     * Wątek do łączenia się z serwerem i pobierania podstawowych danych
     */
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

    /**
     * Wątek odpowiedzialny za logowanie i rejestracje użytkownika
     */
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

        public AuthThread(Storage storage, String login, String password, String name){
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

    /**
     * Wątek odpowiedzialny za wykonywanie aktualizacji danych
     */
    class UpdateThread implements Runnable{

        private PrintWriter output;
        private BufferedReader input;
        private ObjectInputStream objectInputStream;

        private Socket socket;
        private Storage storage;

        private String login;
        private String name;
        private String password;
        private List<com.skowronsky.snkrs.database.FavoriteShoes> favoriteShoesList;
        private List<com.skowronsky.snkrs.database.BaseShoes> baseShoesList;

        private boolean user = false;
        private boolean fav = false;
        private boolean shoes = false;

        public UpdateThread(Storage storage, String login, String name, String password){
            this.storage = storage;
            this.login = login;
            this.name = name;
            this.password = password;
            user = true;
        }

        public UpdateThread(Storage storage, String email, List<com.skowronsky.snkrs.database.FavoriteShoes> favoriteShoesList) {
            this.login = email;
            this.storage = storage;
            this.favoriteShoesList = favoriteShoesList;
            fav = true;
        }


        public UpdateThread(String email, List<com.skowronsky.snkrs.database.BaseShoes> baseShoesList) {
            this.login = email;
            this.baseShoesList = baseShoesList;
            shoes = true;
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

                if(user)
                    updateUser(login,password,name,output);
                if(fav)
                    updateFavoriteShoes(login,favoriteShoesList,output);
                if(shoes)
                    updateBaseShoes(login,baseShoesList,output);
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

    /**
     * Metoda odpowiadająca za porabnie z serwera listy marek butów
     * @param output strumień wyjścia odpowiedzialny za rozkazy skierowane do serwera jaką czynność ma wykonać
     * @param objectInputStream strumień wejścia dla obiektów, w tym przypadku dla listy marek butów
     * @param storage instancja klasy odpowiedzialnej za kolekcjonowanie danych otrzymanych z serwera
     * @throws IOException wyjątek strumienia wejścia i wyjścia
     * @throws ClassNotFoundException wjątek obsługujący pobraną klasę
     */
    private void getBrands(PrintWriter output, ObjectInputStream objectInputStream, Storage storage) throws IOException, ClassNotFoundException {
        output.println("brands");
        storage.setBrandList(Collections.unmodifiableList((List<Brand>) objectInputStream.readObject()));

        insertBrandsToRoom(storage.getBrandList());
    }

    /**
     * Metoda odpowiadająca za dodawanie danych do lokalnej bazy danych, w tym przypadku listy marek butów
     * @param brandList lista marek butów
     */
    private void insertBrandsToRoom(List<Brand> brandList){
        repo.deleteAllBase();
        repo.deleteAllFavorites();
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

    /**
     * Metoda odpowiadająca za pobranie butów z bazy danych
     * @param output strumień wyjścia odpowiedzialny za rozkazy skierowane do serwera jaką czynność ma wykonać
     * @param objectInputStream strumień wejścia dla obiektów, w tym przypadku dla listy marek butów
     * @param storage instancja klasy odpowiedzialnej za kolekcjonowanie danych otrzymanych z serwera
     * @throws IOException wyjątek strumienia wejścia i wyjścia
     * @throws ClassNotFoundException wjątek obsługujący pobraną klasę
     */
    private void getShoes(PrintWriter output, ObjectInputStream objectInputStream, Storage storage) throws IOException, ClassNotFoundException {
        output.println("shoes");
        storage.setShoesList(Collections.unmodifiableList((List<Shoes>) objectInputStream.readObject()));

        insertShoesToRoom(storage.getShoesList());
    }

    /**
     * Metoda odpowiadająca za dodawanie danych do lokalnej bazy danych, w tym przypadku listy butów
     * @param shoesList lista butów
     */
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

    /**
     * Metoda odpowiedzialna za pobranie informacji o użytkowniku z bazy danych
     * @param login zmienna przechowująca login użytkownika
     * @param password zmienna przechowująca hasło użytkownika
     * @param output strumień wyjścia odpowiedzialny za rozkazy skierowane do serwera jaką czynność ma wykonać
     * @param objectInputStream strumień wejścia dla obiektów, w tym przypadku dla listy marek butów
     * @param storage instancja klasy odpowiedzialnej za kolekcjonowanie danych otrzymanych z serwera
     * @throws IOException wyjątek strumienia wejścia i wyjścia
     * @throws ClassNotFoundException wjątek obsługujący pobraną klasę
     */
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

    /**
     * Metoda odpowiadająca za dodawanie danych do lokalnej bazy danych, w tym przypadku listy baz butów
     * @param baseShoes lista baz użytkownika
     */
    private void insertBaseShoesToRoom(List<BaseShoes> baseShoes){
//        repo.deleteAllBase();
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

    /**
     * Metoda odpowiadająca za dodawanie danych do lokalnej bazy danych, w tym przypadku listy ulubionych butów
     * @param favoriteShoesList lista ulubionych butów użytkownika
     */
    private void insertFavoriteShoesToRoom(List<FavoriteShoes> favoriteShoesList){
//        repo.deleteAllFavorites();
        Favorite favorite = null;
        for (int i = 0; i < favoriteShoesList.size(); i++) {
            favorite = new Favorite();
            favorite.id_favorite_shoes = i+1;
            favorite.id_base = favoriteShoesList.get(i).getIdBase();
            favorite.id_shoes = favoriteShoesList.get(i).getIdShoes();
            favorite.size = favoriteShoesList.get(i).getSize();
            repo.insertFavorite(favorite);
        }
    }

    /**
     * Metoda odpowiadająca za utworzenie użytkownika
     * @param login zmienna przechowująca login użytkownika
     * @param password zmienna przechowująca hasło użytkownika
     * @param name zmienna przechowująca nazwę użytkownika
     * @param output strumień wyjścia odpowiedzialny za rozkazy skierowane do serwera jaką czynność ma wykonać
     * @param objectInputStream strumień wejścia dla obiektów, w tym przypadku dla listy marek butów
     * @param storage instancja klasy odpowiedzialnej za kolekcjonowanie danych otrzymanych z serwera
     * @throws IOException wyjątek strumienia wejścia i wyjścia
     * @throws ClassNotFoundException wjątek obsługujący pobraną klasę
     */
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

    /**
     * Metoda odpowiadająca za aktualizację danych użytkownika
     * @param login zmienna przechowująca login użytkownika
     * @param password zmienna przechowująca hasło użytkownika
     * @param name zmienna przechowująca nazwę użytkownika
     * @param out strumień wyjścia odpowiedzialny za rozkazy skierowane do serwera jaką czynność ma wykonać
     */
    private void updateUser(String login, String password, String name, PrintWriter out) {
        out.println("update");
        out.println(login);
        out.println(password);
        out.println(name);

//        storage.setUser(new User());
    }

    /**
     * Metoda odpowiedzialnia za aktualizację listy ulubionych butów użytkownika
     * @param login zmienna przechowująca login użytkownika
     * @param favoriteShoesList lista ulubionych butów użytkownika
     * @param out strumień wyjścia odpowiedzialny za rozkazy skierowane do serwera jaką czynność ma wykonać
     */
    private void updateFavoriteShoes(String login, List<com.skowronsky.snkrs.database.FavoriteShoes> favoriteShoesList, PrintWriter out){
        out.println("fav");
        out.println(login);
        out.println(favoriteShoesList.size());
        for (int i = 0; i < favoriteShoesList.size(); i++) {
            out.println(favoriteShoesList.get(i).shoes.id_shoes);
            out.println(favoriteShoesList.get(i).favorite.id_base);
            out.println(favoriteShoesList.get(i).favorite.size);
        }

    }

    /**
     * Metoda odpowiedzialna za aktualizację baz butów użytkownika
     * @param login zmienna przechowująca login użytkownika
     * @param baseShoesList lista baz butów użytkownika
     * @param out strumień wyjścia odpowiedzialny za rozkazy skierowane do serwera jaką czynność ma wykonać
     */
    private void updateBaseShoes(String login, List<com.skowronsky.snkrs.database.BaseShoes> baseShoesList, PrintWriter out){
        out.println("base");
        out.println(login);
        out.println(baseShoesList.size());
        for (int i = 0; i < baseShoesList.size(); i++) {
            out.println(baseShoesList.get(i).base.id_shoes);
            out.println(baseShoesList.get(i).base.size);
            out.println(baseShoesList.get(i).base.hiddenSize);
        }

    }
}
