package com.skowronsky.snkrs.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.concurrent.Executors;

public class DataServer {

    final static int portNumber = 4444;
    static InetAddress inetAddress;

    public static void main(String[] args) {
        try {
            inetAddress = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        try (var serverSocket = new ServerSocket(portNumber)){
            System.out.println("Server IP: " + inetAddress.getHostAddress());
            System.out.println("Data server is running...");

            var pool = Executors.newFixedThreadPool(20);
            while (true) {
                pool.execute(new Capitalizer(serverSocket.accept()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
