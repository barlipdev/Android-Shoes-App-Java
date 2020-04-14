package com.skowronsky.snkrs.model;

public class UserManager{
    private User user;
    public UserManager(User user){
        this.user = user;
    }

    public String getUsername(){
        return user.getUsername();
    }

    public String getEmail() {return  user.getEmail(); }
}
