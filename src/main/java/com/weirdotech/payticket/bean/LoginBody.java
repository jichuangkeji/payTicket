package com.weirdotech.payticket.bean;

/**
 * Created by Bingo on 17/5/17.
 */
public class LoginBody {
    public String email;
    public String password;

    public LoginBody(String email, String password) {
        this.email = email;
        this.password = password;
    }

    @Override
    public String toString() {
        return "[email: " + email + ", password " + password + "]";
    }
}
