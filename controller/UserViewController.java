package com.itwill.shop.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwill.shop.dispatcherservlet.Controller;
import com.itwill.shop.user.User;
import com.itwill.shop.user.UserService;

public class UserViewController implements Controller {

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
				UserService userService = new UserService();
				User user = userService.select(sUserId);
				request.setAttribute("user", user);
				forwardPath = "forward:/WEB-INF/views/user_view.jsp";
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return forwardPath;
	}

}
