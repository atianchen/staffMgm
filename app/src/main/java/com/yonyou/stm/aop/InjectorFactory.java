package com.yonyou.stm.aop;

/**
 * Created by atian on 2017/11/29.
 */

public class InjectorFactory {

    private static Injector injector = new SimpleInjector();

    public static Injector getInjector()
    {
        return injector;
    }
}
