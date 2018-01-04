package com.yonyou.stm;

import android.app.Application;
import android.util.Log;

import com.baidu.ocr.sdk.OCR;
import com.baidu.ocr.sdk.OnResultListener;
import com.baidu.ocr.sdk.exception.OCRError;
import com.baidu.ocr.sdk.model.AccessToken;
import com.yonyou.stm.ctx.AppContext;
import com.yonyou.stm.ctx.Constants;

import static android.content.ContentValues.TAG;

/**
 * Created by atian on 2017/11/29.
 */

public class StmApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

    }

}
