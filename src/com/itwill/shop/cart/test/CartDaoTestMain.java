package com.itwill.shop.cart.test;

import com.itwill.shop.cart.CartDao;

public class CartDaoTestMain {

	public static void main(String[] args) throws Exception {
		
		CartDao cartDao = new CartDao();
		//System.out.println(cartDao.add("guard1", 2, 3));
		//System.out.println(cartDao.cartList("guard1"));
		System.out.println(cartDao.isExistProduct("guard1", 1));
		System.out.println(cartDao.isExistProduct("guard1", 2));
		System.out.println(cartDao.isExistProduct("guard1", 3));
		System.out.println(cartDao.isExistProduct("guard1", 4));
		System.out.println(cartDao.update("guard1", 2, 1));
	}

}
