package com.weirdotech.payticket.model;

import com.weirdotech.payticket.bean.LoginBody;
import com.weirdotech.payticket.bean.LoginResult;
import com.weirdotech.payticket.bean.RegisterBody;
import com.weirdotech.payticket.bean.RegisterResult;
import com.weirdotech.payticket.service.IUserService;
import com.weirdotech.payticket.service.RetrofitWrapper;

import retrofit2.Call;

/**
 * Created by Bingo on 17/5/17.
 */
public class UserModel {

    private static UserModel sInstance;
    private IUserService mUserService;
    private LoginResult mLoginedRsult;

    public static UserModel getInstance() {
        if(sInstance == null) {
            sInstance = new UserModel();
        }
        return sInstance;
    }

    private UserModel() {
        mUserService = RetrofitWrapper.getInstance().create(IUserService.class);
    }

    public Call<RegisterResult> register(RegisterBody body) {
        return mUserService.register(body);
    }

    public Call<LoginResult> login(LoginBody body) {
        return mUserService.login(body);
    }

}
