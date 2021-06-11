package com.itwill.shop.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwill.shop.dispatcherservlet.Controller;
import com.itwill.shop.product.Product;
import com.itwill.shop.product.ProductService;

public class ProductDetailController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
		
		String forwardPath = "";
		/*
		 * 메인비지니스업무라인
		 */
		String p_no = request.getParameter("p_no");
		if(p_no==null || p_no.equals("")){
			forwardPath = "redirect:product_list.do";
		}else {
			try {
				ProductService productService = new ProductService();
				Product product = productService.selectByNo(Integer.parseInt(p_no));
				request.setAttribute("product", product);
				forwardPath = "forward:/WEB-INF/views/product_detail.jsp";
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return forwardPath;
	}

}
