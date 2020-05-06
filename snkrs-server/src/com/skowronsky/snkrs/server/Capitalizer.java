package com.skowronsky.snkrs.server;

import com.skowronsky.snkrs.data.Storage;
import com.skowronsky.snkrs.model.Brand;
import com.skowronsky.snkrs.model.FavoriteShoes;
import com.skowronsky.snkrs.model.Shoes;
import com.skowronsky.snkrs.model.User;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
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
            var objOut = new ObjectOutputStream(socket.getOutputStream());
            String message = "";


            do{
                message = in.nextLine();

                System.out.println("conne");

                executeCommadn(message, objOut, in, storage);
                System.out.println(message);
            }while (!message.equals("QQQ"));

            in.close();
            objOut.close();
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

    private void executeCommadn(String command, ObjectOutputStream objOut, Scanner input, Storage storage) throws IOException {
        String login = null;
        String password = null;
        String name = null;
        User user = null;
        int length;

        switch (command){
            case "login":
                login = input.nextLine();
                password = input.nextLine();
                user = storage.getUser(login,password);
                sendUserInfo(user,objOut);
                break;
            case "shoes":
                sendShoes(storage.getShoesList(),objOut);
                break;
            case "brands":
                sendBrands(storage.getBrandList(),objOut);
                break;
            case "signup":
                login = input.nextLine();
                password = input.nextLine();
                name = input.nextLine();
                user = null;
                if(storage.checkUserData(login)){
                    user = new User(login,name,"",password,new ArrayList<>(),new ArrayList<>());
                    storage.insertUser(user);
                }
                sendUserInfo(user,objOut);
                break;
            case "update":
                login = input.nextLine();
                password = input.nextLine();
                name = input.nextLine();
                System.out.println(login+" : "+name+" : "+ password);
                storage.updateUser(login,name,password);

                break;
            case "fav":
                login = input.nextLine();
                length = input.nextInt();
                double size;
                int idShoes;

                List<FavoriteShoes> favoriteShoesList = new ArrayList<>();
                for (int i = 0; i < length; i++) {
                    idShoes = input.nextInt();
                    size = input.nextDouble();
                    favoriteShoesList.add(new FavoriteShoes(idShoes,size));
                }
                user = storage.getUser(login);
                user.setFavoriteShoesList(favoriteShoesList);
                storage.printFavorite();
                break;

            case "base":
                login = input.nextLine();
                length = input.nextInt();



                break;
            default:
                break;
        }
    }
}
