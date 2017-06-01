package com.weirdotech.payticket.webconf;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

import static com.weirdotech.payticket.webconf.ConfContactConstant.GET_TOKEN_PATH;
import static com.weirdotech.payticket.webconf.ConfContactConstant.SYNENTERPRISE_PATH;

/**
 * Created by Bingo on 17/5/31.
 */
public interface IConfContactService {

    @GET(GET_TOKEN_PATH)
    Observable<LoginResult> login(@Query("mobile") String mobile,
                                  @Query("password") String password);

    @POST(SYNENTERPRISE_PATH)
    Observable<SynResult> synEnterprise(@Header("X-Token") String token,
                                        @Body SynBody synBody);
}
