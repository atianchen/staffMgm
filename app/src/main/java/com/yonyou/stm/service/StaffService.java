package com.yonyou.stm.service;

import com.yonyou.stm.StmApp;
import com.yonyou.stm.domain.Staff;
import com.yonyou.stm.domain.StaffDao;

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
        staffDao.insert(entity);
    }

    @Override
    public Staff load(long id) {
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
