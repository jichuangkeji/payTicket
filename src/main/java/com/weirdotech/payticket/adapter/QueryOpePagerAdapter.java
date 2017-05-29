package com.weirdotech.payticket.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bingo on 17/5/27.
 */
public class QueryOpePagerAdapter extends PagerAdapter {
    private List<View> mPagers = new ArrayList<>();

    public QueryOpePagerAdapter(List<View> pagers) {
        mPagers = pagers;
    }


    @Override
    public int getCount() {
        return mPagers.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {//必须实现，实例化
        container.addView(mPagers.get(position));
        return mPagers.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {//必须实现，销毁
        container.removeView(mPagers.get(position));
    }
}
