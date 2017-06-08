package com.weirdotech.payticket.bean;

import com.weirdotech.payticket.utils.StringUtils;

/**
 * Created by Bingo on 17/5/18.
 */
public class LoginResult {

    @Override
    public String toString() {
        return "[error_code: " + error_code + ", message: "
                + message + ", code: " + code + ", error_msg: " + error_msg + "]";
    }

    /**
     * error_code : 100
     * message : 账号或密码错误
     */

    private int error_code;
    private String message;

    /**
     * code : 200
     * error_msg :
     * data : {"name":null,"email":"bingo575395982@163.com","mobile":null,"avatar":null,"birthday":null,"vehicle_plate":null,"token":"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOjExLCJpc3MiOiJodHRwczpcL1wvd3d3Lmd1dWZhci5jb21cL2FwaVwvdXNlclwvbG9naW4iLCJpYXQiOjE0OTUwMzcwMjQsImV4cCI6MTQ5NzM0MTAyNCwibmJmIjoxNDk1MDM3MDI0LCJqdGkiOiJiNTFkZWMyMzFhZjk0MzY3MjQyMGRiNmRkMDFlOTcwMSJ9.ec3mbY8f4t3bFz1LFDsiVSO54HFJfsQPPIw8PfhHRws"}
     */

    private String code;
    private String error_msg;
    /**
     * name : null
     * email : bingo575395982@163.com
     * mobile : null
     * avatar : null
     * birthday : null
     * vehicle_plate : null
     * token : eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOjExLCJpc3MiOiJodHRwczpcL1wvd3d3Lmd1dWZhci5jb21cL2FwaVwvdXNlclwvbG9naW4iLCJpYXQiOjE0OTUwMzcwMjQsImV4cCI6MTQ5NzM0MTAyNCwibmJmIjoxNDk1MDM3MDI0LCJqdGkiOiJiNTFkZWMyMzFhZjk0MzY3MjQyMGRiNmRkMDFlOTcwMSJ9.ec3mbY8f4t3bFz1LFDsiVSO54HFJfsQPPIw8PfhHRws
     */

    private LoginInfo data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getError_msg() {
        return error_msg;
    }

    public void setError_msg(String error_msg) {
        this.error_msg = error_msg;
    }

    public LoginInfo getLoginInfo() {
        return data;
    }

    public void setData(LoginInfo data) {
        this.data = data;
    }

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

    public boolean isLogined() {
        boolean isSuccess = false;
        //判断成功
        if(!StringUtils.isNullOrEmpty(code) && code.equals("200")) {
            isSuccess = true;
        }

        return isSuccess;
    }

    public String getLoginResultMsg() {
        return isLogined() ? "登录成功" : "登录失败";
    }
}
