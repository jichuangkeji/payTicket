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

    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private Object name;
        private String email;
        private Object mobile;
        private Object avatar;
        private Object birthday;
        private Object vehicle_plate;
        private String token;

        public Object getName() {
            return name;
        }

        public void setName(Object name) {
            this.name = name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public Object getMobile() {
            return mobile;
        }

        public void setMobile(Object mobile) {
            this.mobile = mobile;
        }

        public Object getAvatar() {
            return avatar;
        }

        public void setAvatar(Object avatar) {
            this.avatar = avatar;
        }

        public Object getBirthday() {
            return birthday;
        }

        public void setBirthday(Object birthday) {
            this.birthday = birthday;
        }

        public Object getVehicle_plate() {
            return vehicle_plate;
        }

        public void setVehicle_plate(Object vehicle_plate) {
            this.vehicle_plate = vehicle_plate;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
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
