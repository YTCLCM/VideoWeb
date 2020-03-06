package com.liucm.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 获得config.properties文件的键所对应的值
 * 
 * @author 13576
 *
 */
public class ConfigProperties {

	private String path;

	private ConfigProperties(String path) {
		this.path = path;
	}

	/**
	 * ConfigProperties的构造器已私有化，通过此方法获得一个ConfigProperties实例
	 * 
	 * @param path config.properties文件所在的路径
	 * @return 返回一个ConfigProperties实例
	 */
	public static ConfigProperties getInstance(String path) {
		return new ConfigProperties(path);
	}

	/**
	 * 获得config.properties文件中配置信息
	 * 
	 * @param keyname 键名
	 * @return 键名对应的值名
	 */
	public String getConfigPropertiesValue(String keyname) {
		Properties properties = new Properties();
		InputStream inputStream = ConfigProperties.class.getResourceAsStream(this.path);
		try {
			if (inputStream != null) {
				properties.load(inputStream);
			}else {
				System.out.println("未找到文件！");
				return null;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return properties.getProperty(keyname);
	}
}
