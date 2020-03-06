package com.liucm.util;

/**
 * 工具类工厂，可获取时间工具类，文件工具类，字符串工具类
 * @author 小明明
 * @version 1.0
 */
public class ToolFactory {
	/**
	 * 定义静态的ToolFactory的对象
	 */
	private static ToolFactory toolFactory= new ToolFactory();
	/**
	 * 构造方法私有
	 */
	private ToolFactory() {}
	
	/**
	 * 由于构造方法以私有化，必须通过该方法获取ToolFactory类型的对象
	 * @return ToolFactory类型的对象
	 */
	public static ToolFactory getToolInstance() {
		return toolFactory;
	}
	
	/**
	 * 获得一个时间工具的对象
	 * @return 时间工具类DateUtil的对象
	 */
	public DateUtil getDateUtilInstance() {
		return new DateUtil();
	}
	
	/**
	 * 获得一个文件工具的对象
	 * @return 文件工具类FileUtil的对象
	 */
	public FileUtil getFileUtilInstance() {
		return new FileUtil();
	}
	/**
	 * 获得一个字符串工具的对象
	 * @return 字符串工具类StringUtil的对象
	 */
	public StringUtil getStringUtilInstance() {
		return new StringUtil();
	}
	
	/**
	 * @param path config.properties路径
	 * @return ConfigProperties实例
	 */
	public ConfigProperties getConfigPropertiesInstance(String path) {
		return ConfigProperties.getInstance(path);
	}
}
