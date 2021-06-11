package com.itwill.shop.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwill.shop.dispatcherservlet.Controller;
import com.itwill.shop.jumun.JumunService;

public class JumunDeleteActionController implements Controller {

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
			if(request.getMethod().equalsIgnoreCase("GET")){
				forwardPath = "redirect:shop_main.do";
			}else {
				try {
					int j_no = Integer.parseInt(request.getParameter("j_no"));
					JumunService jumunService = new JumunService();
					int deleteRowCount = jumunService.deleteJumun(j_no);
					forwardPath = "redirect:jumun_list.do";
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return forwardPath;
	}

}
