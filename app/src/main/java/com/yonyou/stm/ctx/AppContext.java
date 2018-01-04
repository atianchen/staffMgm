package com.yonyou.stm.ctx;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by atian on 2017/11/29.
 */

public class AppContext {

    private static Map<String,Object> params = new HashMap<String,Object>();

    public static void setParam(String key,Object val)
    {
        params.put(key,val);
    }

    public static Object getParam(String key)
    {
        return params.get(key);
    }
}
