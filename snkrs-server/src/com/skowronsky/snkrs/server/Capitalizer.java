package com.skowronsky.snkrs.server;

import com.skowronsky.snkrs.data.Storage;
import com.skowronsky.snkrs.model.Brand;
import com.skowronsky.snkrs.model.Shoes;
import com.skowronsky.snkrs.model.User;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;
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
            var objOut = new ObjectOutputStream(socket.getOutputStream());
            String message = "";

            do{
                message = in.nextLine();
                executeCommadn(message, objOut, in, storage);
                System.out.println(message);
//                out.println("response to messege: "+ message);
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

    private void sendBrands(List<Brand> brandList, ObjectOutputStream objOut) throws IOException {
        objOut.writeObject(brandList);
    }

    private void sendShoes(List<Shoes> shoesList, ObjectOutputStream objOut) throws IOException {
        objOut.writeObject(shoesList);
    }

    private void sendUserInfo(User user, ObjectOutputStream objOut) throws IOException {
        objOut.writeObject(user);
    }

    private void executeCommadn(String command,ObjectOutputStream objOut, Scanner input, Storage storage) throws IOException {
        switch (command){
            case "login":
                String login = input.nextLine();
                String password = input.nextLine();
                User user = storage.getUser(login,password);
                if (user != null)
                    sendUserInfo(user,objOut);
                break;
            case "shoes":
                sendShoes(storage.getShoesList(),objOut);
                break;
            case "brands":
                sendBrands(storage.getBrandList(),objOut);
                break;
            default:
                break;
        }
    }
}
