package com.weirdotech.payticket.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.weirdotech.payticket.R;
import com.weirdotech.payticket.bean.ViolationInfo;

import java.util.List;

/**
 * Created by Bingo on 17/6/2.
 */
public class ViolationInfoAdapter extends BingoAdapter<ViolationInfo> {
    private static final String TAG = ViolationInfoAdapter.class.getSimpleName();

    public ViolationInfoAdapter(Context context, List<ViolationInfo> datas) {
        super(context, datas);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if(convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.violation_info_item_layout, null);
            holder = new ViewHolder();

            holder.violationNumTv = (TextView) convertView.findViewById(R.id.violationNumTv);
            holder.plateDetailsTv = (TextView) convertView.findViewById(R.id.plateDetailsTv);
            holder.descriptionTv = (TextView) convertView.findViewById(R.id.descriptionTv);
            holder.issueDateTv = (TextView) convertView.findViewById(R.id.issueDateTv);
            holder.liabilityTv = (TextView) convertView.findViewById(R.id.liabilityTv);
            holder.pendingTv = (TextView) convertView.findViewById(R.id.pendingTv);
            holder.willPayTv = (TextView) convertView.findViewById(R.id.willPayTv);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder)convertView.getTag();
        }

        ViolationInfo info = getItem(position);

        holder.violationNumTv.setText(info.getViolation());
        holder.plateDetailsTv.setText(info.getPlate_details());
        holder.descriptionTv.setText(info.getViolation_description());
        holder.issueDateTv.setText(info.getIssue_date());
        holder.liabilityTv.setText(info.getLiability());
        holder.pendingTv.setText(info.getPending());
        holder.willPayTv.setText("");

        return convertView;
    }

    public static class ViewHolder {
        public TextView violationNumTv;
        public TextView plateDetailsTv;
        public TextView descriptionTv;
        public TextView issueDateTv;
        public TextView liabilityTv;
        public TextView pendingTv;
        public TextView willPayTv;




    }
}
