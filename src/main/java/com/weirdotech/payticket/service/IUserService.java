package com.weirdotech.payticket.service;

import com.weirdotech.payticket.bean.LoginBody;
import com.weirdotech.payticket.bean.LoginResult;
import com.weirdotech.payticket.bean.RegisterBody;
import com.weirdotech.payticket.bean.RegisterResult;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

import static com.weirdotech.payticket.constant.RequestConstant.REGISTER_PATH;

/**
 * 注册，登录的Service
 */
public interface IUserService {

    @FormUrlEncoded
    @POST(REGISTER_PATH)
    Call<RegisterResult> register(@Body RegisterBody body);

    @FormUrlEncoded
    @POST(REGISTER_PATH)
    Call<LoginResult> login(@Body LoginBody body);

}
