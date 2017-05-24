package com.weirdotech.widgets.circlebutton;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.ImageButton;

import com.weirdotech.widgets.R;


public class CircleButton extends ImageButton {
    private int mSize;
    private static final int SIZE_MINI = 0;
    private static final int SIZE_SMALL = 1;
    private static final int SIZE_NORMAL = 2;
    private static final int SIZE_BIG = 3;
    private CircleButtonImpl mImpl;

    public CircleButton(Context context) {
        this(context, null);
    }

    public CircleButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (attrs != null) {
            TypedArray attributes =
                    context.obtainStyledAttributes(attrs, R.styleable.CircleButtonOptions, 0, 0);
            try {
                mSize = attributes.getInt(R.styleable.CircleButtonOptions_Size, SIZE_NORMAL);
            } finally {
                attributes.recycle();
            }
        }

        mImpl = new CircleButtonImpl(this);
    }

    /*final int getSizeDimension() {
        switch (mSize) {
            case SIZE_MINI:
                return getResources().getDimensionPixelSize(android.support.design.R.dimen.design_fab_size_mini);
            case SIZE_NORMAL:
            default:
                return getResources().getDimensionPixelSize(android.support.design.R.dimen.design_fab_size_normal);
        }
    }*/


}
