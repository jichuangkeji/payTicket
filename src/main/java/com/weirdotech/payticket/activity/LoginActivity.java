package com.weirdotech.payticket.activity;

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
import com.weirdotech.payticket.bean.RegisterBody;
import com.weirdotech.payticket.bean.RegisterResult;
import com.weirdotech.payticket.manager.UserMrg;
import com.weirdotech.payticket.utils.AnimationUtils;
import com.weirdotech.payticket.utils.MainThread;
import com.weirdotech.payticket.utils.dialog.WaitDialogUtils;
import com.weirdotech.widgets.progress.RoundProgressBar;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

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

    @Bind(R.id.registerBtn)
    protected View mRegisterBtn;

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

    @Bind(R.id.usernameEdit)
    protected EditText mUsernameTvForReg;

    @Bind(R.id.emailEditReg)
    protected EditText mEmailEditReg;

    @Bind(R.id.passwdEditReg)
    protected EditText mPasswdEditReg;

    @Bind(R.id.passwdEditReg2)
    protected EditText mPasswdEditReg2;

    private UserMrg mUserMrg;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
        ButterKnife.bind(this);

        initModel();
        setView();
    }

    private void initModel() {
        mUserMrg = UserMrg.getInstance();
    }


    private void setView() {
        mEmailEdit.setText(mUserMrg.getEmail());
        mPasswdEdit.requestFocus();
    }

    @OnClick(R.id.noAccTipBtn)
    public void onNoAccTipBtnClick() {
        mTipLayout.setVisibility(View.GONE);
        mHasAccBtn.setVisibility(View.VISIBLE);
        mLoginBtn.setVisibility(View.GONE);
        mRegisterBtn.setVisibility(View.VISIBLE);

        AnimationUtils.fadeSize(mLoginLayout, 1f, 0f);
        AnimationUtils.fadeSize(mSignUpLayout, 0f, 1f);
    }

    @OnClick(R.id.hasAccTipBtn)
    public void onHasAccTipBtn() {
        mTipLayout.setVisibility(View.VISIBLE);
        mHasAccBtn.setVisibility(View.GONE);
        mLoginBtn.setVisibility(View.VISIBLE);
        mRegisterBtn.setVisibility(View.GONE);

        AnimationUtils.fadeSize(mLoginLayout, 0f, 1f);
        AnimationUtils.fadeSize(mSignUpLayout, 1f, 0f);
    }

    @OnClick(R.id.forgetPasswdTipBtn)
    public void onForgetPasswdBtnClick() {
        Toast.makeText(this, "忘记密码 Under Develop", Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.loginBtn)
    public void onLoginBtnClick() {
        String email = mEmailEdit.getText().toString().trim();
        String password = mPasswdEdit.getText().toString().trim();
        LoginBody body = new LoginBody(email, password);
        mUserMrg.saveLoginBody(body);

        mUserMrg.login(body)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<LoginResult>() {

                    @Override
                    public void onStart() {
                        super.onStart();
                        WaitDialogUtils.show(TAG, LoginActivity.this);
                    }

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        WaitDialogUtils.hide(TAG);
                        Toast.makeText(LoginActivity.this, "登录失败111 t: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNext(LoginResult loginResult) {
                        WaitDialogUtils.hide(TAG);

                        mUserMrg.saveLoginResult(loginResult);

                        if (loginResult.isLogined()) {
                            handleLoginEvent();
                            Toast.makeText(LoginActivity.this, loginResult.getLoginResultMsg(), Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(LoginActivity.this, loginResult.toString(), Toast.LENGTH_SHORT).show();
                        }



                    }
                });
    }

    @OnClick(R.id.registerBtn)
    public void onRegisterBtnClick() {
        // TODO: 17/6/3  进行账户有效性的判断

        if (isValidRegisterInfo()) {
            register();

        } else {
            // TODO: 17/6/3
        }

    }

    private boolean isValidRegisterInfo() {
        // TODO: 17/6/3  
        return true;
    }

    private void register() {
        String email = mEmailEditReg.getText().toString().trim();
        String username = mUsernameTvForReg.getText().toString().trim();
        String password = mPasswdEditReg.getText().toString().trim();

        mUserMrg.register(new RegisterBody(email, username, password))
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<RegisterResult>() {

                    @Override
                    public void onStart() {
                        super.onStart();
                        WaitDialogUtils.show(TAG, LoginActivity.this);
                    }

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        WaitDialogUtils.hide(TAG);
                        Toast.makeText(LoginActivity.this, "注册失败,原因： " + e.getMessage(), Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onNext(RegisterResult registerResult) {
                        WaitDialogUtils.hide(TAG);


                        if (registerResult.getStatus_code() == 200) {
                            Toast.makeText(LoginActivity.this, "注册成功", Toast.LENGTH_SHORT).show();

                        } else {
                            Toast.makeText(LoginActivity.this, "注册失败, 原因：" + registerResult.toString(),
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void handleLoginEvent() {
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
