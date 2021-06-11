package com.itwill.shop.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class UserDeleteMain {

	public static void main(String[] args) throws Exception {
		String driverClass = "oracle.jdbc.OracleDriver";
		String url = "jdbc:oracle:thin:@182.237.126.19:1521:xe";
		String user = "javabackend92";
		String password = "javabackend92";
		String deleteSql =
				"delete userinfo where userid = 'mone'";
		
		Class.forName(driverClass);
		Connection con = DriverManager.getConnection(url, user, password);
		Statement st = con.createStatement();
		int deleteRowCount = st.executeUpdate(deleteSql);
		System.out.println(deleteRowCount + "행 delete");
		st.close();
		con.close();

	}

}
