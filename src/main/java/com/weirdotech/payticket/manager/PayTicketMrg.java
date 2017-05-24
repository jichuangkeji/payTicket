package com.weirdotech.payticket.manager;

import com.weirdotech.payticket.bean.PayTicketInfo;
import com.weirdotech.payticket.service.IPayTicketService;
import com.weirdotech.payticket.service.RetrofitWrapper;

import retrofit2.Call;

/**
 * Created by Bingo on 17/5/17.
 */
public class PayTicketMrg {
    private static PayTicketMrg sInstance;
    private IPayTicketService mPayTicketSerice;

    public static PayTicketMrg getInstance() {
        if(sInstance == null) {
            sInstance = new PayTicketMrg();
        }
        return sInstance;
    }

    private PayTicketMrg() {
        mPayTicketSerice = RetrofitWrapper.getInstance().create(IPayTicketService.class);
    }

    public Call<PayTicketInfo> listTickets(String queryKey) {
        return mPayTicketSerice.listTickets(queryKey);
    }



}
