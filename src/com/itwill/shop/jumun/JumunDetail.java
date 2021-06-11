package com.itwill.shop.jumun;
/*
이름     널? 유형         
------ -- ---------- 
JD_NO     NUMBER(10) 
JD_QTY    NUMBER(10) 
J_NO      NUMBER(10) 
P_NO      NUMBER(10) 
 */

import com.itwill.shop.product.Product;

public class JumunDetail {
	
	private int jd_no;
	private int jd_qty;
	private int j_no;
	private Product product;
	
	public JumunDetail() {
		// TODO Auto-generated constructor stub
	}

	public JumunDetail(int jd_no, int jd_qty, int j_no, Product product) {
		super();
		this.jd_no = jd_no;
		this.jd_qty = jd_qty;
		this.j_no = j_no;
		this.product = product;
	}

	public int getJd_no() {
		return jd_no;
	}

	public void setJd_no(int jd_no) {
		this.jd_no = jd_no;
	}

	public int getJd_qty() {
		return jd_qty;
	}

	public void setJd_qty(int jd_qty) {
		this.jd_qty = jd_qty;
	}

	public int getJ_no() {
		return j_no;
	}

	public void setJ_no(int j_no) {
		this.j_no = j_no;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@Override
	public String toString() {
		return "JumunDetail [jd_no=" + jd_no + ", jd_qty=" + jd_qty + ", j_no=" + j_no + ", product=" + product + "]";
	}
	
	
}
