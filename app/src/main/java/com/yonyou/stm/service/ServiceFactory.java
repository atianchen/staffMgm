package com.yonyou.stm.service;

import android.content.Context;

import com.j256.ormlite.table.DatabaseTable;
import com.yonyou.stm.domain.BaseEntity;
import com.yonyou.stm.domain.Staff;
import com.yonyou.stm.err.InjectError;
import com.yonyou.stm.util.PackageScanner;
import com.yonyou.stm.util.Scanner;
import com.yonyou.stm.widget.BaseActivity;
import com.yonyou.stm.widget.BaseFragment;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Service工厂
 * @author inc.chenzhi
 *
 */
public class ServiceFactory {
	
	private static final String domainPackageName = "com.yonyou.stm.domain";
	private static final String servicePackageName = "com.yonyou.stm.service";
	
	private  static Map<String,Class<BaseEntity>> domainClasses;
	
	public static Map<String, Class<BaseEntity>> getDomainClasses() {
		return domainClasses;
	}

	public static void setDomainClasses(Map<String, Class<BaseEntity>> domainClasses) {
		ServiceFactory.domainClasses = domainClasses;
	}

	public static BaseService<?> getServiceBean(Object context,Class<?> serviceClass)
	{
		try {
			Class<?>[] args = new Class<?>[1];	  
			args[0]=BaseActivity.class;
			Constructor<?> cons = serviceClass.getConstructor(args);
			if (context instanceof BaseFragment)
				return (BaseService<?>)cons.newInstance(((BaseFragment)context).getActivity());
			else
				return (BaseService<?>)cons.newInstance(context);
		} catch (Exception e) {
			e.printStackTrace();
			throw new InjectError(e);
		}
	}
	
	/**
	 * 初始化ServiceFactory
	 * @param context
	 * @throws Exception
	 */
	public static void initFactory(Context context) throws Exception
	{
		domainClasses =  new HashMap<String,Class<BaseEntity>>();
		//Collection<Class<?>> rs =  PackageScanner.scanClasses(context, domainPackageName);
		//Collection<Class<?>> rs =  PackageScanner.getClasses(domainPackageName);
		//Collection<Class<?>> rs = Scanner.scan(domainPackageName);
		Collection<Class<?>> rs = new ArrayList<Class<?>>();
		rs.add(Staff.class);
		for (Class<?> entityClass : rs)
		{
			if (entityClass.getAnnotation(DatabaseTable.class)!=null)
			{
				domainClasses.put(entityClass.getName(), (Class<BaseEntity>)entityClass);
			}
		}
	}


	/*
	 * 获取service类名
	 */
	private static  String getServiceClassName(String entityClassName)
	{
		return  servicePackageName+entityClassName.substring( entityClassName.lastIndexOf("."))+"Service";
	}


}
