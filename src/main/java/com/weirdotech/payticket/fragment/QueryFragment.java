package com.weirdotech.payticket.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.weirdotech.payticket.R;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * Created by Bingo on 17/5/27.
 */
public class QueryFragment extends Fragment {


    @Bind(R.id.operViewPager)
    protected ViewPager mViewPager;

    private PagerAdapter mPagerAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initModel();
        iniData();
    }

    private void initModel() {
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

    private void initListener() {

    }

    private View initView(LayoutInflater inflater, ViewGroup container) {
        View rootView = inflater.inflate(
                R.layout.query_fragment_layout, container, false);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

}
