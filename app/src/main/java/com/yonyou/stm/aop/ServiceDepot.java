package com.yonyou.stm.aop;



import java.util.HashMap;
import java.util.Map;

/**
 * Created by atian on 2017/11/29.
 */

public class ServiceDepot {

    private static Map<Class<?>,Object> implMap = new HashMap<>();



    public static Object getServiceImpl(Class<?> interfaceCls)
    {
        if (implMap.containsKey(interfaceCls))
            return implMap.get(interfaceCls);
        else
        {
            String packageName = interfaceCls.getPackage().getName();
            String clsName = interfaceCls.getSimpleName();
            Class<?> implClass = null;
            try {
                implClass = Class.forName(packageName + "." + clsName + "Impl");
            }
            catch (Exception e)
            {
                try {
                    implClass = Class.forName(packageName + ".impl." + clsName + "Impl");
                }
                catch (Exception ex)
                {
                    ex.printStackTrace();
                }
            }
            try {
               /* if (implClass==null)
                    throw new InjectError("Not found Impl for "+interfaceCls.getName());*/
                Object serviceImpl = implClass.newInstance();
                implMap.put(interfaceCls,serviceImpl);
                return serviceImpl;
            }
            catch (Exception e)
            {
                e.printStackTrace();
               // throw new InjectError(e);
            }
            return null;
        }
    }
}
