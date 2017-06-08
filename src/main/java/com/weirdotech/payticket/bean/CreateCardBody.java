package com.weirdotech.payticket.bean;

/**
 * Created by Bingo on 17/6/8.
 */
public class CreateCardBody {
    public String number;
    public String exp_year;
    public String exp_month;
    public String cvc;
    public String name;

    public CreateCardBody(String number, String exp_year, String exp_month, String cvc, String name) {
        this.number = number;
        this.exp_year = exp_year;
        this.exp_month = exp_month;
        this.cvc = cvc;
        this.name = name;
    }
}
