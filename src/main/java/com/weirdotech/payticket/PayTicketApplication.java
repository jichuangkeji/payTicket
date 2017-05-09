package com.weirdotech.payticket;

import android.app.Application;
import android.content.Context;

import com.weirdotech.payticket.utils.Log;

/**
 * Created by Bingo on 17/5/10.
 */
public class PayTicketApplication extends Application {
    private static final String TAG = PayTicketApplication.class.getSimpleName();
    private static Context sContext;

    @Override
    public void onCreate() {
        super.onCreate();
        sContext = this;
        Log.d(TAG, " PayTicketApplication onCreate ");
    }

    public static Context getContext() {
        return sContext;
    }
}
