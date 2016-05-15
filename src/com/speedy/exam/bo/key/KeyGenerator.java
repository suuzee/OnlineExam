package com.speedy.exam.bo.key;

import java.rmi.RemoteException;

import com.speedy.exam.exception.KeyException;

/**
 *定义主键生成EJB的远程接口，通过获取唯一序号和唯一编码的功能。
 */
public interface KeyGenerator
{
	public long nextKey(String tableName)throws KeyException, RemoteException;
	public long[] nextKey(String tableName, int count)throws KeyException, RemoteException;
	public String nextKey(String tableName, String prefix)throws KeyException, RemoteException;
	public String[] nextKey(String tableName, String prefix,int count)throws KeyException, RemoteException;
	public void clearKey()throws KeyException, RemoteException;
}
