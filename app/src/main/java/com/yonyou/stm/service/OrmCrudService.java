package com.yonyou.stm.service;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.AndroidDatabaseConnection;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;
import com.yonyou.stm.db.DatabaseHelper;
import com.yonyou.stm.domain.BaseEntity;
import com.yonyou.stm.err.DbError;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.sql.SQLException;
import java.util.List;

public abstract class OrmCrudService<T extends BaseEntity> implements
		BaseService<T> {

	protected DatabaseHelper dbHelper;

	protected Dao<T, String> dao;

	protected Class<T> entityClass;

	protected Context context;

	public OrmCrudService(Context context) {
		dbHelper = OpenHelperManager.getHelper(context,DatabaseHelper.class);
		LogService.log("getDatabaseName:"+dbHelper.getDatabaseName());
		//dbHelper = DatabaseHelper.getInstance(context);
		entityClass = (Class<T>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
		try {
			dao = dbHelper.getDao(entityClass);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.context = context;
	}

	public void save(T entity) {
		try {
			dao.createOrUpdate(entity);
		} catch (SQLException e) {
			throw new DbError(e);
		}
	}

	public T load(Integer id) {
		try {
			List<T> rs = dao.queryBuilder().where().eq("id", id).query();
			if (rs.size() > 0)
				return rs.get(0);
			else
				return null;
		} catch (SQLException e) {
			throw new DbError(e);
		}
	}

	public T load(String id) {
		try {
			List<T> rs = dao.queryBuilder().where().eq("id", id).query();
			if (rs.size() > 0)
				return rs.get(0);
			else
				return null;
		} catch (SQLException e) {

			throw new DbError(e);
		}
	}

	public void remove(T entity) {
		try {
			dao.delete(entity);
		} catch (SQLException e) {
			throw new DbError(e);
		}
	}

	public void release() {
		if (dbHelper != null) {
			OpenHelperManager.releaseHelper();
			dbHelper = null;
		}
	}
}
