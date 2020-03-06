package com.skowronsky.snkrs;

import android.app.Application;

import com.skowronsky.snkrs.model.User;
import com.skowronsky.snkrs.model.UserManager;

public class MyApplication extends Application {

    User user = new User("123","rooot@root.pl", "root", "Kiemon");
    public UserManager userManager = new UserManager(user);


    @Override
    public void onCreate() {
        super.onCreate();

    }
}

