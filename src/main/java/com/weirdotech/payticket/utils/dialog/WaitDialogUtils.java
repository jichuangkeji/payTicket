package com.weirdotech.payticket.utils.dialog;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;

import java.util.HashMap;

/**
 * Created by Bingo on 17/6/1.
 * 显示等待进度的对话框
 */
public class WaitDialogUtils {
    private static HashMap<String, Dialog> sDialogMap = new HashMap<>();

    public static void show(String which, Context context) {
        hide(which);

        if(sDialogMap.containsKey(which)) {
            sDialogMap.get(which).show();
        } else {
            ProgressDialog dialog = new ProgressDialog(context);
            dialog.setCancelable(false);
            sDialogMap.put(which, dialog);
            dialog.show();
        }
    }

    public static void hide(String which) {

        if(sDialogMap.containsKey(which) && sDialogMap.get(which).isShowing()) {
            sDialogMap.get(which).dismiss();
            sDialogMap.remove(which);
        }
    }

}
