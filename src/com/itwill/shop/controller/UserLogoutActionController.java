package com.itwill.shop.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwill.shop.dispatcherservlet.Controller;

public class UserLogoutActionController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
		
		String forwardPath = "";
		/*
		 * 메인비지니스업무라인
		 */
		String sUserId = (String)request.getSession().getAttribute("sUserId");
		if(sUserId==null || sUserId.equals("")){
			forwardPath = "redirect:shop_main.do";
		}else {
			request.getSession().invalidate();
			forwardPath = "redirect:shop_main.do";
		}
		return forwardPath;
	}

}
