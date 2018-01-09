package com.yonyou.stm.err;

import android.annotation.SuppressLint;
import android.content.Context;


import com.yonyou.stm.service.LogService;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.Thread.UncaughtExceptionHandler;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
public class ExceptionHandler implements UncaughtExceptionHandler{
	private UncaughtExceptionHandler mDefaultHandler;
	private static ExceptionHandler INSTANCE = new ExceptionHandler(); 
	private Context activity;
	
	public static ExceptionHandler getInstance() {  
		        return INSTANCE;  
	  }  

	public void init(Context context) {  
	       	this.activity =  activity;
		     mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();  
		    Thread.setDefaultUncaughtExceptionHandler(this);  
	}  

	
	@SuppressLint("SimpleDateFormat")
	@Override
	public void uncaughtException(Thread arg0, Throwable ex) {
		ex.printStackTrace();
		 if (ex != null) {  
	        new Thread() {  
	            @Override  
	            public void run() {    
	               // Toast.makeText(activity, "很抱歉,程序出现异常,即将退出.", Toast.LENGTH_LONG).show();  
	            }  
	        }.start();  
	        try {
	            Thread.sleep(3000);  
	        } catch (InterruptedException e) {  
	        	e.printStackTrace();
	        }
	        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			LogService.log("---------"+formatter.format(Calendar.getInstance().getTime())+"---------\n"+eToString(ex));
	        //退出程序  
	        android.os.Process.killProcess(android.os.Process.myPid());  
	        System.exit(1); 
		 }
	}
	
	public static String eToString(Throwable e) {
		try {
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			return "\r\n" + sw.toString() + "\r\n";
		} catch (Exception e2) {
			return "bad getErrorInfoFromException";
		}
	}

}
