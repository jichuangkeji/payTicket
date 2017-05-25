package com.weirdotech.payticket.utils;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.view.View;

/**
 * Created by Bingo on 17/5/25.
 */
public class AnimationUtils {

    public static void fadeSize(final View view, final Float start, final Float end) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(view, "zhy", start, end).setDuration(500);
        animator.start();
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float cVal = (Float) animation.getAnimatedValue();
                view.setAlpha(cVal);
                view.setScaleX(cVal);

                if(cVal == end) {

                    if(start > end) {
                        view.setVisibility(View.GONE);
                    } else {
                        view.setVisibility(View.VISIBLE);
                    }


                }
            }

        });

    }
}
