package com.skowronsky.snkrs;

import android.app.Application;
import android.content.Context;

import com.skowronsky.snkrs.model.User;
import com.skowronsky.snkrs.model.UserManager;
import com.skowronsky.snkrs.storage.Storage;

public class MyApplication extends Application {

    User user = new User("123","rooot@root.pl", "root", "Kiemon");
    public UserManager userManager = new UserManager(user);
    public Storage storage = new Storage();
    public SnkrsClient snkrsClient = new SnkrsClient(storage);

    @Override
    public void onCreate() {
        super.onCreate();

    }
}

