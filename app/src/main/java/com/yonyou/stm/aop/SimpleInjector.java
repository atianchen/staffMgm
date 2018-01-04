package com.yonyou.stm.aop;

import android.app.Service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

/**
 * Created by atian on 2017/11/29.
 */

public class SimpleInjector implements Injector {

    @Override
    public void inject(Object target) {
        Collection<Field> fields = getFields(target.getClass());
        for (Field field : fields)
        {
            if (field.getAnnotation(Inject.class)!=null)
            {
                try {
                    field.set(target, ServiceDepot.getServiceImpl(field.getType()));
                }
                catch (Exception e)
                {
                    e.printStackTrace();;
                }
            }
        }
    }


    private Collection<Field> getFields(Class<?> entityClass)
    {
        Collection<Field> fieldList = new ArrayList<Field>();
        Class<?> tempClass = entityClass;
        while (tempClass != null) {
            fieldList.addAll(Arrays.asList(tempClass.getDeclaredFields()));
            tempClass = tempClass.getSuperclass();
        }
        return fieldList;
    }
}
