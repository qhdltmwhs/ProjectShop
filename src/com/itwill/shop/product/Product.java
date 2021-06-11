package com.itwill.shop.product;
/*       
------------- -------- ------------- 
P_NO          NOT NULL NUMBER(10)    
P_NAME        NOT NULL VARCHAR2(50)  
P_PRICE       NOT NULL NUMBER(10)    
P_IMAGE       NOT NULL VARCHAR2(100) 
P_DESC                 VARCHAR2(200) 
P_CLICK_COUNT NOT NULL NUMBER(10)    
 */
public class Product {
	
	private int p_no;
	private String p_name;
	private int p_price;
	private String p_image;
	private String p_desc;
	private int p_click_count;
	
	public Product() {
		// TODO Auto-generated constructor stub
	}

	public Product(int p_no, String p_name, int p_price, String p_image, String p_desc, int p_click_count) {
		super();
		this.p_no = p_no;
		this.p_name = p_name;
		this.p_price = p_price;
		this.p_image = p_image;
		this.p_desc = p_desc;
		this.p_click_count = p_click_count;
	}

	public int getP_no() {
		return p_no;
	}

	public void setP_no(int p_no) {
		this.p_no = p_no;
	}

	public String getP_name() {
		return p_name;
	}

	public void setP_name(String p_name) {
		this.p_name = p_name;
	}

	public int getP_price() {
		return p_price;
	}

	public void setP_price(int p_price) {
		this.p_price = p_price;
	}

	public String getP_image() {
		return p_image;
	}

	public void setP_image(String p_image) {
		this.p_image = p_image;
	}

	public String getP_desc() {
		return p_desc;
	}

	public void setP_desc(String p_desc) {
		this.p_desc = p_desc;
	}

	public int getP_click_count() {
		return p_click_count;
	}

	public void setP_click_count(int p_click_count) {
		this.p_click_count = p_click_count;
	}

	@Override
	public String toString() {
		return "Product [p_no=" + p_no + ", p_name=" + p_name + ", p_price=" + p_price + ", p_image=" + p_image
				+ ", p_desc=" + p_desc + ", p_click_count=" + p_click_count + "]";
	}
	
}
