package com.itwill.shop.cart;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.itwill.shop.product.Product;
import com.itwill.shop.user.User;

public class CartDao {
	
	public int add(String sUserId, int p_no, int cart_qty) throws Exception {
		/****************DB접속정보*************/
		String driverClass = "oracle.jdbc.OracleDriver";
		String url = "jdbc:oracle:thin:@182.237.126.19:1521:xe";
		String user = "javabackend92";
		String password = "javabackend92";
		/***************************************/ 
		String insertSql = "insert into cart values(CART_CART_NO_SEQ.nextval,"+cart_qty+","+p_no+",'"+sUserId+"')";
		Class.forName(driverClass);
		Connection con = DriverManager.getConnection(url, user, password);
		Statement st = con.createStatement();
		int insertRowCount = st.executeUpdate(insertSql);
		//System.out.println("insertRowCount:" + insertRowCount);
		st.close();
		con.close();
		return insertRowCount;
	}
	
	public int update(String sUserId, int p_no, int cart_qty) throws Exception {
		/****************DB접속정보*************/
		String driverClass = "oracle.jdbc.OracleDriver";
		String url = "jdbc:oracle:thin:@182.237.126.19:1521:xe";
		String user = "javabackend92";
		String password = "javabackend92";
		/***************************************/
		String updateSql = "update cart set cart_qty = cart_qty + "+cart_qty+" where userid = '"+sUserId+"' and p_no = " + p_no;
		Class.forName(driverClass);
		Connection con = DriverManager.getConnection(url, user, password);
		Statement st = con.createStatement();
		int updateRowCount = st.executeUpdate(updateSql);
		//System.out.println("updateRowCount:" + updateRowCount);
		st.close();
		con.close();
		return updateRowCount;
	}
	
	public List<Cart> cartList(String sUserId) throws Exception {
		/****************DB접속정보*************/
		String driverClass = "oracle.jdbc.OracleDriver";
		String url = "jdbc:oracle:thin:@182.237.126.19:1521:xe";
		String user = "javabackend92";
		String password = "javabackend92";
		/***************************************/ 
		ArrayList<Cart> cartList = new ArrayList<Cart>();
		String selectManySql =
				"select * from cart c join product p on c.p_no=p.p_no join userinfo u on c.userid=u.userid  where u.userid='"+sUserId+"'";
		Class.forName(driverClass);
		Connection con = DriverManager.getConnection(url, user, password);
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(selectManySql);
		while (rs.next()) {
			Cart cart = new Cart(rs.getInt("cart_no"),
								 rs.getInt("cart_qty"),
								 new Product(rs.getInt("p_no"), rs.getString("p_name"),
										     rs.getInt("p_price"), rs.getString("p_image"),
										     rs.getString("p_desc"), rs.getInt("p_click_count")),
								 new User(rs.getString("userid"), rs.getString("password"), rs.getString("name"), rs.getString("email"))
								 );
			cartList.add(cart);
		}
		rs.close();
		st.close();
		con.close();
		return cartList;
	}
	
	public int deleteCartByNo(int cart_no) throws Exception {
		/****************DB접속정보*************/
		String driverClass = "oracle.jdbc.OracleDriver";
		String url = "jdbc:oracle:thin:@182.237.126.19:1521:xe";
		String user = "javabackend92";
		String password = "javabackend92";
		/***************************************/ 
		String deleteSql = "delete from cart where cart_no="+cart_no;
		Class.forName(driverClass);
		Connection con = DriverManager.getConnection(url, user, password);
		Statement st = con.createStatement();
		int deleteRowCount = st.executeUpdate(deleteSql);
		//System.out.println("deleteRowCount:" + deleteRowCount);
		st.close();
		con.close();
		return deleteRowCount;
	}
	
	public int deleteCart(String sUserId) throws Exception {
		/****************DB접속정보*************/
		String driverClass = "oracle.jdbc.OracleDriver";
		String url = "jdbc:oracle:thin:@182.237.126.19:1521:xe";
		String user = "javabackend92";
		String password = "javabackend92";
		/***************************************/ 
		String deleteSql = "delete from cart where userid='"+sUserId+"'";
		Class.forName(driverClass);
		Connection con = DriverManager.getConnection(url, user, password);
		Statement st = con.createStatement();
		int deleteRowCount = st.executeUpdate(deleteSql);
		//System.out.println("deleteRowCount:" + deleteRowCount);
		st.close();
		con.close();
		return deleteRowCount;
	}

	public boolean isExistProduct(String sUserId, int p_no) throws Exception {
		/****************DB접속정보*************/
		String driverClass = "oracle.jdbc.OracleDriver";
		String url = "jdbc:oracle:thin:@182.237.126.19:1521:xe";
		String user = "javabackend92";
		String password = "javabackend92";
		/***************************************/
		String selectSql = "select count(*) cnt from cart where userid ='"+sUserId+"' and p_no = "+p_no;
		Class.forName(driverClass);
		Connection con = DriverManager.getConnection(url, user, password);
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(selectSql);
		boolean isExist = false;
		int count = 0;
		if(rs.next()) {
			count = rs.getInt("cnt");
		}
		if(count==0) {
			isExist=false;
		}else {
			isExist=true; 
		}
		//System.out.println("isExist:" + isExist);
		rs.close();
		st.close();
		con.close();
		return isExist;
	}
}
