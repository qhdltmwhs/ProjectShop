package com.itwill.shop.cart;

import com.itwill.shop.product.Product;
import com.itwill.shop.user.User;

/*
이름       널? 유형            
-------- -- ------------- 
CART_NO     NUMBER(10)    
CART_QTY    NUMBER(10)    
P_NO        NUMBER(10)    
USERID      VARCHAR2(100)
 */
public class Cart {
	
	private int cart_no;
	private int cart_qty;
	private Product product;
	private User user;
	
	public Cart() {
		// TODO Auto-generated constructor stub
	}

	public Cart(int cart_no, int cart_qty, Product product, User user) {
		super();
		this.cart_no = cart_no;
		this.cart_qty = cart_qty;
		this.product = product;
		this.user = user;
	}

	public int getCart_no() {
		return cart_no;
	}

	public void setCart_no(int cart_no) {
		this.cart_no = cart_no;
	}

	public int getCart_qty() {
		return cart_qty;
	}

	public void setCart_qty(int cart_qty) {
		this.cart_qty = cart_qty;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Cart [cart_no=" + cart_no + ", cart_qty=" + cart_qty + ", product=" + product + ", user=" + user + "]";
	}
	
}
