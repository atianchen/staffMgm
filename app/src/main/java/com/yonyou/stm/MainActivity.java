package com.yonyou.stm;

import android.app.ProgressDialog;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Toast;

import com.baidu.ocr.sdk.OCR;
import com.baidu.ocr.sdk.OnResultListener;
import com.baidu.ocr.sdk.exception.OCRError;
import com.baidu.ocr.sdk.model.AccessToken;
import com.yonyou.stm.action.HomeFragment;
import com.yonyou.stm.action.StaffListFragment;
import com.yonyou.stm.ctx.AppContext;
import com.yonyou.stm.ctx.Constants;
import com.yonyou.stm.util.DlgHelper;

public class MainActivity extends AppCompatActivity implements HomeFragment.OnFragmentInteractionListener {

    private FragmentManager fm;

    private ProgressDialog progressDialog;

    private boolean inited = false;

    private boolean hasGotToken = false;

    private Handler handler;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    changeFragment(HomeFragment.newInstance(),false);
                    return true;
                case R.id.navigation_dashboard:
                    changeFragment(StaffListFragment.newInstance(),false);
                    return true;
                case R.id.navigation_notifications:

                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                progressDialog.dismiss();
                if (msg.what==Constants.HANDLER_RESULT_OCRINIT_OK)
                {
                    Toast.makeText(MainActivity.this, "初始化成功", Toast.LENGTH_SHORT).show();
                }
                else if (msg.what==Constants.HANDLER_RESULT_OCRINIT_ERROR)
                {
                    Toast.makeText(MainActivity.this, "初始化失败", Toast.LENGTH_SHORT).show();
                }

            }
        };
        fm = getSupportFragmentManager();
        changeFragment(HomeFragment.newInstance(),false);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        if (!this.inited) {
            this.inited = true;
            progressDialog = DlgHelper.loadDlg(this, "OcrToken", "Loading..", null);
            OCR.getInstance().initAccessTokenWithAkSk(new OnResultListener<AccessToken>() {
                @Override
                public void onResult(AccessToken result) {
                    AppContext.setParam(Constants.PARAMKEY_BAIDUOCR_TOKEN, result.getAccessToken());
                    handler.sendEmptyMessage(Constants.HANDLER_RESULT_OCRINIT_OK);
                }

                @Override
                public void onError(OCRError error) {
                    handler.sendEmptyMessage(Constants.HANDLER_RESULT_OCRINIT_ERROR);
                }
            }, getApplicationContext(), Constants.BAIDU_APIKEY, Constants.BAIDU_SECRETKEY);
        }

        initAccessTokenWithAkSk();
    }

    private  void changeFragment(Fragment f, boolean init){
        FragmentTransaction ft = this.fm.beginTransaction();
        ft.replace(R.id.content, f);
        if(!init)
            ft.addToBackStack(null);
        ft.commit();
    }


    public void onFragmentInteraction(Uri uri) {

    }

    private void initAccessTokenWithAkSk() {
        OCR.getInstance().initAccessTokenWithAkSk(new OnResultListener<AccessToken>() {
            @Override
            public void onResult(AccessToken result) {
                String token = result.getAccessToken();
                hasGotToken = true;
            }

            @Override
            public void onError(OCRError error) {
                error.printStackTrace();
            }
        }, getApplicationContext(), Constants.BAIDU_APIKEY, Constants.BAIDU_SECRETKEY);
    }

}
