package com.speedy.exam.bo;


import java.sql.Date;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;

import com.speedy.base.common.JetException;
import com.speedy.base.common.JetLog;
import com.speedy.base.lock.Lock;
import com.speedy.base.lock.LockMrg;
import com.speedy.exam.bo.key.KeyGeneratorBean;
import com.speedy.exam.dao.MaxDao;
import com.speedy.exam.exception.KeyException;

/**
 *所有Stateless Session Bean的实现的基类，实现了事务回滚，
 *日志处理，获取系统时间，提供EJB缺省实现等。
 */
public class BaseBean
{
	@Autowired
	KeyGeneratorBean keyGeneratorBean;
	@Autowired
	MaxDao maxDao;
	
	protected void debug(String msg)
	{
		JetLog.debug(this, msg);
	}
	 	
	protected void error(String msg)
	{
		JetLog.error(this, msg);
	}
	protected void error(String msg, Throwable e)
	{
		JetLog.error(this, msg, e);
	}
	public Date getCurrentDate() throws JetException
	{
		Date date=new Date(System.currentTimeMillis());
		return date;
	}
//	public Timestamp getCurrentTime()
//	{
//		Timestamp timestamp=new Timestamp(System.currentTimeMillis());
//		return timestamp;
//	}
	public int[] getMonth(Date cur)
	{
		Calendar c = Calendar.getInstance();
		c.setTime(cur);
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH)+1;
		int day = c.get(Calendar.DATE);
		if(day>25)
			month++;
		if(month>12){
			year++;
			month=1;		
		}
		return new int[] { year, month };		
	}
	
	public Date[] getBeginEndDate(int year, int month) 
	{
		Date[] ret = new Date[2];
		Calendar c = Calendar.getInstance();
		c.clear();
		if (month == 1) {
			c.set(year-1, 11, 26);
		} else	c.set(year, month - 2, 26);
		ret[0] = new Date(c.getTime().getTime());
		c.set(year, month - 1, 25);
		ret[1] = new Date(c.getTime().getTime());
		return ret;
	}
	
	public int[] getLastMonth(int year, int month) {
		month--;
		if(month<1){
			year--;
			month = 12;
		}
		return new int[] { year, month };
	}
	
	protected void info(String msg)
	{
		JetLog.info(this, msg);
	}
	

	public Lock lock(Object key)throws JetException
	{
		try
		{
			if(key==null)
				key = "";
			return LockMrg.getInstance().lock(key);
		}
		catch(Exception e)
		{
			throw new JetException("获取独占锁出现错误", e);
		}
	}
	
	public String generatorKey(String tableName,String prefix) throws KeyException
	{
		return keyGeneratorBean.nextKey(tableName, prefix);
	}
	public void releaseLock(Object key)
	{
		if(key==null)
			key = "";
		LockMrg.getInstance().release(key);
	}
	public Lock sharelock(Object key)throws JetException
	{
		try
		{
			return LockMrg.getInstance().shareLock(key);
		}
		catch(Exception e)
		{
			throw new JetException("获取共享锁出现错误", e);
		}
	}
	public void releaseShareLock(Object key)
	{
		LockMrg.getInstance().releaseShareLock(key);
	}
	public String getMax(String maxCode){
		int max = maxDao.getMax(maxCode);
		String result = "";
		if(max<10){
			result = "000000"+max;
		}else if(max>=10&&max<100){
			result = "00000"+max;
		}else if(max>=100&&max<1000){
			result = "0000"+max;
		}else if(max>=1000&&max<10000){
			result = "000"+max;
		}else if(max>=10000&&max<100000){
			result = "00"+max;
		}else{
			result = "0"+max;
		}
		maxDao.updateMax(maxCode);
		return result;
	}
}
