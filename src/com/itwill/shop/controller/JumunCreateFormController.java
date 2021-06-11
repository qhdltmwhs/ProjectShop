package com.itwill.shop.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwill.shop.cart.Cart;
import com.itwill.shop.cart.CartService;
import com.itwill.shop.dispatcherservlet.Controller;
import com.itwill.shop.product.Product;
import com.itwill.shop.product.ProductService;
import com.itwill.shop.user.User;
import com.itwill.shop.user.UserService;

public class JumunCreateFormController implements Controller {

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
				String buyType = request.getParameter("buyType");
				String p_noStr = request.getParameter("p_no");
				String p_qtyStr = request.getParameter("p_qty");
				try {
					CartService cartService = new CartService();
					ProductService productService = new ProductService();
					UserService userService = new UserService();
					User user = userService.select(sUserId);
					List<Cart> cartList = new ArrayList<Cart>();
					if(buyType.equals("cart")){
						cartList = cartService.cartList(sUserId);
						
					}else if(buyType.equals("direct")) {
						Product product = productService.selectByNo(Integer.parseInt(p_noStr));
						//int cart_no, int cart_qty, Product product, User user
						cartList.add(new Cart(0, Integer.parseInt(p_qtyStr), product, user));
						
					}
					request.setAttribute("user", user);
					request.setAttribute("cartList", cartList);
					forwardPath = "forward:/WEB-INF/views/jumun_create_form.jsp";
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}

}
