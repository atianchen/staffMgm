package com.yonyou.stm.util;

import android.app.Activity;
import android.util.DisplayMetrics;

import com.yonyou.stm.action.PhotoViewActivity;

/**
 * Created by lsq on 2018/1/10.
 */

public class DeviceUtils {

    public static int getWindowWidth(Activity activity) {
        DisplayMetrics dm = activity.getResources().getDisplayMetrics();
        return dm.widthPixels;
    }

    public static int getWindowHeight(Activity activity) {
        DisplayMetrics dm = activity.getResources().getDisplayMetrics();
        return  dm.heightPixels;
    }
}
