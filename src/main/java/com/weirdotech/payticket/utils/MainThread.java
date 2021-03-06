/**
 * Copyright (c) 2001-2008 Starnet Info-Tech Co., Ltd.
 * All rights reserved.
 */


package com.weirdotech.payticket.utils;

import android.os.Handler;
import android.os.Looper;

/* @author
 * @date 15-12-10下午8:01
 * @description:
 * 
 */
public class MainThread {
    private static MainThread sInstance;
    private Handler mHandler = new Handler(Looper.getMainLooper());

    public MainThread() {
    }

    public static synchronized MainThread getInstance() {
        if (sInstance == null) {
            sInstance = new MainThread();
        }
        return sInstance;
    }

    public void post(Runnable runnable) {
        mHandler.post(runnable);
    }

    public void postDelayed(Runnable runnable, long time) {
        mHandler.postDelayed(runnable, time);
    }

    public void remove(Runnable runnable) {
        mHandler.removeCallbacks(runnable);
    }
}
