package com.yonyou.stm.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.yonyou.stm.domain.BaseEntity;
import com.yonyou.stm.service.LogService;
import com.yonyou.stm.service.ServiceFactory;

import java.sql.SQLException;
import java.util.Collection;

/**
 * Database helper class used to manage the creation and upgrading of your database. This class also usually provides
 * the DAOs used by the other classes.
 */
public class DatabaseHelper extends OrmLiteSqliteOpenHelper {


	private static final String DATABASE_NAME = "stm.db";

	private static final int DATABASE_VERSION = 1;

	public DatabaseHelper(Context context)
	{
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
		try {
			LogService.log("onCreate");
			Collection<Class<BaseEntity>> domainClasses = ServiceFactory.getDomainClasses().values();
			for (Class<BaseEntity> domainClass: domainClasses)
			{
				TableUtils.createTable(connectionSource, domainClass);
				LogService.log("createTable:"+domainClass.getName());
			}
	
		} catch (SQLException e) {
			LogService.log(e);
			Log.e(DatabaseHelper.class.getName(), "Can't create database", e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * This is called when your application is upgraded and it has a higher version number. This allows you to adjust
	 * the various data to match the new version number.
	 */
	@Override
	public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion,
						  int newVersion) {
		try {
			Log.i(DatabaseHelper.class.getName(), "onUpgrade");
			Collection<Class<BaseEntity>> domainClasses = ServiceFactory.getDomainClasses().values();
			for (Class<BaseEntity> domainClass: domainClasses)
			{
				TableUtils.dropTable(connectionSource, domainClass,true);
			}
			// after we drop the old databases, we create the new ones
			onCreate(database,connectionSource);
		} catch (SQLException e) {
			Log.e(DatabaseHelper.class.getName(), "Can't drop databases", e);
			throw new RuntimeException(e);
		}
	}


}
