package com.yonyou.stm.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by lsq on 2018/1/9.
 */

public class TimeUtils {

    private static SimpleDateFormat sdf= new SimpleDateFormat("yyyy.MM.dd");

    /**
     * 字符串（yyyy.MMM.dd）转时间
     * @param str
     * @return
     */
    public static Long strToLong(String str){
        try {
            Date dt2 = sdf.parse(str);
            return dt2.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String longToStr(Long time){
        if(time!=null)
           return sdf.format(new Date(time));
        return null;
    }
}
