/*
 * 创建日期 2004-10-10
 *
 * 更改所生成文件模板为
 * 窗口 > 首选项 > Java > 代码生成 > 代码和注释
 */
package com.speedy.exam.model.key;

import java.util.*;
/**
 * @author Administrator
 *
 * 更改所生成类型注释的模板为
 * 窗口 > 首选项 > Java > 代码生成 > 代码和注释
 */
public class KeyRegCenter 
{
	private static KeyRegCenter keyRegCenter = null;
	private Hashtable keys = new Hashtable();
	
	public static synchronized KeyRegCenter getInstance()
	{
		if(keyRegCenter == null)
			keyRegCenter = new KeyRegCenter();
		return keyRegCenter;
	}
	public static void release()
	{
		keyRegCenter = null;
	}
	public void putKey(String tabName, KeyModel key)
	{
		keys.put(tabName, key);
	}
	public synchronized KeyModel getKey(String tabName)
	{
		KeyModel keyModel = (KeyModel)keys.get(tabName);
		if(keyModel == null)
		{
			keyModel = new KeyModel();
			keys.put(tabName, keyModel);
		}
		return keyModel;
	}
}
