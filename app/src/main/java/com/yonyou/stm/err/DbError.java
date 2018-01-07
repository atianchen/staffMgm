package com.yonyou.stm.err;

public class DbError extends InjectError{
	
	private static final long serialVersionUID = 1L;
	
	public DbError(String message)
	{
		super(message);
	}
	
	public DbError(Exception e)
	{
		super(e.getMessage());
	}
}
