package com.weirdotech.payticket.adapter;

import android.content.Context;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bingo on 17/6/2.
 */
public abstract class BingoAdapter<T> extends BaseAdapter{
    protected List<T> mDatas = new ArrayList<>();
    protected Context mContext;

    public BingoAdapter(Context context, List<T> datas) {
        mContext = context;
        refreshDatas(datas);
    }
    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public T getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void update(List<T> datas) {
        refreshDatas(datas);
        notifyDataSetChanged();
    }

    private void refreshDatas(List<T> datas) {
        if(datas != null) {
            mDatas  = datas;
        }
    }

}
