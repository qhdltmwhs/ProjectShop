package com.itwill.shop.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwill.shop.dispatcherservlet.Controller;
import com.itwill.shop.user.UserService;

public class UserRemoveActionController implements Controller {

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
			if(request.getMethod().equalsIgnoreCase("GET")) {
				forwardPath = "redirect:shop_main.do";
			}else {
				try {
					UserService userService = new UserService();
					int deleteRowCount = userService.delete(sUserId);
					forwardPath = "redirect:user_logout_action.do";
				} catch (Exception e) {
					e.printStackTrace();
					forwardPath = "forward:/WEB-INF/views/user_error.jsp";
				}
			}
		}
		return forwardPath;
	}

}
