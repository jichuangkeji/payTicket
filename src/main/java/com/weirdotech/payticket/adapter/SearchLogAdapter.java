package com.weirdotech.payticket.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.weirdotech.payticket.R;
import com.weirdotech.payticket.bean.SearchLogItem;

import java.util.List;

/**
 * Created by Bingo on 17/6/8.
 */
public class SearchLogAdapter extends BingoAdapter<SearchLogItem> {
    private static final String TAG = SearchLogAdapter.class.getSimpleName();

    public SearchLogAdapter(Context context, List<SearchLogItem> datas) {
        super(context, datas);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.search_log_item_layout, null);
            holder = new ViewHolder();

            holder.createTv = (TextView) convertView.findViewById(R.id.createTv);
            holder.plateTv = (TextView) convertView.findViewById(R.id.plateTv);
            holder.ticketNumTv = (TextView) convertView.findViewById(R.id.ticketNumTv);
            holder.payNumTv = (TextView) convertView.findViewById(R.id.payNumTv);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        SearchLogItem item = getItem(position);

        holder.createTv.setText(item.getCreated_at());
        holder.plateTv.setText(item.getPlate());
        holder.ticketNumTv.setText(item.getTicket_count() + "");
        holder.payNumTv.setText("$" + item.getPay_sum());


        return convertView;
    }

    public static class ViewHolder {
        public TextView createTv;
        public TextView plateTv;
        public TextView ticketNumTv;
        public TextView payNumTv;
    }


}
