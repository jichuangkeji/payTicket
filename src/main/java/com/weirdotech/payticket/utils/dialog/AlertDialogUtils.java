package com.weirdotech.payticket.utils.dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

/**
 * Created by Bingo on 17/6/3.
 * 具有
 */
public class AlertDialogUtils {

    public static AlertDialog show(Context context, int titleResId, int msgResId,
                            int negBtnTitleResId, int posBtnTitleResId,
                            DialogInterface.OnClickListener negBtnListener,
                            DialogInterface.OnClickListener posBtnListener) {

        AlertDialog dialog = new AlertDialog.Builder(context)
                .setTitle(titleResId)
                .setMessage(msgResId)
                .setNegativeButton(negBtnTitleResId, negBtnListener)
                .setPositiveButton(posBtnTitleResId, posBtnListener)
                .create();

        dialog.show();

        return dialog;
    }

    public static AlertDialog show(Context context, String title, String msg,
                            String negBtnTitle, String posBtnTitle,
                            DialogInterface.OnClickListener negBtnListener,
                            DialogInterface.OnClickListener posBtnListener) {

        AlertDialog dialog = new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(msg)
                .setNegativeButton(negBtnTitle, negBtnListener)
                .setPositiveButton(posBtnTitle, posBtnListener)
                .create();

        dialog.show();

        return dialog;
    }
}
