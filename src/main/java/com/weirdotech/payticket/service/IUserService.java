package com.weirdotech.payticket.service;

import com.weirdotech.payticket.bean.LoginBody;
import com.weirdotech.payticket.bean.LoginResult;
import com.weirdotech.payticket.bean.LogoutResult;
import com.weirdotech.payticket.bean.RegisterBody;
import com.weirdotech.payticket.bean.RegisterResult;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

import static com.weirdotech.payticket.constant.RequestConstant.LOGIN_PATH;
import static com.weirdotech.payticket.constant.RequestConstant.LOGOUT_PATH;
import static com.weirdotech.payticket.constant.RequestConstant.REGISTER_PATH;

/**
 * 注册，登录的Service
 */
public interface IUserService {

    @POST(REGISTER_PATH)
    Observable<RegisterResult> register(@Body RegisterBody body);

    @POST(LOGIN_PATH)
    Observable<LoginResult> login(@Body LoginBody body);

    @GET(LOGOUT_PATH)
    Call<LogoutResult> logout(@Query("token") String token);
}
