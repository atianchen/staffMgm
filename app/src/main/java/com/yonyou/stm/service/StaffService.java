package com.yonyou.stm.service;

import com.yonyou.stm.StmApp;
import com.yonyou.stm.dao.StaffDao;
import com.yonyou.stm.domain.Staff;

import java.util.List;

/**
 * Created by lsq on 2018/1/9.
 */

public class StaffService implements BaseService<Staff>{

    private StaffDao staffDao;

    public StaffService(){
        staffDao = StmApp.getInstances().getDaoSession().getStaffDao();
    }

    @Override
    public void save(Staff entity) {
        if(entity.getId()!=null)
            staffDao.update(entity);
        else
            staffDao.insert(entity);
    }

    @Override
    public Staff load(Long id) {
        return staffDao.load(id);
    }

    @Override
    public void remove(Staff entity) {

    }

    @Override
    public void release() {

    }

    public List<Staff> loadAll() {
        return staffDao.loadAll();
    }
}
