package com.itwill.shop.cart.test;

import com.itwill.shop.cart.CartService;

public class CartServiceTestMain {

	public static void main(String[] args) throws Exception {
		
		CartService cartService = new CartService();
		System.out.println(cartService.cartList("guard1"));

	}

}
