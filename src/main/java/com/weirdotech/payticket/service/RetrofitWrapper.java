package com.weirdotech.payticket.service;

import com.weirdotech.payticket.constant.RequestConstant;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Bingo on 17/5/16.
 */
public class RetrofitWrapper {
    private static RetrofitWrapper sInstance;
    private Retrofit mRetrofit;

    public static synchronized RetrofitWrapper getInstance() {
        if(sInstance == null) {
            sInstance = new RetrofitWrapper();
        }
        return sInstance;
    }

    private RetrofitWrapper() {
        mRetrofit = new Retrofit.Builder().baseUrl(RequestConstant.SERVER_URL)
                .addConverterFactory(GsonConverterFactory.create()).build();
    }

    public <T> T create(final Class<T> service){
        return mRetrofit.create(service);
    }


}
