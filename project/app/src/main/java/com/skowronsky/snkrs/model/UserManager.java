package com.skowronsky.snkrs.model;

/**
 * Klasa odpowiedzialna za wykonywanie działań tyczących się uźytkownika
 */
public class UserManager{
    private User user;
    public UserManager(User user){
        this.user = user;
    }

    public String getUsername(){
        return user.getName();
    }

    public String getEmail() {return  user.getEmail(); }
}
