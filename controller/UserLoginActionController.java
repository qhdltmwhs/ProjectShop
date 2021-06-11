package com.itwill.shop.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwill.shop.dispatcherservlet.Controller;
import com.itwill.shop.user.User;
import com.itwill.shop.user.UserService;

public class UserLoginActionController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
		
		String forwardPath = "";
		/*
		 * 메인비지니스업무라인
		 */
		if(request.getMethod().equalsIgnoreCase("GET")){
			forwardPath = "redirect:shop_main.do";
		}else {
			//request.setCharacterEncoding("UTF-8");
			String userId = request.getParameter("userId");
			String password = request.getParameter("password");
			User user = null;
			try{
				UserService userService = new UserService();
				if(userService.login(userId, password)){
					request.getSession().setAttribute("sUserId", userId);
					forwardPath = "redirect:shop_main.do";
				}else {
					forwardPath = "redirect:user_login_form.do";
				}
			}catch (Exception e){
				e.printStackTrace();
				forwardPath = "forward:/WEB-INF/views/user_error.jsp";
			}
		}
		return forwardPath;
	}

}
