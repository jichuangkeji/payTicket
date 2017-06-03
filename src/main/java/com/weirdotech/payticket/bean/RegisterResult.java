package com.weirdotech.payticket.bean;

/**
 * Created by Bingo on 17/5/17.
 */
public class RegisterResult {
    /**
     * status_code : 1000
     * message : 该账号已被注册了, 请换一个
     */

    /**
     * status_code: 200
     * message": 注册成功
     */

    private int status_code;
    private String message;

    public int getStatus_code() {
        return status_code;
    }

    public void setStatus_code(int status_code) {
        this.status_code = status_code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "[code : " + status_code + ", msg: " + message +"]";
    }
}
