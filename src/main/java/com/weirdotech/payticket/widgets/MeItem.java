package com.weirdotech.payticket.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.weirdotech.payticket.R;
import com.weirdotech.payticket.utils.StringUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Bingo on 17/6/8.
 */
public class MeItem extends LinearLayout{
    private static final String TAG = MeItem.class.getSimpleName();
    private Context mContext;
    private CustomizedAttrs mCustomizedAttrs;

    @Bind(R.id.itemIcon)
    protected ImageView mItemIcon;

    @Bind(R.id.itemTitle)
    protected TextView mItemTitle;

    public MeItem(Context context) {
        super(context);
    }

    public MeItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initView();
        initCustomizedAttr(attrs, 0);
        setView();
    }

    private void initView() {
        inflate(getContext(), R.layout.me_item_layout, this);
        ButterKnife.bind(this);

    }

    private void initCustomizedAttr(AttributeSet attrs, int defStyleAttr) {
        mCustomizedAttrs = new CustomizedAttrs();

        TypedArray typeArr = mContext.getTheme()
                .obtainStyledAttributes(attrs, R.styleable.meItemStyle, defStyleAttr, 0);


        for (int attrIndex = 0; attrIndex < typeArr.getIndexCount(); attrIndex++) {
            int attr = typeArr.getIndex(attrIndex);

            if (attr == R.styleable.meItemStyle_itemIcon) {
                mCustomizedAttrs.icon = typeArr.getDrawable(attr);

            } else if (attr == R.styleable.meItemStyle_itemTitle) {
                mCustomizedAttrs.title = typeArr.getString(attr);
            }

        }
    }

    private void setView() {
        if(mCustomizedAttrs.icon != null) {
            mItemIcon.setImageDrawable(mCustomizedAttrs.icon);
        }

        if(!StringUtils.isNullOrEmpty(mCustomizedAttrs.title)) {
            mItemTitle.setText(mCustomizedAttrs.title);
        }
    }

    private static class CustomizedAttrs {
        public Drawable icon = null;
        public String title = "";
    }

}
