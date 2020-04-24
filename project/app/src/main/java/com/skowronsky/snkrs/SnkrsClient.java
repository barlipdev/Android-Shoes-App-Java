package com.skowronsky.snkrs;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.skowronsky.snkrs.database.Base;
import com.skowronsky.snkrs.model.Brand;
import com.skowronsky.snkrs.model.Shoes;
import com.skowronsky.snkrs.repository.Repository;
import com.skowronsky.snkrs.storage.Storage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Collections;
import java.util.List;

public class SnkrsClient {
    Thread connectionThread = null;

    String SERVER_IP = "192.168.1.12";
    int SERVER_PORT = 59899;

    private Repository repo;
    private Storage storage;
    private Context application;

    private SnkrsClient(Storage storage, Context application){
        this.storage = storage;
        this.application = application;
        repo = new Repository((Application) application.getApplicationContext());
        connect();
    }

    private static volatile SnkrsClient INSTANCE;
    public static SnkrsClient getInstance(Storage storage, Context context){
        if (INSTANCE == null){
            synchronized (SnkrsClient.class){
                if (INSTANCE == null)
                    INSTANCE = new SnkrsClient(storage,context);
            }
        }
        return INSTANCE;
    }

    public void connect(){
        connectionThread = new Thread(new ConnectionThread(storage));
        connectionThread.start();
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
                Log.i("SnkrsServer","Connected");


                storage.setBrandList(Collections.unmodifiableList((List<Brand>) objectInputStream.readObject()));
                storage.setShoesList(Collections.unmodifiableList((List<Shoes>) objectInputStream.readObject()));
//                List<Brand> brandList = (List<Brand>) objectInputStream.readObject();
//
//                repo.deleteAllBrands();
                com.skowronsky.snkrs.database.Brand brand = null;
                for (int i = 0; i < storage.getBrandList().size(); i++) {
                    Log.i("SnkrsServer","Obj: "+ storage.getBrandList().get(i).getName());
                    brand = new com.skowronsky.snkrs.database.Brand();
                    brand.id_brand = storage.getBrandList().get(i).getId();
                    brand.brand_name = storage.getBrandList().get(i).getName();
                    brand.image = storage.getBrandList().get(i).getImage();
                    repo.insertBrand(brand);
                }
                com.skowronsky.snkrs.database.Shoes shoes = null;
                for (int i = 0; i < storage.getShoesList().size(); i++) {
                    shoes = new com.skowronsky.snkrs.database.Shoes();
                    shoes.id_shoes = storage.getShoesList().get(i).getId();
                    shoes.brand_name = storage.getShoesList().get(i).getBrandName();
                    shoes.factor = storage.getShoesList().get(i).getFactor();
                    shoes.image = storage.getShoesList().get(i).getImage();
                    shoes.modelName = storage.getShoesList().get(i).getModelName();
                    repo.insertShoes(shoes);
                }




//                for (int i = 0; i < repo.getAllShoes().getValue().size(); i++) {
////                    Log.i("ROOM123","SQLite: "+ repo.getAllBaseShoes().get(i));
//                }

                output.println("QQQ");
                do{
                    message = input.readLine();
                    Log.i("SnkrsServer","Messege: "+ message);

                }while (!message.equals("QQQ"));

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

}
