package com.liucm.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间处理工具
 * 
 * @author 小明明
 * @version 1.0
 */
public class DateUtil {
	/**
	 * 获取格式化后的时间
	 * 
	 * @param formatType 时间格式化的类型，当为null时，默认值为yyyy-MM-dd HH:mm:ss
	 * @return 返回格式化后的时间
	 */
	public String getFormatDate(String formatType) {
		String definedFormatType = "yyyy-MM-dd HH:mm:ss";
		if (formatType != null) {
			definedFormatType = formatType;
		}
		return new SimpleDateFormat(definedFormatType).format(new Date());
	}
	
	/**
	 * 获取格式化后的时间
	 * 
	 * @param formatType 时间格式化的类型，当为null时，默认值为yyyy-MM-dd HH:mm:ss
	 * @param date需要格式化的时间
	 * @return 返回格式化后的时间
	 */
	public String getFormatDate(String formatType,Date date) {
		String definedFormatType = "yyyy-MM-dd HH:mm:ss";
		if (formatType != null) {
			definedFormatType = formatType;
		}
		return new SimpleDateFormat(definedFormatType).format(date);
	}
}
