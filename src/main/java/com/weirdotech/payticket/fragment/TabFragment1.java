package com.weirdotech.payticket.fragment;

import android.content.Context;
import android.database.ContentObserver;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.weirdotech.payticket.R;
import com.weirdotech.payticket.utils.Log;
import com.weirdotech.payticket.utils.MainThread;
import com.weirdotech.payticket.utils.contact.ContactProviderUtils;
import com.weirdotech.payticket.utils.contact.SearchItem;
import com.weirdotech.payticket.utils.contact.SearchMrg;

import java.util.ArrayList;
import java.util.List;

import static com.weirdotech.payticket.utils.contact.ContactProviderUtils.EAB_CHANGE_TYPE;
import static com.weirdotech.payticket.utils.contact.ContactProviderUtils.EAB_FAVORITE_CHANGE_TYPE;
import static com.weirdotech.payticket.utils.contact.ContactProviderUtils.LOCAL_CHANGE_TYPE;
import static com.weirdotech.payticket.utils.contact.ContactProviderUtils.*;

public class TabFragment1 extends Fragment {
    String key = "";
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_tab, null);
        TextView tv = (TextView) inflate.findViewById(R.id.txt);
        tv.setBackgroundColor(Color.BLUE);

        Uri base = Uri.parse(HOST + "bingo" + "/1212/local/dsdsds/");
        String[] sds = base.getPath().split("/");
        Log.e("", " show uri: quey path:" + sds[0] + ", :" + sds[1] + ", :" + sds[2]);
        View btn = inflate.findViewById(R.id.queryBtn);
        final EditText  queryKey = (EditText)inflate.findViewById(R.id.queryKey);
        final EditText  queryType = (EditText)inflate.findViewById(R.id.queryType);
        final ListView lv = (ListView)inflate.findViewById(R.id.lv);
        final MyAdapter adapter = new MyAdapter(new ArrayList<SearchItem>(), getActivity());
        lv.setAdapter(adapter);



        final SearchMrg searchMrg = new SearchMrg(getActivity(), new SearchMrg.SearchCallback() {
            @Override
            public void onSearchFinish(final String searchType, final String searchKey, final List<SearchItem> searchItems) {
                Log.e("", " testhbp onSearchFinish "
                        + "searchKey: " + searchKey
                        + ", searchType: " + searchType
                        + ", searchItems.size: " + searchItems.size());

                Log.e("", " testhbp onSearchFinish key: " + key + ", searchKey: " + searchKey
                        + ", isSame: " + key.toUpperCase().equals(searchKey.toUpperCase()));

//                if(!key.toUpperCase().equals(searchKey.toUpperCase())) {
//                    return;
//                }

                MainThread.getInstance().post(new Runnable() {
                    @Override
                    public void run() {
                        adapter.update(searchItems);
                    }
                });

            }
        });

        queryKey.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String searchKey = s.toString().trim();
                String searchType = queryType.getText().toString().trim();

                key = searchKey;
                searchMrg.startSearch(searchKey, searchType);
            }
        });


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });



        getActivity().getContentResolver().registerContentObserver(ContactProviderUtils.CHANGE_URI, true, new ContentObserver(null) {

            @Override
            public void onChange(boolean selfChange, Uri uri) {
                super.onChange(selfChange, uri);
                String type = uri.getLastPathSegment();
                android.util.Log.d("", " testhbp paytickect: contact_change: " + type);

                switch (type) {
                    case LOCAL_CHANGE_TYPE:
                        String searchKey = queryKey.getText().toString().trim();
                        String searchType = queryType.getText().toString().trim();
                        searchMrg.startSearch(searchKey, searchType);
                        break;

                    case EAB_CHANGE_TYPE:
                    case EAB_FAVORITE_CHANGE_TYPE:

                }

            }
        });




        return inflate;
    }

    @Override
    public void onResume() {
        super.onResume();


    }

    public static class MyAdapter extends BaseAdapter {
        private List<SearchItem> mItem = new ArrayList<>();
        private Context mContext;
        public MyAdapter(List<SearchItem> item, Context context) {
            mItem = item;
            mContext = context;
        }

        public void update(List<SearchItem> item) {
            if(item ==null) {
                mItem = new ArrayList<>();
            } else {
                mItem = item;
            }

            notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            return mItem.size();
        }

        @Override
        public Object getItem(int position) {
            return mItem.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.list_view_item_layout, null);
            TextView tv1 = (TextView) view.findViewById(R.id.lvItemTv1);
            TextView tv2 = (TextView) view.findViewById(R.id.lvItemTv2);

            tv1.setText(mItem.get(position).getNameSpan(new ForegroundColorSpan(Color.GREEN)));
            tv2.setText(mItem.get(position).getNumberSpan(new ForegroundColorSpan(Color.GREEN)));


            return view;
        }
    }
}
