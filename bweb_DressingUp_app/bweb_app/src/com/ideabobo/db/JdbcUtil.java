package com.ideabobo.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcUtil {
	public static Connection conn = null;
	static {
		try {
			Class.forName(ConfigUtil.getDriver());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static Connection getConnection() {
		if(conn != null){
			return conn;
		}else{
			String url = ConfigUtil.getUrl();
			String user = ConfigUtil.getUser();
			String pwd = ConfigUtil.getPwd();
			try {
				conn = DriverManager.getConnection(url, user, pwd);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return conn;
		}
		
	}

	public static void release(Object o) {
		if (o == null) {
			return;
		}
		if (o instanceof ResultSet) {
			try {
				((ResultSet) o).close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if (o instanceof Statement) {
			try {
				((Statement) o).close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if (o instanceof Connection) {
			Connection c = (Connection) o;
			try {
				if (!c.isClosed()) {
					c.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	public static void release(ResultSet rs, Statement stmt, Connection conn) {
		release(rs);
		release(stmt);
		release(conn);
	}
}
