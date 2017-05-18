package com.weirdotech.payticket.service;

import com.weirdotech.payticket.bean.PayTicketInfo;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

import static com.weirdotech.payticket.constant.RequestConstant.LIST_TICKET_PATH;
import static com.weirdotech.payticket.constant.RequestConstant.QUERY_KEY;

/**
 * Created by Bingo on 17/5/11.
 */
public interface IPayTicketService {

    @GET(LIST_TICKET_PATH)
    Call<PayTicketInfo> listTickets(@Path(QUERY_KEY) String queryKey);
}
