<%@page import="com.itwill.shop.cart.CartService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String sUserId = (String)session.getAttribute("sUserId");
	if(sUserId==null){
		response.sendRedirect("shop_main.jsp");
		return;
	}
	if(request.getMethod().equalsIgnoreCase("GET")) {
		response.sendRedirect("shop_main.jsp");
		return;
	}
	int cart_no = Integer.parseInt(request.getParameter("cart_no"));
	CartService cartService = new CartService();
	int deleteRowCount = cartService.deleteCartByNo(cart_no);
	response.sendRedirect("cart_view.jsp");
%>