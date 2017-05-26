package com.weirdotech.payticket.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.weirdotech.payticket.R;

import butterknife.ButterKnife;

/**
 * Created by Bingo on 17/5/27.
 */
public class NotifyFragment extends Fragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initModel();
    }

    private void initModel() {
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
        View rootView = inflater.inflate(R.layout.notify_fragment_layout, container, false);
        ButterKnife.bind(this, rootView);
        return rootView;
    }
}
