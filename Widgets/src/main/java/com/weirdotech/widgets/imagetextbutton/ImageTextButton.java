package com.weirdotech.widgets.imagetextbutton;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.weirdotech.widgets.R;


public class ImageTextButton extends LinearLayout {

    private static final String TAG = ImageTextButton.class.getSimpleName();

    private ImageView mImg;
    private TextView mTV;
    private Context mContext = null;

    public ImageTextButton(Context context) {
        this(context, null);
        mContext = context;
    }

    protected void inflaterLayout(Context context) {
        // 导入布局 
        LayoutInflater.from(context).inflate(R.layout.widget_image_text_btn_horizontal_layout, this, true);
    }


    @SuppressLint("NewApi")
    public ImageTextButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;

        Resources.Theme theme = context.getTheme();

        inflaterLayout(context);
        // 导入布局 
//        LayoutInflater.from(context).inflate(R.layout.widget_image_text_btn_layout, this, true); 
        mImg = (ImageView) findViewById(R.id.widget_image_text_btn_Img);
        mTV = (TextView) findViewById(R.id.widget_image_text_btn_TV);
        // mBtn = (Button) findViewById(R.id.widget_image_text_btn_Btn);

        //不能够使用ImageView的click方法， 在android里点击事件消息是从内向外传递的，设置了false之后才能传递出来给LinearLayout
        mImg.setClickable(false); //谢谢 Brightshadow11111 朋友的提醒

        TypedArray typeArray = context.obtainStyledAttributes(attrs,
                R.styleable.ImgTextBtn);
        if (typeArray == null) {
            return;
        }

        int count = typeArray.getIndexCount();
        int resId = 0;
        for (int i = 0; i < count; i++) {
            int attr = typeArray.getIndex(i);
            if (attr == R.styleable.ImgTextBtn_ImgDraw) {
                Drawable background = typeArray.getDrawable(attr);
                int sdk = android.os.Build.VERSION.SDK_INT;
                if (sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
                    mImg.setBackgroundDrawable(background);
                } else {
                    mImg.setBackground(background);
                }
            } else if (attr == R.styleable.ImgTextBtn_ImgDrawWidth) {
                int imageWidth = typeArray.getDimensionPixelSize(attr, -1);
                mImg.setMaxWidth(imageWidth);
                mImg.setMinimumWidth(imageWidth);
            } else if (attr == R.styleable.ImgTextBtn_ImgDrawHeight) {
                int imageHeight = typeArray.getDimensionPixelSize(attr, -1);
                mImg.setMaxHeight(imageHeight);
                mImg.setMinimumHeight(imageHeight);
            } else if (attr == R.styleable.ImgTextBtn_ImgDrawMinWidth) {
                int imageMinWidth = typeArray.getDimensionPixelSize(attr, -1);
                mImg.setMinimumHeight(imageMinWidth);
            } else if (attr == R.styleable.ImgTextBtn_ImgDrawMinHeight) {
                int imageMinHeight = typeArray.getDimensionPixelSize(attr, -1);
                mImg.setMinimumHeight(imageMinHeight);
            } else if (attr == R.styleable.ImgTextBtn_ImgDrawMaxHeight) {
                int imageMaxHeight = typeArray.getDimensionPixelSize(attr, -1);
                mImg.setMaxHeight(imageMaxHeight);
            } else if (attr == R.styleable.ImgTextBtn_ImgDrawMaxWidth) {
                int imageMaxWidth = typeArray.getDimensionPixelSize(attr, -1);
                mImg.setMaxWidth(imageMaxWidth);
            } else if (attr == R.styleable.ImgTextBtn_TVText) {
                mTV.setText(typeArray.getText(attr));
            } else if (attr == R.styleable.ImgTextBtn_TVTextSize) {
                mTV.setTextSize(typeArray.getDimensionPixelSize(attr, 15));
            }
            else if (attr == R.styleable.ImgTextBtn_TVTextColor) {
                mTV.setTextColor(typeArray.getColor(attr,0));
            }
        }
        typeArray.recycle();
    }

    @Override
    public void refreshDrawableState() {
        // TODO Auto-generated method stub
        super.refreshDrawableState();
        //------------接下来处理联动效果，关键代码，请认真看-------------------
        //------------ ImageView控件的联动刷新 -------------------
        Drawable imgDrawable = mImg.getBackground(); //获取drawable资源
        Log.d(TAG, "drawable = " + imgDrawable);
        if (imgDrawable != null && imgDrawable.isStateful()) {
            //关键中的关键，根据当前状态设置drawable的状态，本来应该是LinearLayout的getDrawableState()状态，
            //但是现在是实现联动效果，而且获取的ImageView的getDrawState()结果不对。
            imgDrawable.setState(this.getDrawableState());
        }

        //------------- TextView的联动刷新， 抱歉， 无法刷新 ------------------
        //这块代码很快写出来了，但是写出来后发现，颜色总是停留在最后一次的颜色上，后面再点击都无法改变颜色了，
        //才恍然大悟，mTV.setTextColor()是设置TextView内部的ColorStateList对象的，这样会清掉原先的
        //res/color/colorSelector.xml里的设置，导致只有一种颜色值。应该赋值给TextView内部的private int mCurTextColor;
        //可惜没有并设置mCurTextColor值的接口，不知到使用反射能否做到，有兴趣朋友可以一试。
        //既然Text的color是靠TextPaint刷出来的，那么是否可以改变TextPaint的颜色值呢？发现有接口getPaint可以获取到TextPaint，
        //但是设置了color值之后，还是不行，哦，原来在onDraw()函数中，重新设置了颜色值，所以白设了。
        //总而言之，联动刷新颜色是不行滴，只能设一个颜色（对于我目前来讲功能已经是足够了） 哈哈，讲的够详细吧～～
        ColorStateList mTextColor = mTV.getTextColors();

        int color = mTextColor.getColorForState(this.getDrawableState(), 0);
        if (mTV.getCurrentTextColor() != color) {
            mTV.getPaint().setColor(color);
        	mTV.setTextColor(color);
            mTV.invalidate();
        }

        //-----------如果有个Button的话就可以在这里设置改变，效果同上，略---------------
//        Drawable btnDrawable = mBtn.getBackground();
//    	Log.d(TAG, "drawable = " + btnDrawable);
//    	if (btnDrawable != null && btnDrawable.isStateful()) {
//    		btnDrawable.setState(this.getDrawableState());
//    	}
//    	
//    	ColorStateList mTextColor2 = mBtn.getTextColors();
//    	int color2 = mTextColor2.getColorForState(this.getDrawableState(), 0);
//        if (mBtn.getCurrentTextColor() != color2) {
//        	mBtn.setTextColor(color2);
//        }

    }

    /**
     * 设置图片资源
     */
    public void setImgViewResource(int resId) {
        mImg.setImageResource(resId);
    }

    /**
     * 设置图片
     */
    public void setImgViewResource(Bitmap bitmap) {
        mImg.setImageBitmap(bitmap);
    }

    /**
     * 设置显示的文字
     */
    public void setTextViewText(int resId) {
        mTV.setText(resId);
    }

    /**
     * 设置显示的文字
     */
    public void setTextViewText(String text) {
        mTV.setText(text);
    }

}