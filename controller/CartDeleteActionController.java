package com.itwill.shop.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwill.shop.cart.CartService;
import com.itwill.shop.dispatcherservlet.Controller;

public class CartDeleteActionController implements Controller {
	
	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
		
		String forwardPath = "";
		/*
		 * 메인비지니스업무라인
		 */
		String sUserId = (String) request.getSession().getAttribute("sUserId");
		if(sUserId==null){
			forwardPath = "redirect:shop_main.do";
		}else {
			if(request.getMethod().equalsIgnoreCase("GET")){
				forwardPath = "redirect:shop_main.do";
			}else {
				try {
					CartService cartService = new CartService();
					int deleteRowCount = cartService.emptyCartByUserId(sUserId);
					forwardPath = "redirect:cart_view.do";
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return forwardPath;
	}
	
}
