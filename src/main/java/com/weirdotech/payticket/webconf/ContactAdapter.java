package com.weirdotech.payticket.webconf;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.weirdotech.payticket.R;
import com.weirdotech.payticket.webconf.model.ConferDepart;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bingo on 17/5/31.
 */
public class ContactAdapter extends BaseAdapter{
    private List<ConferDepart> mDepts = new ArrayList<>();
    private Context mContext;

    public ContactAdapter(List<ConferDepart> depts, Context context) {
        mDepts = depts;
        mContext = context;
    }

    @Override
    public int getCount() {
        return mDepts.size();
    }

    @Override
    public Object getItem(int position) {
        return mDepts.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View root = LayoutInflater.from(mContext).inflate(R.layout.list_item_main, null);
        TextView tv = (TextView)root.findViewById(R.id.tvText);
        ConferDepart bean = mDepts.get(position);
        tv.setText("name: " + bean.getDepartName()
                + ", id: " + bean.getDepartId()
                + ", superDeptId: " + bean.getSuperiorDepartmentId() + ", usernum: " + bean.getUserNum());
        return root;
    }

    public void update(List<ConferDepart> depts) {
        mDepts = depts;
        notifyDataSetChanged();
    }


}
