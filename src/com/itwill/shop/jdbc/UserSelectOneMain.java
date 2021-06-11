package com.itwill.shop.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class UserSelectOneMain {

	public static void main(String[] args) throws Exception {
		String driverClass = "oracle.jdbc.OracleDriver";
		String url = "jdbc:oracle:thin:@182.237.126.19:1521:xe";
		String user = "javabackend92";
		String password = "javabackend92";
		
		String selectSql = "select * from userinfo where userid = 'aaa'";
		
		/*
		 * 1. Driver Class loading
		 */
		Class.forName(driverClass);
		System.out.println("1. Driver Class loading");
		
		/*
		 * 2. Connection 객체생성(Socket생성, DBServer에연결)
		 */
		Connection con = DriverManager.getConnection(url, user, password);
		System.out.println("2. Connection생성: " + con);
		
		/*
		 * 3. Statement 객체생성(SQL 전송객체)[Stream객체]
		 */
		Statement stmt = con.createStatement();
		System.out.println("3. Statement객체생성: " + stmt);
		
		/*
		 * 4. SQL문 전송
		 * 5. ResultSet(결과집합) 얻기
		 */
		ResultSet rs = stmt.executeQuery(selectSql);
		System.out.println("4. SQL문 전송: " + selectSql);
		System.out.println("5. ResultSet(결과집합) 얻기: " + rs);
		
		while(rs.next()) {
			String userid = rs.getString("userid");
			String pass = rs.getString("password");
			String name = rs.getString("name");
			String email = rs.getString("email");
			System.out.println(userid + "\t" + pass + "\t" + name + "\t" + email);
		}
		
		/*
		 * 6. resource 해지
		 */
		rs.close();
		stmt.close();
		con.close();
		System.out.println("6. resource해지 close()");

	}

}
