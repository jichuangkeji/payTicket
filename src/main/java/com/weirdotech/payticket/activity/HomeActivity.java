package com.weirdotech.payticket.activity;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.weirdotech.payticket.R;
import com.weirdotech.payticket.fragment.TabFragment1;
import com.weirdotech.payticket.fragment.TabFragment2;
import com.weirdotech.payticket.fragment.TabFragment3;
import com.weirdotech.payticket.fragment.TabFragment4;
import com.weirdotech.widgets.bottomtab.BottomTabBaseActivity;
import com.weirdotech.widgets.bottomtab.BottomTabView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bingo on 17/5/18.
 */
public class HomeActivity extends BottomTabBaseActivity {


    @Override
    protected List<BottomTabView.TabItemView> getTabViews() {
        int norColor = R.color.colorPrimary;
        int pressColor = R.color.colorGreen;
        Context context = this;

        List<BottomTabView.TabItemView> tabItemViews = new ArrayList<>();

        tabItemViews.add(new BottomTabView.TabItemView(
                context, context.getString(R.string.home),
                norColor, pressColor,
                R.drawable.home_icon, R.drawable.home_icon));

        tabItemViews.add(new BottomTabView.TabItemView(
                context, context.getString(R.string.search),
                norColor, pressColor,
                R.drawable.search_icon, R.drawable.search_icon));

        tabItemViews.add(new BottomTabView.TabItemView(
                context, context.getString(R.string.notify),
                norColor, pressColor,
                R.drawable.notifications_icon, R.drawable.notifications_icon));

        tabItemViews.add(new BottomTabView.TabItemView(
                context, context.getString(R.string.me),
                norColor, pressColor,
                R.drawable.me_icon, R.drawable.me_icon));

        return tabItemViews;
    }

    @Override
    protected List<Fragment> getFragments() {
        List<Fragment> fragments = new ArrayList<>();

        fragments.add(new TabFragment1());
        fragments.add(new TabFragment2());
        fragments.add(new TabFragment3());
        fragments.add(new TabFragment4());

        return fragments;
    }

    @Override
    protected View getCenterView() {
        View view = LayoutInflater.from(this).inflate(R.layout.home_center_view_layout, null);
        ImageView centerView = (ImageView)view.findViewById(R.id.centerView);
        return view;
    }
}
