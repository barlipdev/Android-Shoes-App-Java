package com.skowronsky.snkrs.model;

public class User {
    private String token;
    private String email;
    private String password;
    private String username;

    public User(String token, String email, String password, String username){
        this.token = token;
        this.email = email;
        this.password = password;
        this.username = username;
    }

    public String getEmail(){
        return email;
    }
    public String getUsername(){
        return username;
    }
    public void setUsername(String newUsername){
        username = newUsername;
    }
}
