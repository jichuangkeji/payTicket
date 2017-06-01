package com.weirdotech.payticket.bean;

/**
 * Created by Bingo on 17/6/1.
 */

import java.io.Serializable;

/**
 *  查询出来的罚单的重点内容
 */
public class ViolationInfo implements Serializable{
    private String violation;
    private String view_ticket;
    private String plate_details;
    private String issue_date;
    private String liability;
    private String pending;
    private String violation_description;
    private String name;
    private boolean appear;
    private boolean pay_enable;

    public String getViolation() {
        return violation;
    }

    public void setViolation(String violation) {
        this.violation = violation;
    }

    public String getView_ticket() {
        return view_ticket;
    }

    public void setView_ticket(String view_ticket) {
        this.view_ticket = view_ticket;
    }

    public String getPlate_details() {
        return plate_details;
    }

    public void setPlate_details(String plate_details) {
        this.plate_details = plate_details;
    }

    public String getIssue_date() {
        return issue_date;
    }

    public void setIssue_date(String issue_date) {
        this.issue_date = issue_date;
    }

    public String getLiability() {
        return liability;
    }

    public void setLiability(String liability) {
        this.liability = liability;
    }

    public String getPending() {
        return pending;
    }

    public void setPending(String pending) {
        this.pending = pending;
    }

    public String getViolation_description() {
        return violation_description;
    }

    public void setViolation_description(String violation_description) {
        this.violation_description = violation_description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isAppear() {
        return appear;
    }

    public void setAppear(boolean appear) {
        this.appear = appear;
    }

    public boolean isPay_enable() {
        return pay_enable;
    }

    public void setPay_enable(boolean pay_enable) {
        this.pay_enable = pay_enable;
    }
}
