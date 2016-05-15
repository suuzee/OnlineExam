package com.speedy.base.util;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.StringTokenizer;

public class StringUtil {
	public static String replace(String str, String src, String tar) {
		if (str == null || str.trim().length() <= 0)
			return str;
		StringBuffer ret = new StringBuffer();
		int len = src.length();
		int pos = str.indexOf(src);
		while (pos >= 0) {
			ret.append(str.substring(0, pos));
			ret.append(tar);
			str = str.substring(pos + len);
			pos = str.indexOf(src);
		}
		ret.append(str);
		return ret.toString();
	}
	public static ArrayList StringTokenizer(String source, String diff) {
		ArrayList ret = new ArrayList();
		if(source==null)
			return ret;
		StringTokenizer tokens = new StringTokenizer(source, diff);
		while (tokens.hasMoreTokens())
			ret.add(tokens.nextToken());
		return ret;
	}

	public static String numberFormat(String number, int digit) {
		if (number == null || number.trim().length() <= 0)
			return "";
		number = replace(number, ",", "");
		NumberFormat nf = NumberFormat.getInstance();
		nf.setMaximumFractionDigits(digit);
		nf.setMinimumFractionDigits(digit);
		try {
			String ret = nf.format(Double.parseDouble(number));
			int pos = ret.indexOf(".");

			String dot = "";
			if (pos > 0) {
				dot = ret.substring(pos + 1);
				int size = dot.length();
				String digits = "123456789";
				boolean needDigit = false;
				for (int i = 0; i < size; i++) {
					if (digits.indexOf(dot.charAt(i)) >= 0)
						needDigit = true;
				}
				if (!needDigit)
					ret = ret.substring(0, pos);
			}
			return ret;
		} catch (Exception e) {
			return number;
		}
	}

	public static int numberDigitSize(String number) {
		if (number == null || number.trim().length() <= 0)
			return 1;
		number = replace(number, ",", "");
		try {
			String ret = number;
			int pos = ret.indexOf(".");

			String dot = "";
			if (pos > 0) {
				NumberFormat nf = NumberFormat.getInstance();
				nf.setMinimumFractionDigits(20);
				ret = nf.format(Double.parseDouble(ret));
				ret = ret.substring(pos + 1);

				int size = ret.length();
				String digits = "123456789";
				boolean needDigit = false;
				for (int i = size - 1; i >= 0; i--) {
					if (digits.indexOf(ret.charAt(i)) >= 0)
						break;
					else
						ret = ret.substring(0, ret.length() - 1);
				}

				return ret.length();
			}
			return 1;
		} catch (Exception e) {
			return 1;
		}
	}

	public static String numberFormat(String number) {
		return numberFormat(number, 2);
	}

	public static java.sql.Date parseDate(String date) {
		try {
			if (date == null || date.trim().length() <= 0)
				return null;
			int p = date.indexOf(' ');
			if (p > 0)
				date = date.substring(0, p);
			return java.sql.Date.valueOf(date);
		} catch (Exception e) {
			return null;
		}
	}

	public static double parseDouble(String s) {
		try {
			if (s == null)
				return 0;
			s = replace(s, ",", "");
			return Double.parseDouble(s);
		} catch (Exception e) {
			return 0;
		}
	}

	public static int parseInteger(String s) {
		try {
			if (s == null)
				return 0;
			s = replace(s, ",", "");
			return (int) Double.parseDouble(s);
		} catch (Exception e) {
			return 0;
		}
	}
	public static long parseLong(String s) {
		try {
			if (s == null)
				return 0;
			s = replace(s, ",", "");
			return  Long.parseLong(s);
		} catch (Exception e) {
			return 0;
		}
	}
	public static String intFormat(String s) {
		try {
			if (s == null)
				return "";
			s = replace(s, ",", "");
			double ret = Math.ceil(parseDouble(s));

			NumberFormat nf = NumberFormat.getInstance();
			nf.setMaximumFractionDigits(0);
			nf.setMinimumFractionDigits(0);
			return nf.format((int) ret);
		} catch (Exception e) {
			return "";
		}
	}
	public static Timestamp parseTimestamp(String s)
	{
		try
		{
			if(s!=null)
			{
				int pos = s.indexOf(".");
				if(pos>0)
					return Timestamp.valueOf(s);
				else return Timestamp.valueOf(s+".0"); 
			} 
			return null;
		}
		catch(Exception e)
		{
			try
			{
				Date d = parseDate(s);
				if(d!=null)
				{
					Calendar c = Calendar.getInstance();
					c.setTime(d);
					Timestamp ret = new Timestamp(c.getTime().getTime());
					return ret;
				}
			}
			catch(Exception ee)
			{
			}
			return null;
		}
	}	
	
}
