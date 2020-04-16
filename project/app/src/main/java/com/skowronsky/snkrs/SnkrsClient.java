package com.skowronsky.snkrs;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.skowronsky.snkrs.model.Brand;
import com.skowronsky.snkrs.model.Shoes;
import com.skowronsky.snkrs.storage.Storage;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class SnkrsClient {
    Thread connectionThread = null;

    String SERVER_IP = "192.168.1.12";
    int SERVER_PORT = 59899;

    private Storage storage;

    public SnkrsClient(Storage storage){
        this.storage = storage;
        connect();
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


                storage.setBrandList((List<Brand>) objectInputStream.readObject());
                storage.setShoesList((List<Shoes>) objectInputStream.readObject());
//                List<Brand> brandList = (List<Brand>) objectInputStream.readObject();
//
//                for (int i = 0; i < storage.getBrandList().size(); i++) {
//                    Log.i("SnkrsServer","Obj: "+ storage.getBrandList().get(i).getName());
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

                    output.close();
                    socket.close();
                    Log.i("SnkrsServer","Connection Closed");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }//run
    }//ConnectionThread

}
