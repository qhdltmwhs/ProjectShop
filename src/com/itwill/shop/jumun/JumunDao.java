package com.itwill.shop.jumun;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.itwill.shop.product.Product;
import com.itwill.shop.user.User;


public class JumunDao {
	
	/*
	 * insert by Jumun + JumunDetail
	 */
	public int insert(Jumun jumun) throws Exception {
		/****************DB접속정보*************/
		String driverClass = "oracle.jdbc.OracleDriver";
		String url = "jdbc:oracle:thin:@182.237.126.19:1521:xe";
		String user = "javabackend92";
		String password = "javabackend92";
		/***************************************/ 
		/*
		insert into jumun(j_no, j_desc, j_date, j_price, userid) values (jumun_j_no_seq.nextval, '비글외1종', sysdate, 26000000, 'guard1');
		insert into jumun_detail(jd_no, jd_qty, j_no, p_no) values(JUMUN_DETAIL_JD_NO_SEQ.nextval, 2, jumun_j_no_seq.currval, 1);
		 */
		String j_desc = jumun.getJ_desc();
		int j_price =  jumun.getJ_price();
		String userId = jumun.getUser().getUserId(); 
		List<JumunDetail> jumunDetailList = jumun.getJumunDetailList();
		
		String insertSql1 =
				"insert into jumun(j_no, j_desc, j_date, j_price, userid) values (jumun_j_no_seq.nextval, '"+j_desc+"', sysdate, "+j_price+", '"+userId+"')";

		Class.forName(driverClass);
		Connection con = DriverManager.getConnection(url, user, password);
		Statement st1 = con.createStatement();
		Statement st2 = con.createStatement();
		int insertRowCount1 = st1.executeUpdate(insertSql1);
		for (JumunDetail jumunDetail : jumunDetailList) {
			int jd_qty = jumunDetail.getJd_qty();
			int p_no = jumunDetail.getProduct().getP_no();
			String insertSql2 =
					"insert into jumun_detail(jd_no, jd_qty, j_no, p_no) values(JUMUN_DETAIL_JD_NO_SEQ.nextval, "+jd_qty+", jumun_j_no_seq.currval, "+p_no+")";
			st2.executeUpdate(insertSql2);
		}
		return insertRowCount1;
	}
	/*
	 * SelectAll by userid
	 */
	public List<Jumun> selectAllByUserId(String sUserId) throws Exception {
		/****************DB접속정보*************/
		String driverClass = "oracle.jdbc.OracleDriver";
		String url = "jdbc:oracle:thin:@182.237.126.19:1521:xe";
		String user = "javabackend92";
		String password = "javabackend92";
		/***************************************/ 
		List<Jumun> jumunList = new ArrayList<Jumun>();
		String selectManySql =
				"select * from jumun where userid = '"+sUserId+"'";
		Class.forName(driverClass);
		Connection con = DriverManager.getConnection(url, user, password);
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(selectManySql);
		while (rs.next()) {
			jumunList.add(new Jumun(rs.getInt("j_no"),
									rs.getString("j_desc"),
									rs.getDate("j_date"),
									rs.getInt("j_price"),
									null,
									null));
		}
		rs.close();
		st.close();
		con.close();
		return jumunList;
	}
	
	/*
	 * SelectOne by userid, j_no 
	 */
	public Jumun selectOneByUserIdNj_no(String sUserId, int j_no) throws Exception {
		/****************DB접속정보*************/
		String driverClass = "oracle.jdbc.OracleDriver";
		String url = "jdbc:oracle:thin:@182.237.126.19:1521:xe";
		String user = "javabackend92";
		String password = "javabackend92";
		/***************************************/
		String selectOneSql =
				"select * from jumun j"
				+ " join jumun_detail jd on j.j_no = jd.j_no" 
				+ " join product p on jd.p_no = p.p_no"
				+ " join userinfo u on j.userid = u.userid" 
				+ " where j.userid = '"+sUserId+"' and j.j_no = "+j_no;
		Class.forName(driverClass);
		Connection con = DriverManager.getConnection(url, user, password);
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(selectOneSql);
		List<JumunDetail> jumunDetailList = new ArrayList<JumunDetail>();
		Jumun jumun = null;
		if (rs.next()) {
			jumun = new Jumun(rs.getInt("j_no"),
							  rs.getString("j_desc"),
							  rs.getDate("j_date"),
							  rs.getInt("j_price"),	   
							  new User(rs.getString("userid"),
									   rs.getString("password"),
									   rs.getString("name"),
									   rs.getString("email")),
							  null);
			
			do { 
				
				jumunDetailList.add(new JumunDetail(rs.getInt("jd_no"),
												    rs.getInt("jd_qty"),
												    rs.getInt("j_no"),
												    new Product(rs.getInt("p_no"),
																rs.getString("p_name"),
																rs.getInt("p_price"),
																rs.getString("p_image"),
																rs.getString("p_desc"),
																rs.getInt("p_click_count"))));
			} while(rs.next());
			jumun.setJumunDetailList(jumunDetailList);	
		}
		rs.close();
		st.close();
		con.close();
		return jumun;
	}
	
	/*
	 * delete by j_no
	 */
	public int deleteByj_no(int j_no) throws Exception {
		/****************DB접속정보*************/
		String driverClass = "oracle.jdbc.OracleDriver";
		String url = "jdbc:oracle:thin:@182.237.126.19:1521:xe";
		String user = "javabackend92";
		String password = "javabackend92";
		/***************************************/
		String deleteSql1 = "delete from jumun_detail where j_no =" + j_no;		
		String deleteSql2 = "delete from jumun where j_no =" + j_no;		
		Class.forName(driverClass);
		Connection con = DriverManager.getConnection(url, user, password);
		Statement st1 = con.createStatement();
		Statement st2 = con.createStatement();
		int deleteRowCount1 = st1.executeUpdate(deleteSql1);
		int deleteRowCount2 = st2.executeUpdate(deleteSql2);
		st1.close();
		st2.close();
		con.close();
		return deleteRowCount2;
	}
}		
