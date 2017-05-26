package com.weirdotech.payticket.bean;

/**
 * Created by Bingo on 17/5/26.
 */
public class LogoutResult {



    public boolean isSuccess() {
        return error_code == 0;
    }

    @Override
    public String toString() {
        return "[ error_code: " + error_code + ", message: " + message + "]";
    }

    /**
     * error_code: 0,
     * message: 退出登录成功
     */

    /**
     * error_code : 1002
     * message : token invalid
     */

    private int error_code;
    private String message;

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
