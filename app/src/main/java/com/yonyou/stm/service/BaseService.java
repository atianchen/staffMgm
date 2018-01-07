package com.yonyou.stm.service;


import com.yonyou.stm.domain.BaseEntity;

public interface BaseService<T extends BaseEntity> {

	void save(T entity);
	
	T load(String id);
	
	void remove(T entity);
	
	void release();
	
}
