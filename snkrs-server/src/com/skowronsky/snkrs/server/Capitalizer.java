package com.skowronsky.snkrs.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Capitalizer implements Runnable {
    private Socket socket;

    public Capitalizer(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        System.out.println("Connected: " + socket);
        try {
            var in = new Scanner(socket.getInputStream());
            var out = new PrintWriter(socket.getOutputStream(), true);

            String message = "";


            out.println("Siema Co ram");
            out.println("a Co ram");
            out.println("Essa");


            do{
                message = in.nextLine();
                System.out.println(message);
                //out.println("response to messege: "+ message);
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
