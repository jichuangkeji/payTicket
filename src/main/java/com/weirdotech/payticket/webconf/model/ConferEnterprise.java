package com.weirdotech.payticket.webconf.model;

/**
 * Created by Bingo on 17/5/31.
 */
public class ConferEnterprise {
    private String enterpriseId;
    private String name;
    private String departmentNum;

    public String getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(String enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartmentNum() {
        return departmentNum;
    }

    public void setDepartmentNum(String departmentNum) {
        this.departmentNum = departmentNum;
    }
}
