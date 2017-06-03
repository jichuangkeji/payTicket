package com.weirdotech.payticket.bean;

/**
 * Created by Bingo on 17/5/17.
 */
public class RegisterBody {

    public String email;
    public String username;
    public String password;


    public RegisterBody(String email, String username, String password) {
        this.email = email;
        this.username = username;
        this.password = password;
    }
}
