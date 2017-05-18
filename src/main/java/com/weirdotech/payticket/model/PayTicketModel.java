package com.weirdotech.payticket.model;

import com.weirdotech.payticket.bean.PayTicketInfo;
import com.weirdotech.payticket.service.IPayTicketService;
import com.weirdotech.payticket.service.RetrofitWrapper;

import retrofit2.Call;

/**
 * Created by Bingo on 17/5/17.
 */
public class PayTicketModel {
    private static PayTicketModel sInstance;
    private IPayTicketService mPayTicketSerice;

    public static PayTicketModel getInstance() {
        if(sInstance == null) {
            sInstance = new PayTicketModel();
        }
        return sInstance;
    }

    private PayTicketModel() {
        mPayTicketSerice = RetrofitWrapper.getInstance().create(IPayTicketService.class);
    }

    public Call<PayTicketInfo> listTickets(String queryKey) {
        return mPayTicketSerice.listTickets(queryKey);
    }



}
