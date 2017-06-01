package com.weirdotech.payticket.webconf.model;

/**
 * Created by Bingo on 17/5/31.
 */
public class ConferDepart {
    private String departId;
    private String departName;
    private String superiorDepartmentId;
    private String userNum;
    private String status;

    public String getDepartId() {
        return departId;
    }

    public void setDepartId(String departId) {
        this.departId = departId;
    }

    public String getDepartName() {
        return departName;
    }

    public void setDepartName(String departName) {
        this.departName = departName;
    }

    public String getSuperiorDepartmentId() {
        return superiorDepartmentId;
    }

    public void setSuperiorDepartmentId(String superiorDepartmentId) {
        this.superiorDepartmentId = superiorDepartmentId;
    }

    public String getUserNum() {
        return userNum;
    }

    public void setUserNum(String userNum) {
        this.userNum = userNum;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
