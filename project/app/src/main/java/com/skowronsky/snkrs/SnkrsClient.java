package com.skowronsky.snkrs;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.Date;

public class SnkrsClient {
    Thread connectionThread = null;

    String SERVER_IP = "192.168.1.12";
    int SERVER_PORT = 59898;

    private Context context;

    public SnkrsClient(){}
    public SnkrsClient(Context context){
        this.context = context;
    }

    public void connect(){
        connectionThread = new Thread(new ConnectionThread());
        connectionThread.start();
    }


    private PrintWriter output;
    private BufferedReader input;

    class ConnectionThread implements Runnable {

        Socket socket;

        @RequiresApi(api = Build.VERSION_CODES.O)
        @SuppressLint("RestrictedApi")
        public void run() {
            try {
                socket = new Socket(SERVER_IP,SERVER_PORT);
                output = new PrintWriter(socket.getOutputStream(),true);
                input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String message = "";


                Log.i("SnkrsServer","Connected");

                int time = LocalDateTime.now().getSecond();

                for (int i = 0; i < 100000; i++) {

                    output.println(i+" Hello!!!");

                }


                output.println("QQQ");

                do{
                    message = input.readLine();
                    Log.i("SnkrsServer","Messege: "+ message);

                }while (!message.equals("QQQ"));
                int time2 = LocalDateTime.now().getSecond();
                Log.i("SnkrsServer","Time: "+ (time2-time));


            } catch (IOException e) {
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
