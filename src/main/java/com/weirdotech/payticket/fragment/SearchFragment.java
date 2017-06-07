package com.weirdotech.payticket.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.weirdotech.payticket.R;
import com.weirdotech.payticket.activity.SearchResultActivity;
import com.weirdotech.payticket.adapter.SearchLogAdapter;
import com.weirdotech.payticket.bean.PayTicketInfo;
import com.weirdotech.payticket.bean.SearchLog;
import com.weirdotech.payticket.bean.SearchLogItem;
import com.weirdotech.payticket.constant.Constants;
import com.weirdotech.payticket.manager.PayTicketMrg;
import com.weirdotech.payticket.manager.UserMrg;
import com.weirdotech.payticket.utils.Log;
import com.weirdotech.payticket.utils.StringUtils;
import com.weirdotech.payticket.utils.dialog.WaitDialogUtils;
import com.weirdotech.payticket.webconf.ConfContactMrg;
import com.weirdotech.payticket.webconf.ContactAdapter;
import com.weirdotech.payticket.webconf.LoginResult;
import com.weirdotech.payticket.webconf.SynBody;
import com.weirdotech.payticket.webconf.SynResult;

import java.io.Serializable;
import java.util.Arrays;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Subscriber;


/**
 * Created by Bingo on 17/5/27.
 */
public class SearchFragment extends Fragment {
    private static final String TAG = SearchFragment.class.getSimpleName();

    @Bind(R.id.operViewPager)
    protected ViewPager mViewPager;

    @Bind(R.id.searchBtn)
    protected View mSearchBtn;

    @Bind(R.id.searchKeyEdit)
    protected EditText mSearchKeyEdit;

    private PagerAdapter mPagerAdapter;

    @Bind(R.id.contactLv)
    protected ListView mLv;
    @Bind(R.id.logItemLv)
    protected ListView mLogItemlv;

    public LoginResult mResult;
    private ConfContactMrg mMrg;
    protected ContactAdapter mAdapter;
    private PayTicketMrg mPayTicketMrg;
    private UserMrg mUserMrg;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initModel();
        iniData();
    }

    private void initModel() {
        mMrg = ConfContactMrg.createInstance(getContext());
        mPayTicketMrg = PayTicketMrg.getInstance();
        mUserMrg = UserMrg.getInstance();
    }

    private void iniData() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = initView(inflater, container);
        initListener();
        setView();
        return rootView;
    }

    private void setView() {
    }

    @Override
    public void onResume() {
        super.onResume();
        renderView();
    }

    private void renderView() {
        if (mUserMrg.isLogin() && mUserMrg.getLoginedResult() != null) {
            Log.e(TAG, " 进行显示查询记录");
            renderSearchLogs();
        }
    }

    private void renderSearchLogs(){
        String token = mUserMrg.getLoginedResult().getData().getToken();
        mPayTicketMrg.listSearchLogs(token, new Subscriber<SearchLog>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, " renderSearchLogs onError: " + e.getMessage());
            }

            @Override
            public void onNext(SearchLog searchLog) {
                Log.e(TAG, " renderSearchLogs searchLog: " + searchLog.toString());
                SearchLogAdapter mSearchLogAdapter = new SearchLogAdapter(getContext(), searchLog.getData());
                mLogItemlv.setAdapter(mSearchLogAdapter);
            }
        });
    }

    private void initListener() {

        mLogItemlv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if(parent.getAdapter() instanceof SearchLogAdapter) {
                    SearchLogItem item = ((SearchLogAdapter)parent.getAdapter()).getItem(position);
                    listTickets(item.getPlate());
                }

            }
        });

    }

    private View initView(LayoutInflater inflater, ViewGroup container) {
        View rootView = inflater.inflate(
                R.layout.search_fragment_layout, container, false);
        ButterKnife.bind(this, rootView);
        return rootView;
    }


    private class SearchSubscribe extends Subscriber<PayTicketInfo> {

        @Override
        public void onStart() {
            super.onStart();
            WaitDialogUtils.show(TAG, getContext());
        }

        @Override
        public void onCompleted() {
            WaitDialogUtils.hide(TAG);
        }

        @Override
        public void onError(Throwable e) {
            Toast.makeText(getContext(), "查询失败，原因: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            WaitDialogUtils.hide(TAG);
        }

        @Override
        public void onNext(PayTicketInfo payTicketInfo) {
            int listSize = payTicketInfo.getData().size();
//            Toast.makeText(getContext(), "查询成功 ! size: " + listSize + ", code: " + payTicketInfo.getCode()
//                    , Toast.LENGTH_SHORT).show();

            //进行查询信息的显示
            Intent intent = new Intent(getContext(), SearchResultActivity.class);
            intent.putExtra(Constants.VIOLATION_INFO_KEY, (Serializable) payTicketInfo.getData());
            getActivity().startActivity(intent);

        }
    }


    @OnClick(R.id.searchBtn)
    public void onSearchBtnClick() {
        String searchKey = mSearchKeyEdit.getText().toString().trim();
        Log.d(TAG, " searchKey: " + searchKey);
        if (StringUtils.isNullOrEmpty(searchKey)) {
            Toast.makeText(getContext(), "请输入车牌号或者罚单号", Toast.LENGTH_SHORT).show();
        } else {
            listTickets(searchKey);

        }

    }

    private void listTickets(String searchKey) {
        mPayTicketMrg.listTickets(searchKey, new SearchSubscribe());
    }

    @OnClick(R.id.queryBtn)
    public void onQueryBtnClick() {

        ConfContactMrg mrg = ConfContactMrg.createInstance(getContext());
        mrg.login("13910043151", "mobile123", new Subscriber<LoginResult>() {


            @Override
            public void onCompleted() {
                SynBody body = new SynBody();
                body.setEnterpriseId(mResult.getEnterpriseId());
                Integer[] flags = new Integer[]{1, 2, 3};
                body.setFlag(Arrays.asList(flags));

                mMrg.synEnterprise(mResult.getToken(), body, new Subscriber<SynResult>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(SynResult synResult) {
                        Log.e(TAG, " tetshb date is update ");
                        if (mAdapter == null) {
                            mAdapter = new ContactAdapter(synResult.getDeparts(), getContext());
                            mLv.setAdapter(mAdapter);
                        } else {
                            mAdapter.update(synResult.getDeparts());
                        }


                    }
                });
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(LoginResult loginResult) {
                mResult = loginResult;
            }
        });
    }

    @OnClick(R.id.searchLogBtn)
    public void onSearcgLogBtnClick() {
        Toast.makeText(getContext(), "searchLogBtn", Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.payLogBtn)
    public void onPayLogBtnClick() {
        Toast.makeText(getContext(), "payLogBtn", Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.unPayLogBtn)
    public void onNnPayLogBtnClick() {
        Toast.makeText(getContext(), "unPayLogBtn", Toast.LENGTH_SHORT).show();
    }

}
