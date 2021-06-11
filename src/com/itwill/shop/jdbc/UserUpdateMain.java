package com.itwill.shop.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class UserUpdateMain {

	public static void main(String[] args) throws Exception {
		String driverClass = "oracle.jdbc.OracleDriver";
		String url = "jdbc:oracle:thin:@182.237.126.19:1521:xe";
		String user = "javabackend92";
		String password = "javabackend92";
		String updateSql =
				"update userinfo set userid = 'mone', password = '1318', name = '모네', email = 'mone@mone.com' where userid = 'zzz'";
		
		Class.forName(driverClass);
		Connection con = DriverManager.getConnection(url, user, password);
		Statement st = con.createStatement();
		int updateRowCount = st.executeUpdate(updateSql);
		System.out.println(updateRowCount + "행 update");
		st.close();
		con.close();

	}

}
