package com.weirdotech.payticket.activity;

import android.os.Bundle;
import android.view.View;

import com.weirdotech.payticket.R;
import com.weirdotech.widgets.topbar.TopBarBaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Bingo on 17/6/2.
 */
public class QueryResultActivity extends TopBarBaseActivity {
    @Bind(R.id.payBtn)
    protected View mPayBtn;

    @Override
    protected int getContentView() {
        return R.layout.query_result_activity;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        ButterKnife.bind(this);
        setTopLeftButton(new OnClickListener() {
            @Override
            public void onClick() {
                finish();
            }
        });
        setTitle(getString(R.string.search_result_title));
    }

    @OnClick(R.id.payBtn)
    public void onPayBtnClick() {
    }

}
