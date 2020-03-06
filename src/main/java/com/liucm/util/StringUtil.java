package com.liucm.util;
/**
 * 字符串处理工具
 * @author 小明明
 * @version 1.0
 */
public class StringUtil {
	/**
	 * 将字符串首字母转换为大写
	 * @param str 需要将首字母转换为大写的字符串
	 * @return 转换后的字符串
	 */
	public String firstCharToUpperCase(String str) {
		char firstChar = str.charAt(0);
		if (firstChar >= 'a' && firstChar <= 'z') {
			char[] arr = str.toCharArray();
			arr[0] -= ('a' - 'A');
			return new String(arr);
		}
		return str;
	}
	/**
	 * 将字符串首字母转换为小写
	 * @param str 需要将首字母转换为小写的字符串
	 * @return 转换后的字符串
	 */
	public String firstCharToLowerCase(String str) {
		char firstChar = str.charAt(0);
		if (firstChar >= 'A' && firstChar <= 'Z') {
			char[] arr = str.toCharArray();
			arr[0] += ('a' - 'A');
			return new String(arr);
		}
		return str;
	}
}
