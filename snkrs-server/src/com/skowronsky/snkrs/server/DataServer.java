package com.skowronsky.snkrs.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Date;

public class DataServer {

    final static int portNumber = 4444;
    static String SERVER_IP = "";
    static InetAddress inetAddress;

    public static void main(String[] args) {
        try {
            inetAddress = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        try (var serverSocket = new ServerSocket(portNumber)){
            System.out.println("Server IP: " + inetAddress.getHostAddress());
            System.out.println("The date server is running...");

            while (true){
                try (Socket clientSocket = serverSocket.accept()){
                    var out = new PrintWriter(clientSocket.getOutputStream(),true);
                    out.println(new Date().toString());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
