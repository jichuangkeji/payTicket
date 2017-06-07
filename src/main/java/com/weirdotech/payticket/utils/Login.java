package com.weirdotech.payticket.utils;

import android.app.Activity;
import android.content.Intent;
import android.widget.Toast;

import com.weirdotech.payticket.activity.HomeActivity;
import com.weirdotech.payticket.bean.LoginBody;
import com.weirdotech.payticket.bean.LoginResult;
import com.weirdotech.payticket.manager.UserMrg;
import com.weirdotech.payticket.utils.dialog.WaitDialogUtils;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Bingo on 17/6/7.
 */
public class Login {



    public static void login(final UserMrg userMrg, final Activity activity, final String viewFlag) {
        login(userMrg, null, activity, viewFlag);
    }

    public static void login(final UserMrg userMrg, LoginBody loginBody, final Activity activity, final String viewFlag) {
        Observable<LoginResult> observable = null;

        if (loginBody == null) {
            observable = userMrg.login();
        } else {
            observable = userMrg.login(loginBody);
        }

        observable.subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<LoginResult>() {

                    @Override
                    public void onStart() {
                        super.onStart();
                        WaitDialogUtils.show(viewFlag, activity);
                    }

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        WaitDialogUtils.hide(viewFlag);
                        Toast.makeText(activity, "登录失败: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        userMrg.resetLoginResult();
                    }

                    @Override
                    public void onNext(LoginResult loginResult) {
                        WaitDialogUtils.hide(viewFlag);

                        userMrg.saveLoginResult(loginResult);

                        if (loginResult.isLogined()) {
                            handleLoginEvent(activity);
                            Toast.makeText(activity, loginResult.getLoginResultMsg(), Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(activity, loginResult.toString(), Toast.LENGTH_SHORT).show();
                        }


                    }
                });
    }

    private static void handleLoginEvent(final Activity activity) {
        MainThread.getInstance().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(activity, HomeActivity.class);
                activity.startActivity(intent);
                activity.finish();
            }
        }, 500);

    }
}
