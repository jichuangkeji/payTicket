package com.weirdotech.payticket.manager;

import android.content.Context;

import com.weirdotech.payticket.PayTicketApplication;
import com.weirdotech.payticket.bean.LoginBody;
import com.weirdotech.payticket.bean.LoginResult;
import com.weirdotech.payticket.bean.RegisterBody;
import com.weirdotech.payticket.bean.RegisterResult;
import com.weirdotech.payticket.service.IUserService;
import com.weirdotech.payticket.service.RetrofitWrapper;
import com.weirdotech.payticket.utils.PreferenceUtils;

import retrofit2.Call;
import static com.weirdotech.payticket.constant.RequestConstant.*;

import static com.weirdotech.payticket.constant.UserConstant.IS_PREV_LOGIN;

/**
 * Created by Bingo on 17/5/17.
 */
public class UserMrg {

    private static UserMrg sInstance;
    private IUserService mUserService;
    private LoginResult mLoginedRsult;
    private Context mContext;

    public static UserMrg getInstance() {
        if(sInstance == null) {
            sInstance = new UserMrg();
        }
        return sInstance;
    }

    private UserMrg() {
        mUserService = RetrofitWrapper.getInstance().create(IUserService.class);
        mContext = PayTicketApplication.getContext();
    }

    public Call<RegisterResult> register(RegisterBody body) {
        return mUserService.register(body);
    }

    public Call<LoginResult> login(LoginBody body) {
        return mUserService.login(body);
    }

    public boolean isPrevLogin() {
        return PreferenceUtils.getPrefBoolean(mContext, IS_PREV_LOGIN, false);
    }

    public void saveLoginBody(LoginBody body) {
        PreferenceUtils.setPrefString(mContext, LOGIN_KEY_EMAIL, body.email);
    }

    public void saveLoginStatus(boolean isLogined) {
        PreferenceUtils.setPrefBoolean(mContext, IS_LOGINED, isLogined);
    }

    public boolean isLogined() {
        return PreferenceUtils.getPrefBoolean(mContext, IS_LOGINED, false);
    }

    public String getEmail() {
        return PreferenceUtils.getPrefString(mContext, LOGIN_KEY_EMAIL, "");
    }

}
