<%@page import="com.itwill.shop.cart.CartService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	String sUserId = (String)session.getAttribute("sUserId");
	if(sUserId==null) {
		response.sendRedirect("shop_main.jsp");
		return;
	}
	if(request.getMethod().equalsIgnoreCase("GET")){
		response.sendRedirect("shop_main.jsp");
		return;
	}
	int p_no = Integer.parseInt(request.getParameter("p_no"));
	int cart_qty = Integer.parseInt(request.getParameter("cart_qty"));
	
	CartService cartService = new CartService();
	cartService.addCart(sUserId, p_no, cart_qty);
	
 	response.sendRedirect("cart_view.jsp");
%>