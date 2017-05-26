package com.weirdotech.payticket.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.wangjie.shadowviewhelper.ShadowProperty;
import com.wangjie.shadowviewhelper.ShadowViewDrawable;
import com.weirdotech.payticket.R;
import com.weirdotech.payticket.activity.MainActivity;
import com.weirdotech.payticket.bean.LogoutResult;
import com.weirdotech.payticket.manager.UserMrg;
import com.weirdotech.payticket.utils.DensityUtil;
import com.weirdotech.payticket.utils.Log;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Bingo on 17/5/26.
 */
public class MeFragment extends Fragment {
    private static final String TAG = MeFragment.class.getSimpleName();

    @Bind(R.id.personalInfoLayout)
    protected View mPersonalView;

//    @Bind(R.id.photoImg)
//    protected CircleTextImageView mPhotoMrg;

    @Bind(R.id.usernameTv)
    protected TextView mUsernameTv;

    @Bind(R.id.logoutBtn)
    protected View mLogoutBtn;

    private UserMrg mUserMrg;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initModel();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = initView(inflater, container);
        initListener();
        setView();
        return rootView;
    }


    private void initModel() {
        mUserMrg = UserMrg.getInstance();
    }

    private void setView() {

        ShadowProperty sp = new ShadowProperty()
                .setShadowColor(Color.parseColor("#33DED9D8"))
                .setShadowDy(DensityUtil.dip2px(getContext(), 0.5f))
                .setShadowRadius(DensityUtil.dip2px(getContext(), 3))
                .setShadowSide(ShadowProperty.TOP | ShadowProperty.BOTTOM);
        ShadowViewDrawable sd = new ShadowViewDrawable(sp, Color.WHITE, 0, 0);

        ViewCompat.setBackground(mPersonalView, sd);
        ViewCompat.setLayerType(mPersonalView, ViewCompat.LAYER_TYPE_SOFTWARE, null);

        ViewCompat.setBackground(mLogoutBtn, sd);
        ViewCompat.setLayerType(mLogoutBtn, ViewCompat.LAYER_TYPE_SOFTWARE, null);

        mUsernameTv.setText(mUserMrg.getEmail());


    }

    private void initListener() {

    }

    private View initView(LayoutInflater inflater, ViewGroup container) {
        View rootView = inflater.inflate(R.layout.me_fragment_layout, container, false);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @OnClick(R.id.logoutBtn)
    public void onLogoutBtnClick() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        Log.d("", "onLogoutBtnClick: token:" + mUserMrg.getLoginedResult().getData().getToken());
        builder.setMessage("是否确认退出登录?")
                .setTitle("退出登录")
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.dismiss();
                    }
                }).setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String token = mUserMrg.getLoginedResult().getData().getToken();
                        Log.e(TAG, " testhb logout token: " + token);
                        Call<LogoutResult> logoutCall = mUserMrg.logout(token);

                        logoutCall.enqueue(new Callback<LogoutResult>() {
                            @Override
                            public void onResponse(Call<LogoutResult> call, Response<LogoutResult> response) {
                                LogoutResult result = response.body();

                                if(result.isSuccess()) {
                                    mUserMrg.resetLoginResult();

                                    Intent intent  = new Intent(getContext(), MainActivity.class);
                                    getActivity().startActivity(intent);
                                }

                                Toast.makeText(getContext(), "成功 response.msg: "
                                        + response.message()
                                        + ", isSuccessfull: " + response.isSuccessful()
                                        , Toast.LENGTH_SHORT).show();
                                if(result != null) {

                                    Log.e(TAG, " testhb logout onResponse: " + result.toString());
                                }
                            }

                            @Override
                            public void onFailure(Call<LogoutResult> call, Throwable t) {
                                Toast.makeText(getContext(), "注销失败111 t: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });

                        dialog.dismiss();
                    }
                }).show();
    }


}
