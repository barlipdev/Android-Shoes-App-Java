package com.skowronsky.snkrs.server;

import com.skowronsky.snkrs.server.data.Storage;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Capitalizer implements Runnable {
    private Socket socket;
    private Storage storage;

    public Capitalizer(Socket socket, Storage storage) {
        this.socket = socket;
        this.storage = storage;
    }

    @Override
    public void run() {
        System.out.println("Connected: " + socket);
        try {
            var in = new Scanner(socket.getInputStream());
            var out = new PrintWriter(socket.getOutputStream(), true);
//            ObjectOutputStream objOut = new ObjectOutputStream(socket.getOutputStream());
            String message = "";

//            objOut.writeObject(storage.getBrandList());


            do{
                message = in.nextLine();
                System.out.println(message);
                out.println("response to messege: "+ message);
            }while (!message.equals("QQQ"));

            out.println("QQQ");


        } catch (Exception e) {
            System.out.println("I/O Error:" + socket);
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                System.out.println("Close connection err: "+e);
            }
            System.out.println("Closed: " + socket);
        }
    }
}
