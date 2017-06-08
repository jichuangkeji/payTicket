package com.weirdotech.payticket.bean;

/**
 * Created by Bingo on 17/6/8.
 */
public class LoginInfo {
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
