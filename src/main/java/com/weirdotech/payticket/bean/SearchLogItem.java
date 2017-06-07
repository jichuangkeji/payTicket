package com.weirdotech.payticket.bean;

/**
 * Created by Bingo on 17/6/8.
 */
public class SearchLogItem {
    private String created_at;
    private String plate;
    private Object ticket;
    private int ticket_count;
    private String pay_sum;

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public Object getTicket() {
        return ticket;
    }

    public void setTicket(Object ticket) {
        this.ticket = ticket;
    }

    public int getTicket_count() {
        return ticket_count;
    }

    public void setTicket_count(int ticket_count) {
        this.ticket_count = ticket_count;
    }

    public String getPay_sum() {
        return pay_sum;
    }

    public void setPay_sum(String pay_sum) {
        this.pay_sum = pay_sum;
    }
}
