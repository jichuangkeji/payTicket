package com.weirdotech.payticket.manager;

import android.content.Context;

import com.weirdotech.payticket.PayTicketApplication;
import com.weirdotech.payticket.bean.LoginBody;
import com.weirdotech.payticket.bean.LoginResult;
import com.weirdotech.payticket.bean.LogoutResult;
import com.weirdotech.payticket.bean.RegisterBody;
import com.weirdotech.payticket.bean.RegisterResult;
import com.weirdotech.payticket.service.IUserService;
import com.weirdotech.payticket.service.RetrofitWrapper;
import com.weirdotech.payticket.utils.PreferenceUtils;
import com.weirdotech.payticket.utils.StringUtils;

import retrofit2.Call;

import static com.weirdotech.payticket.constant.RequestConstant.IS_LOGINED;
import static com.weirdotech.payticket.constant.RequestConstant.LOGIN_KEY_EMAIL;
import static com.weirdotech.payticket.constant.UserConstant.IS_PREV_LOGIN;

/**
 * Created by Bingo on 17/5/17.
 */
public class UserMrg {

    private static UserMrg sInstance;
    private IUserService mUserService;
    private LoginResult mLoginedRsult;
    private Context mContext;
    private LoginResult mLoginedResult;

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

    public Call<LogoutResult> logout(String token) {
        return mUserService.logout(token);
    }

    public boolean isPrevLogin() {
        return PreferenceUtils.getPrefBoolean(mContext, IS_PREV_LOGIN, false);
    }

    public void saveLoginBody(LoginBody body) {
        PreferenceUtils.setPrefString(mContext, LOGIN_KEY_EMAIL, body.email);
    }

    public void saveLoginResult(LoginResult result) {
        PreferenceUtils.setPrefBoolean(mContext, IS_LOGINED, result.isLogined());

        if(result.isLogined()) {
            mLoginedResult = result;
        }
    }

    public void resetLoginResult() {
        PreferenceUtils.setPrefBoolean(mContext, IS_LOGINED, false);
        mLoginedResult = null;
    }

    public boolean isLogined() {
        return PreferenceUtils.getPrefBoolean(mContext, IS_LOGINED, false);
    }

    public String getEmail() {
        return PreferenceUtils.getPrefString(mContext, LOGIN_KEY_EMAIL, "");
    }

    public LoginResult getLoginedResult() {
        return mLoginedResult;
    }

    public String getUsername() {
        String username = "";

        if(mLoginedResult != null
                && !StringUtils.isNullOrEmpty((String)mLoginedResult.getData().getName())) {
            username = (String)mLoginedResult.getData().getName();
        }

        return username;
    }

    public void logout() {
        mLoginedResult = null;
    }


}
