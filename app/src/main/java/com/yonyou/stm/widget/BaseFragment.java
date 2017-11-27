package com.yonyou.stm.widget;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import  android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;

import com.yonyou.stm.ctx.Constants;

/**
 * Created by atian on 2017/11/26.
 */

public abstract class BaseFragment extends Fragment {

    protected void checkPermission(String[] permissions,CheckPermissionCallback callback)
    {
        if (Build.VERSION.SDK_INT >=  Build.VERSION_CODES.M) {
            for (String permission : permissions)
            {
                int checkCallPhonePermission = ContextCompat.checkSelfPermission(this.getActivity(), permission);
                if(checkCallPhonePermission != PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(this.getActivity(),new String[]{permission}, Constants.REQUESTCODE_PERMISSION);
                    return;
                }
            }callback.callback(PackageManager.PERMISSION_GRANTED);
        } else {

            callback.callback(PackageManager.PERMISSION_GRANTED);
        }
    }
}
