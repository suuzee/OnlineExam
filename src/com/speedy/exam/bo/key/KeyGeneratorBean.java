package com.speedy.exam.bo.key;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.speedy.exam.dao.key.KeyDAO;
import com.speedy.exam.exception.KeyException;
import com.speedy.exam.model.key.KeyModel;
import com.speedy.exam.model.key.KeyRegCenter;
import com.speedy.exam.util.KeyConstants;


/**
 *定义主键生成的本地实现，获取系统唯一的序号和编码。
 */
@Component
public class KeyGeneratorBean 
{
	@Autowired
	KeyDAO keyDAO;
	public long nextKey(String tableName)throws KeyException
	{
		return keyDAO.nextKey(tableName.toUpperCase());		
	}
	public long[] nextKey(String tableName, int count)throws KeyException
	{
		long keyNo = keyDAO.nextKey(tableName.toUpperCase(), count) - count + 1;
		long ret[] = new long[count];
		for(int i=0; i<count; i++)
			ret[i] = keyNo++;
		return ret;				
	}
	public String nextKey(String tableName, String prefix)throws KeyException
	{
		return nextKey(tableName, prefix, 1)[0];
	}
	public String[] nextKey(String tableName, String prefix, int count)throws KeyException
	{
		KeyModel keyModel = null;
		String curDate = getDatestamp();
		try
		{
			if(count < 1) 
				return null;
			if(prefix.length() != 2) 
				throw new KeyException("主键前缀需要2个字符");
			KeyRegCenter keyReg = KeyRegCenter.getInstance();
			long maxKey = 0;		
			synchronized(keyReg)
			{
				keyModel = keyReg.getKey(tableName.toUpperCase());
				if(!curDate.equals(keyModel.getCurDate()))
					keyModel.clearKey();
				if(keyModel.getCount() < count)
				{
					int ccount = count - keyModel.getCount();
					if(ccount < KeyConstants.ONETIME_KEY_COUNT)
						ccount = KeyConstants.ONETIME_KEY_COUNT;
					else ccount += KeyConstants.ONETIME_KEY_COUNT;
					long keyno = keyDAO.nextKey(tableName.toUpperCase(), curDate, ccount);
					keyModel.addKey(curDate, keyno, ccount);
				}
				maxKey = keyModel.getKey(count);
			}
			String ret[] = new String[count];
			long curKey = maxKey-count+1; 
			for(int i=0; i<count; i++)
			{
				StringBuffer buf = new StringBuffer();
				buf.append(prefix);
				buf.append(curDate);
				buf.append(toTwelveDigit(curKey++));
				ret[i] = buf.toString().toUpperCase(); 
			}			
			return ret;
		}
		catch(Exception e)
		{
			if(keyModel != null)
				keyModel.clearKey();
			throw new KeyException("生成主键出现错误", e);
		}
	}
	public void clearKey()throws KeyException
	{
		try
		{
			String lastDate = getLastDatestamp();
			keyDAO.clearKey(lastDate);
		}
		catch(Exception e)
		{
		//	rollback();
			throw new KeyException("清理主键出现错误", e);
		}
	}
	private String getDatestamp()
	{
		Calendar now = Calendar.getInstance();
		String year = toTwoDigit(now.get(Calendar.YEAR) % 100);
		String month = toTwoDigit((now.get(Calendar.MONTH) + 1));
		String day = toTwoDigit(now.get(Calendar.DAY_OF_MONTH));
		String hour =  toTwoDigit(now.get(Calendar.HOUR));
		String minute = toTwoDigit(now.get(Calendar.MINUTE));
		String second = toTwoDigit(now.get(Calendar.SECOND));

		String datestamp = year + month + day + hour + minute + second;
		return datestamp;
	}
	private String getLastDatestamp()
	{
		Calendar now = Calendar.getInstance();
		now.add(Calendar.DAY_OF_MONTH, -10);
		String year = toTwoDigit(now.get(Calendar.YEAR) % 100);
		String month = toTwoDigit((now.get(Calendar.MONTH) + 1));
		String day = toTwoDigit(now.get(Calendar.DAY_OF_MONTH));

		String datestamp = year + month + day;
		return datestamp;
	}
	private String toTwelveDigit(long value) throws KeyException
	{
		String v = Long.toString(value);
		if(v.length() > 12) 
			throw new KeyException("当日生成单据过多");
		if(value <= 0)
			throw new KeyException("编码不能小于等于0");
		int i = 6-v.length();
		StringBuffer buf = new StringBuffer("");
		while(i-- > 0)
		{
			buf.append(0);
		}
		return buf.append(v).toString();
	}
	private String toTwoDigit(int value) 
	{
		StringBuffer buffer = new StringBuffer("");

		if ( value >= 0 && value <= 9 )
			buffer.append("0");
		buffer.append(value);
		return buffer.toString();
	}	
}
