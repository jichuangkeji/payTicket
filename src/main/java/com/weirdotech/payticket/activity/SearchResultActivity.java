package com.weirdotech.payticket.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.weirdotech.payticket.R;
import com.weirdotech.payticket.adapter.ViolationInfoAdapter;
import com.weirdotech.payticket.bean.ViolationInfo;
import com.weirdotech.payticket.constant.Constants;
import com.weirdotech.payticket.service.IPayTicketService;
import com.weirdotech.payticket.service.RetrofitWrapper;
import com.weirdotech.payticket.utils.Log;
import com.weirdotech.payticket.utils.PicUtils;
import com.weirdotech.widgets.topbar.TopBarBaseActivity;

import java.io.IOException;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Bingo on 17/6/2.
 */
public class SearchResultActivity extends TopBarBaseActivity {
    private static final String TAG = SearchResultActivity.class.getSimpleName();

    @Bind(R.id.payBtn)
    protected View mPayBtn;

    @Bind(R.id.violationInfoLv)
    protected ListView mViolationInfoLv;

    @Bind(R.id.emptyTv)
    protected View mEmptyTv;
    private ViolationInfoAdapter mAdapter;

    private IPayTicketService mService;
    @Override
    protected int getContentView() {
        return R.layout.query_result_activity;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        ButterKnife.bind(this);
        initModel();
        initView();
        initData();
        setView();
        initListener();
    }

    private void initModel() {
        mService = RetrofitWrapper.getInstance().create(IPayTicketService.class);
    }

    private void initListener() {
        mViolationInfoLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ViolationInfo info = (ViolationInfo)parent.getAdapter().getItem(position);
//                Toast.makeText(SearchResultActivity.this, "info.showImageID: " + info.getView_ticket(), Toast.LENGTH_SHORT).show();

                mService.showImage(info.getView_ticket()).enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        Bitmap bitmap = null;
                        try {
                            bitmap = PicUtils.getPicFromBytes(response.body().bytes(), null);
                        } catch (IOException e) {
                            Log.e(TAG, " onResponse getPicFromBytes error e.msg: " + e.getMessage());
                            e.printStackTrace();
                        }
                        Toast.makeText(SearchResultActivity.this, "showImage onResponse :"+ response.isSuccessful()
                                + ", bitmap: " + bitmap
                                , Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(SearchResultActivity.this, "showImage t.msg :"+ t.getMessage()
                                , Toast.LENGTH_SHORT).show();
                    }
                });

//                new Callback() {
//                    @Override
//                    public void onResponse(Call call, Response response) {
//                        Toast.makeText(SearchResultActivity.this, "showImage onResponse :"+ response.isSuccessful()
//                                , Toast.LENGTH_SHORT).show();
//                    }
//
//                    @Override
//                    public void onFailure(Call call, Throwable t) {
//                        Toast.makeText(SearchResultActivity.this, "showImage t.msg :"+ t.getMessage()
//                                , Toast.LENGTH_SHORT).show();
//                    }
//                }

            }
        });
    }

    private void initView() {
        setTopLeftButton(new OnClickListener() {
            @Override
            public void onClick() {
                finish();
            }
        });
        setTitle(getString(R.string.search_result_title));
    }

    private void initData() {
        List<ViolationInfo> info = (List<ViolationInfo>)getIntent().
                getSerializableExtra(Constants.VIOLATION_INFO_KEY);
        mAdapter = new ViolationInfoAdapter(this, info);
    }

    private void setView() {
        mViolationInfoLv.setEmptyView(mEmptyTv);
        mViolationInfoLv.setAdapter(mAdapter);
    }

    @OnClick(R.id.payBtn)
    public void onPayBtnClick() {
    }


}
