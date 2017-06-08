package com.weirdotech.payticket.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.weirdotech.payticket.R;
import com.weirdotech.payticket.bean.LoginResult;
import com.weirdotech.payticket.manager.UserMrg;
import com.weirdotech.payticket.utils.Log;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private UserMrg mUserMrg;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity_layout);
        initModel();
        showScene();
    }

    private void initModel() {
        mUserMrg = UserMrg.getInstance();
    }

    /**
     * （1）进行登录界面
     * （2）自动登录后进入主界面的
     */
    private void showScene() {


        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
        finish();

        if(mUserMrg.isLogin()) {
            //进行自动登录
            Log.e(TAG, " doAutoLogin ");
            doAutoLogin();
        }
    }

    private void doAutoLogin() {
        mUserMrg.login()
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<LoginResult>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, " doAutoLogin e: " + e.getMessage());
                        mUserMrg.resetLoginResult();
                    }

                    @Override
                    public void onNext(LoginResult loginResult) {
                        Log.e(TAG, " doAutoLogin onNext loginResult: " + loginResult.toString() + ", loginInfo: " + loginResult.getLoginInfo());
                        mUserMrg.saveLoginResult(loginResult);
                    }
                });

    }

}
