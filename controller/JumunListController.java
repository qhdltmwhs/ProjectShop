package com.itwill.shop.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwill.shop.dispatcherservlet.Controller;
import com.itwill.shop.jumun.Jumun;
import com.itwill.shop.jumun.JumunService;

public class JumunListController implements Controller {
	
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
				JumunService jumunService = new JumunService();
				List<Jumun> jumunList = jumunService.jumunList(sUserId);
				request.setAttribute("jumunList", jumunList);
				forwardPath = "forward:/WEB-INF/views/jumun_list.jsp";
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return forwardPath;
	}
}
