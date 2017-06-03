package com.weirdotech.payticket.service;

import com.weirdotech.payticket.bean.PayTicketInfo;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Url;
import rx.Observable;

import static com.weirdotech.payticket.constant.RequestConstant.LIST_TICKET_PLATE_PATH;
import static com.weirdotech.payticket.constant.RequestConstant.SEARCH_KEY;

/**
 * Created by Bingo on 17/5/11.
 */
public interface IPayTicketService {

    @GET(LIST_TICKET_PLATE_PATH)
    Observable<PayTicketInfo> listTickets(@Path(SEARCH_KEY) String searchKey);

    @GET
    Call<ResponseBody> showImage(@Url String uril);
}
