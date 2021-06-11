package com.itwill.shop.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwill.shop.cart.CartService;
import com.itwill.shop.dispatcherservlet.Controller;

public class CartAddActionController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
		
		String forwardPath = "";	
		/*
		 * 메인비지니스업무라인
		 */
		String sUserId = (String) request.getSession().getAttribute("sUserId");
		if(sUserId==null) {
			forwardPath = "redirect:shop_main.do";
		}else {
			if(request.getMethod().equalsIgnoreCase("GET")){
				forwardPath = "redirect:shop_main.do";
			}else {
				int p_no = Integer.parseInt(request.getParameter("p_no"));
				int cart_qty = Integer.parseInt(request.getParameter("cart_qty"));
				try {
					CartService cartService = new CartService();
					cartService.addCart(sUserId, p_no, cart_qty);
					forwardPath = "redirect:cart_view.do";
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return forwardPath;	
	}

}
