package com.ideabobo.db;


// 数据库连接信息
public class ConfigUtil {

	public static String getDriver() {
		return "com.mysql.jdbc.Driver";
	}

	public static String getUrl() {
		return "jdbc:mysql://localhost:3306/adapeituijian?useUnicode=true&characterEncoding=utf-8&autoReconnect=true";
	}

	public static String getUser() {
		return "root";
	}

	public static String getPwd() {
		return "root";
	}
	
	// 表名前缀
	public static String px = "a_";
}
