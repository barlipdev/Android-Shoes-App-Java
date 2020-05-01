package com.skowronsky.snkrs;

import android.app.Application;
import android.content.Context;

import com.skowronsky.snkrs.model.User;
import com.skowronsky.snkrs.storage.Storage;

public class MyApplication extends Application {

//    public UserManager userManager = new UserManager(user);
    public Storage storage = Storage.getInstance();
    private Context context;
    public SnkrsClient snkrsClient;
//    public SneakersRepository sneakersRepository = new SneakersRepository((Application) getApplicationContext());



    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        snkrsClient  = SnkrsClient.getInstance(storage,context);
        snkrsClient.connect();
//        snkrsClient.signup("1","1","1");
//        snkrsClient.login("root@root.com","root");

//        snkrsClient.updateUser("12","12","12");
    }
}

