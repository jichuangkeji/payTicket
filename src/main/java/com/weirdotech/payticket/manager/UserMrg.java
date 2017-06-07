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
import rx.Observable;

import static com.weirdotech.payticket.constant.UserConstant.IS_LOGINED;
import static com.weirdotech.payticket.constant.UserConstant.*;

/**
 * Created by Bingo on 17/5/17.
 */
public class UserMrg {
    private static final String TAG = UserMrg.class.getSimpleName();
    private static UserMrg sInstance;
    private IUserService mUserService;
    private Context mContext;
    private LoginResult mLoginedResult;
    private LoginBody mLoginedBody;

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

    public Observable<RegisterResult> register(RegisterBody body) {
        return mUserService.register(body);
    }

    public Observable<LoginResult> login(LoginBody body) {
        return mUserService.login(body);
    }

    public Observable<LoginResult> login() {
        LoginBody body = new LoginBody(getEmail(), getPassword());
        return mUserService.login(body);
    }

    public Call<LogoutResult> logout(String token) {
        return mUserService.logout(token);
    }

    /**
     * 判断用户是否 成功登录了，用于下次点击软件后，进行自动登录
     * @return
     */
    public boolean isLogin() {
        return PreferenceUtils.getPrefBoolean(mContext, IS_LOGINED, false);
    }

    /**
     * 保存用户登录时候的账户信息
     * @param body
     */
    public void saveLoginBody(LoginBody body) {
        mLoginedBody = body;
        PreferenceUtils.setPrefString(mContext, LOGIN_KEY_EMAIL, body.email);
        PreferenceUtils.setPrefString(mContext, LOGIN_KEY_PASSWORD, body.password);
    }

    /**
     * 保存 登录后的 响应信息，
     * （1）错误码 和 提示信息
     * （2）是否成功登录的标志位
     * @param result
     */
    public void saveLoginResult(LoginResult result) {
        PreferenceUtils.setPrefBoolean(mContext, IS_LOGINED, result.isLogined());

        if(result.isLogined()) {
            mLoginedResult = result;
        }
    }

    public void resetLoginResult() {
        mLoginedBody = null;
        PreferenceUtils.setPrefBoolean(mContext, IS_LOGINED, false);
        mLoginedResult = null;
    }

    public String getEmail() {
        return PreferenceUtils.getPrefString(mContext, LOGIN_KEY_EMAIL, "");
    }

    private String getPassword() {
        return PreferenceUtils.getPrefString(mContext, LOGIN_KEY_PASSWORD, "");
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
