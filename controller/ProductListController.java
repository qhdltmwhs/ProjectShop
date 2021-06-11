package com.itwill.shop.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwill.shop.dispatcherservlet.Controller;
import com.itwill.shop.product.Product;
import com.itwill.shop.product.ProductService;

public class ProductListController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
		
		String forwardPath = "";
		/*
		 * 메인비지니스업무라인
		 */
		try {
			ProductService productService;
			productService = new ProductService();
			List<Product> productList = productService.selectList();
			request.setAttribute("productList", productList);
			forwardPath = "forward:/WEB-INF/views/product_list.jsp";
		} catch (Exception e) {
			e.printStackTrace();
			forwardPath = "redirect:shop_main.do";
		}
		return forwardPath;
	}

}
