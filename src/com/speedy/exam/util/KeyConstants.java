package com.speedy.exam.util;

/**
 *定义主键生成模块需要的常量
 */

public interface KeyConstants 
{
	public static final String KEY_DAO_CLASS   = "com.speedy.base.key.dao.KeyDAOImpl";
		
	public static final String KEY_TABLE_NAME  = "tp_key_number";
	public static final String CODE_TABLE_NAME = "tp_key_string";	
	public static final int ONETIME_KEY_COUNT  = 1000;
}
