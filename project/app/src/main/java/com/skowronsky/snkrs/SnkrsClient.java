package com.skowronsky.snkrs;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

@SuppressLint("SetTextI18n")
public class SnkrsClient {
    Thread Thread1 = null;

    String SERVER_IP = "192.168.21.1";
    int SERVER_PORT = 4444;
    boolean closeConnection = false;

    private Activity activity;

    public SnkrsClient(Activity activity){
        this.activity = activity;
    }

    public void connect(){
        closeConnection = false;
        Thread1 = new Thread(new Thread1());
        Thread1.start();
    }

    public void disconnect(){
        closeConnection = true;
    }

    private PrintWriter output;
    private BufferedReader input;

    class Thread1 implements Runnable {

        public void run() {
            Socket socket;
            try {
                socket = new Socket(SERVER_IP, SERVER_PORT);
                output = new PrintWriter(socket.getOutputStream());
                input = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.i("SnkrsServer","Connected");
                    }
                });
                new Thread(new Thread2()).start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    class Thread2 implements Runnable {
        @Override
        public void run() {
            while (!closeConnection) {
                try {
                    final String message = input.readLine();
                    if (message != null) {
                        activity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Log.i("SnkrsServer","server: " + message + "\n");
                            }
                        });
                    } else {
                        Thread1 = new Thread(new Thread1());
                        Thread1.start();
                        return;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
