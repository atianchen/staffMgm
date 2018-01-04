package com.yonyou.stm.util;

import android.app.ProgressDialog;
import android.content.Context;

/**
 * Created by atian on 2017/11/29.
 */

public class DlgHelper {

    public static ProgressDialog loadDlg(Context context, String name, String msg, Thread thread) {
        ProgressDialog dialog = new ProgressDialog(context);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setTitle(name);
        dialog.setMessage(msg);
        dialog.setIndeterminate(false);
        dialog.setCancelable(true);
        try {
            dialog.show();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        if (thread != null) {
            thread.start();
        }
        return dialog;
    }
}
