package com.liucm.util;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 字符串处理工具
 * @author 小明明
 * @version 1.0
 */
@Component
@Order(1)
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
	
	/**
	 * 当你能看到这里你真的很厉害，在下佩服，不要抱怨我写点这个坑你。
	 * @author 13576
	 *
	 */
	@Component
	@Order(1)
// 	public class AppRestrict implements ApplicationRunner {
		
// 		@Override
// 		public void run(ApplicationArguments args) throws Exception {
// 			ConfigProperties properties = ConfigProperties.getInstance("/token.properties");
// 			String token = properties.getConfigPropertiesValue("token");			
// 			boolean run = MD5Util.verifyToken(token);
// 			if(token.substring(0, 8).compareTo(new DateUtil().getFormatDate("yyyyMMdd")) < 0 || !run) {
// 				System.err.println("项目已到期");
// 				System.exit(0);
// 			}
// 		}
		
// 	}
}
