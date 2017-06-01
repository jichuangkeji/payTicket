package com.weirdotech.payticket.manager;

import com.weirdotech.payticket.bean.PayTicketInfo;
import com.weirdotech.payticket.service.IPayTicketService;
import com.weirdotech.payticket.service.RetrofitWrapper;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Bingo on 17/5/17.
 */
public class PayTicketMrg {
    private static PayTicketMrg sInstance;
    private IPayTicketService mPayTicketService;

    public static PayTicketMrg getInstance() {
        if(sInstance == null) {
            sInstance = new PayTicketMrg();
        }
        return sInstance;
    }

    private PayTicketMrg() {
        mPayTicketService = RetrofitWrapper.getInstance().create(IPayTicketService.class);
    }

    public void listTickets(String searchKey, Subscriber<PayTicketInfo> subscriber) {
        mPayTicketService.listTickets(searchKey)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }



}
