package com.cjean.lucene.util;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcConntectUtil {
	
	static String DBDriver = "com.mysql.jdbc.Driver";
	
	static String DBUrl = "jdbc:mysql://localhost:3306/ownermysql";

//	static String DBUrl = "jdbc:mysql://localhost:3306/ownermysql?useUnicode=true&amp;characterEncoding=UTF-8";
	
	static String DBName = "root";

	static String DBPwd = "a123456";
	
	static Connection connection;
	
	static Statement statement;
	
	static ResultSet rs;

	public JdbcConntectUtil() {
		
	}
	
	public JdbcConntectUtil(String DBDriver, String DBUrl, String DBName, String DBPwd) {
		
		this.DBDriver = DBDriver;
		this.DBUrl = DBUrl;
		this.DBName = DBName;
		this.DBPwd = DBPwd;
	}

	@Override
	public String toString() {
		return "DBDriver==="+DBDriver+"==DBUrl==="+DBUrl+"==DBName==="+DBName+"==DBPwd==="+DBPwd;
	}
	
	static {
		try {
			// 注册驱动
			Class.forName(DBDriver);
			// 打开连接诶
			connection = DriverManager.getConnection(DBUrl,DBName,DBPwd);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ResultSet getSeacherData(String searchSql) throws Exception {
		statement = connection.createStatement();
		rs = statement.executeQuery(searchSql);
		return rs;
	}
	
	public void closeConnect() {
		try {
			rs.close();
			statement.close();
			connection.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	

}
