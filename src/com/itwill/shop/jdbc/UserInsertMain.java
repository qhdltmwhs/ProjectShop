package com.itwill.shop.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class UserInsertMain {

	public static void main(String[] args) throws Exception {
		String driverClass = "oracle.jdbc.OracleDriver";
		String url = "jdbc:oracle:thin:@182.237.126.19:1521:xe";
		String user = "javabackend92";
		String password = "javabackend92";
		
		String insertSql =
				"insert into userinfo values('zzz','zzz','zzz','zzz')";
		
		Class.forName(driverClass);
		Connection con = DriverManager.getConnection(url, user, password);
		Statement st = con.createStatement();
		
		int rowCount = st.executeUpdate(insertSql);
		System.out.println(rowCount + "행 insert");
		
		st.close();
		con.close();

	}

}
