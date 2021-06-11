package com.itwill.shop.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwill.shop.cart.Cart;
import com.itwill.shop.cart.CartService;
import com.itwill.shop.dispatcherservlet.Controller;

public class CartViewController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
		
		String forwardPath = "";
		/*
		 * 메인비지니스업무라인
		 */
		String sUserId = (String) request.getSession().getAttribute("sUserId"); 
		if(sUserId==null || sUserId.equals("")){
			forwardPath = "redirect:shop_main.do";
		}else {
			try {
				CartService cartService = new CartService();
				List<Cart> cartList = cartService.cartList(sUserId);
				request.setAttribute("cartList", cartList);
				forwardPath = "forward:/WEB-INF/views/cart_view.jsp";
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return forwardPath;
	}

}
