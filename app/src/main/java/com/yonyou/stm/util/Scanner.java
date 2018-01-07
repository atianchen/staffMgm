package com.yonyou.stm.util;

import com.yonyou.stm.service.LogService;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;

import dalvik.system.DexFile;
import dalvik.system.PathClassLoader;

/**
 * Created by lsq on 2018/1/6.
 */

public class Scanner {

    private static Field dexField;

    static {
        try {
            dexField = PathClassLoader.class.getDeclaredField("mDexs");
            dexField.setAccessible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Collection<Class<?>> scan(String packageName) {
        Collection<Class<?>> rs = new ArrayList<Class<?>>();
        try {
            PathClassLoader classLoader = (PathClassLoader) Thread.currentThread().getContextClassLoader();

            DexFile[] dexs = (DexFile[]) dexField.get(classLoader);
            for (DexFile dex : dexs) {
                Enumeration<String> entries = dex.entries();
                while (entries.hasMoreElements()) {
                    String entry = entries.nextElement();
                    Class<?> entryClass = dex.loadClass(entry, classLoader);
                    LogService.log("PACKEGE:"+entryClass.getPackage().getName());
                    if (entryClass != null && entryClass.getPackage().getName().contains(packageName)) {
                        rs.add(entryClass);
                    }
                }
            }
            return rs;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
