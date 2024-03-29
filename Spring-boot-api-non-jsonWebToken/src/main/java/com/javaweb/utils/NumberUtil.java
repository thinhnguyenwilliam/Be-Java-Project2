package com.javaweb.utils;

public class NumberUtil 
{
	public static boolean checkNumber(String data)
	{
		try {
			Long number=Long.parseLong(data);
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
		
		return true;
	}
}
