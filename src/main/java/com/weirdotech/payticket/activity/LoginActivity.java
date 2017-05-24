package com.weirdotech.payticket.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.weirdotech.payticket.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Bingo on 17/5/24.
 */
public class LoginActivity extends AppCompatActivity {
    private static final String TAG = LoginActivity.class.getSimpleName();

    @Bind(R.id.noAccTipBtn)
    protected View mNoAccBtn;

    @Bind(R.id.hasAccTipBtn)
    protected View mHasAccBtn;

    @Bind(R.id.loginBtn)
    protected View mLoginBtn;

    @Bind(R.id.signUpBtn)
    protected View mSignUpBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.noAccTipBtn)
    public void onNoAccTipBtnClick() {
        mNoAccBtn.setVisibility(View.GONE);
        mHasAccBtn.setVisibility(View.VISIBLE);
        mLoginBtn.setVisibility(View.GONE);
        mSignUpBtn.setVisibility(View.VISIBLE);
    }

    @OnClick(R.id.hasAccTipBtn)
    public void onHasAccTipBtn() {
        mNoAccBtn.setVisibility(View.VISIBLE);
        mHasAccBtn.setVisibility(View.GONE);
        mLoginBtn.setVisibility(View.VISIBLE);
        mSignUpBtn.setVisibility(View.GONE);
    }
}
