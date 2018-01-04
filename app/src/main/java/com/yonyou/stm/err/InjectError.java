package com.yonyou.stm.err;

/**
 * Created by atian on 2017/11/29.
 */

public class InjectError extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public InjectError(String message)
    {
        super(message);
    }

    public InjectError(Exception e)
    {
        super(e.getMessage());
    }


}
