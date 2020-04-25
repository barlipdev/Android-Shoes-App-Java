package com.skowronsky.snkrs;

import android.app.Application;
import android.content.Context;

import com.skowronsky.snkrs.model.User;
import com.skowronsky.snkrs.model.UserManager;
import com.skowronsky.snkrs.storage.Storage;

public class MyApplication extends Application {

//    User user = new User("123","rooot@root.pl", "root", "Kiemon");
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
        snkrsClient.connect("root@root.com","root");
    }
}

