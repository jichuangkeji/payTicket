package com.weirdotech.payticket.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.weirdotech.payticket.R;
import com.weirdotech.payticket.bean.LoginBody;
import com.weirdotech.payticket.bean.LoginResult;
import com.weirdotech.payticket.manager.UserMrg;
import com.weirdotech.payticket.utils.AnimationUtils;
import com.weirdotech.payticket.utils.MainThread;
import com.weirdotech.widgets.progress.RoundProgressBar;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Bingo on 17/5/24.
 */
public class LoginActivity extends AppCompatActivity {
    private static final String TAG = LoginActivity.class.getSimpleName();

    @Bind(R.id.noAccTipBtn)
    protected View mNoAccBtn;

    @Bind(R.id.hasAccTipBtn)
    protected View mHasAccBtn;

    @Bind(R.id.forgetPasswdTipBtn)
    protected View mForgetPasswdBtn;

    @Bind(R.id.tip1Layout)
    protected View mTipLayout;

    @Bind(R.id.loginBtn)
    protected View mLoginBtn;

    @Bind(R.id.signUpBtn)
    protected View mSignUpBtn;

    @Bind(R.id.loginLayout)
    protected View mLoginLayout;

    @Bind(R.id.signUpLayout)
    protected View mSignUpLayout;

    @Bind(R.id.waitProgress)
    protected RoundProgressBar mProgress;

    @Bind(R.id.emailEdit)
    protected EditText mEmailEdit;

    @Bind(R.id.passwordEdit)
    protected EditText mPasswdEdit;


    private ProgressDialog mWaitDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
        ButterKnife.bind(this);

        setView();
    }

    private void setView() {
        mEmailEdit.setText(UserMrg.getInstance().getEmail());
        mPasswdEdit.requestFocus();
    }

    @OnClick(R.id.noAccTipBtn)
    public void onNoAccTipBtnClick() {
        mTipLayout.setVisibility(View.GONE);
        mHasAccBtn.setVisibility(View.VISIBLE);
        mLoginBtn.setVisibility(View.GONE);
        mSignUpBtn.setVisibility(View.VISIBLE);

        AnimationUtils.fadeSize(mLoginLayout, 1f, 0f);
        AnimationUtils.fadeSize(mSignUpLayout, 0f, 1f);
    }

    @OnClick(R.id.hasAccTipBtn)
    public void onHasAccTipBtn() {
        mTipLayout.setVisibility(View.VISIBLE);
        mHasAccBtn.setVisibility(View.GONE);
        mLoginBtn.setVisibility(View.VISIBLE);
        mSignUpBtn.setVisibility(View.GONE);

        AnimationUtils.fadeSize(mLoginLayout, 0f, 1f);
        AnimationUtils.fadeSize(mSignUpLayout, 1f, 0f);
    }

    @OnClick(R.id.forgetPasswdTipBtn)
    public void onForgetPasswdBtnClick() {
        Toast.makeText(this, "忘记密码 Under Develop", Toast.LENGTH_SHORT).show();
    }

    private void showWaitDialog() {
        dismissWaitDialog();

        mWaitDialog = new ProgressDialog(this);
        mWaitDialog.setCancelable(false);
        mWaitDialog.show();
    }

    private void dismissWaitDialog() {
        if(mWaitDialog != null) {
            mWaitDialog.dismiss();
        }
    }

    @OnClick(R.id.loginBtn)
    public void onLoginBtnClick() {
        String email = mEmailEdit.getText().toString().trim();
        String password = mPasswdEdit.getText().toString().trim();
        LoginBody body = new LoginBody(email, password);
        UserMrg.getInstance().saveLoginBody(body);

        Call<LoginResult> call = UserMrg.getInstance().login(body);

        showWaitDialog();

        call.enqueue(new Callback<LoginResult>() {
            @Override
            public void onResponse(Call<LoginResult> call, Response<LoginResult> response) {
                LoginResult result = response.body();
                UserMrg.getInstance().saveLoginResult(result);

                Toast.makeText(LoginActivity.this, result.getLoginResultMsg(), Toast.LENGTH_SHORT).show();
                dismissWaitDialog();

                handleLoginEvent(result.isLogined());

            }

            @Override
            public void onFailure(Call<LoginResult> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "登录失败111 t: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                dismissWaitDialog();

            }
        });

    }

    @OnClick(R.id.signUpBtn)
    public void onSignUpBtnClick() {

    }

    private void handleLoginEvent(boolean isLogined) {
        if(isLogined) {
            MainThread.getInstance().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                    startActivity(intent);

                    finish();
                }
            }, 500);

        }
    }


}
