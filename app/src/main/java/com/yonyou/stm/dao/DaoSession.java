package com.yonyou.stm.dao;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.yonyou.stm.domain.Staff;

import com.yonyou.stm.dao.StaffDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig staffDaoConfig;

    private final StaffDao staffDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        staffDaoConfig = daoConfigMap.get(StaffDao.class).clone();
        staffDaoConfig.initIdentityScope(type);

        staffDao = new StaffDao(staffDaoConfig, this);

        registerDao(Staff.class, staffDao);
    }
    
    public void clear() {
        staffDaoConfig.getIdentityScope().clear();
    }

    public StaffDao getStaffDao() {
        return staffDao;
    }

}
