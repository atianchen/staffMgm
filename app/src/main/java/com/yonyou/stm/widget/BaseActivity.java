package com.yonyou.stm.widget;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.yonyou.stm.aop.InjectorFactory;

/**
 * Created by atian on 2017/11/26.
 */

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        InjectorFactory.getInjector().inject(this);
    }
}
