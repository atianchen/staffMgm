package com.yonyou.stm.service;

import android.os.Environment;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class LogService {

	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public static String eToString(Throwable t) {
		if(t == null)
			return null;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try{
			t.printStackTrace(new PrintStream(baos));
		}finally{
			try {
				baos.close();
			} catch (IOException e) {
				LogService.log(e);
				e.printStackTrace();
			}
		}
		return baos.toString();
	}

	public static String log(Throwable exception)
	{
		return log(eToString(exception));
	}

	public static String log(String logMessage) {
		logMessage = sdf.format(Calendar.getInstance().getTime())+"---"+logMessage;
		File f = createFile();
		FileOutputStream out = null;
		try {
			byte[] buff = new byte[] {};
			logMessage = logMessage + "\n";
			buff = logMessage.getBytes();
			out = new FileOutputStream(f, true);
			out.write(buff);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return f.getPath();
	}

	public static File createFile() {
		String state = Environment.getExternalStorageState();
		if (state.equals(Environment.MEDIA_MOUNTED)) {
			String path = Environment.getExternalStorageDirectory()+"/a/log.txt";;
			File f = new File(path);
			if (!f.getParentFile().exists()) {
				f.getParentFile().mkdirs();
			}
			if (!f.exists()) {
				try {
					f.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return f;
		}
		return null;
	}
}
