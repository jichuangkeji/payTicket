package com.weirdotech.payticket.webconf.model;

/**
 * Created by Bingo on 17/5/31.
 */
public class ConferUser {
    private String userId;
    private String userName;
    private String mobile;
    private String email;
    private String belongCompanyId;
    private String belongDepartmentId;
    private String status;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBelongCompanyId() {
        return belongCompanyId;
    }

    public void setBelongCompanyId(String belongCompanyId) {
        this.belongCompanyId = belongCompanyId;
    }

    public String getBelongDepartmentId() {
        return belongDepartmentId;
    }

    public void setBelongDepartmentId(String belongDepartmentId) {
        this.belongDepartmentId = belongDepartmentId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
