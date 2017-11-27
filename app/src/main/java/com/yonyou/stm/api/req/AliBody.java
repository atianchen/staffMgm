package com.yonyou.stm.api.req;

/**
 * Created by atian on 2017/11/26.
 */

public abstract class AliBody {

    private int dataType = 50;

    private String dataValue;

    public int getDataType() {
        return dataType;
    }

    public void setDataType(int dataType) {
        this.dataType = dataType;
    }

    public String getDataValue() {
        return dataValue;
    }

    public void setDataValue(String dataValue) {
        this.dataValue = dataValue;
    }
}
