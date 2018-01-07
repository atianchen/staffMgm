package com.yonyou.stm.service;

import android.content.Context;

import com.yonyou.stm.domain.Staff;
import com.yonyou.stm.err.DbError;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by lsq on 2018/1/6.
 */

public class StaffService extends OrmCrudService<Staff>{

    public StaffService(Context context) {
        super(context);
    }

    public List<Staff> loadAll() {
        try {
            return this.dao.queryForAll();
        } catch (SQLException e) {
            throw new DbError(e);
        }
    }

}
