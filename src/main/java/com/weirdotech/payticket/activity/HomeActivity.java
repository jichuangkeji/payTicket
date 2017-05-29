package com.weirdotech.payticket.activity;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.weirdotech.payticket.R;
import com.weirdotech.payticket.fragment.MeFragment;
import com.weirdotech.payticket.fragment.NotifyFragment;
import com.weirdotech.payticket.fragment.QueryFragment;
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
        int norColor = R.color.icon_text_gray_color;
        int pressColor = R.color.primaryBlueColor;
        Context context = this;

        List<BottomTabView.TabItemView> tabItemViews = new ArrayList<>();

//        tabItemViews.add(new BottomTabView.TabItemView(
//                context, context.getString(R.string.home),
//                norColor, pressColor,
//                R.drawable.home_icon, R.drawable.home_icon));

        tabItemViews.add(new BottomTabView.TabItemView(
                context, context.getString(R.string.query),
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

        fragments.add(new QueryFragment());
        fragments.add(new NotifyFragment());
        fragments.add(new MeFragment());

        return fragments;
    }

    @Override
    protected View getCenterView() {
        View view = LayoutInflater.from(this).inflate(R.layout.home_center_view_layout, null);
        ImageView centerView = (ImageView)view.findViewById(R.id.centerView);
        return null;
    }
}
