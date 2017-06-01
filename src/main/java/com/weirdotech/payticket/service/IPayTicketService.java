package com.weirdotech.payticket.service;

import com.weirdotech.payticket.bean.PayTicketInfo;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

import static com.weirdotech.payticket.constant.RequestConstant.LIST_TICKET_PLATE_PATH;
import static com.weirdotech.payticket.constant.RequestConstant.SEARCH_KEY;

/**
 * Created by Bingo on 17/5/11.
 */
public interface IPayTicketService {

    @GET(LIST_TICKET_PLATE_PATH)
    Observable<PayTicketInfo> listTickets(@Path(SEARCH_KEY) String searchKey);
}
