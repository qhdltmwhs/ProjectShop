package com.itwill.shop.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwill.shop.dispatcherservlet.Controller;
import com.itwill.shop.jumun.JumunService;

public class JumunCreateActionController implements Controller {

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
					JumunService jumunService = new JumunService();
					if(buyType.equals("cart")){
						jumunService.createJumun(sUserId);
					}else if(buyType.equals("direct")) {
						jumunService.createJumun(sUserId, Integer.parseInt(p_noStr), Integer.parseInt(p_qtyStr));
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				forwardPath = "redirect:jumun_list.do";
			}
		}
		return forwardPath;
	}

}
