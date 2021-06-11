package com.itwill.shop.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwill.shop.dispatcherservlet.Controller;
import com.itwill.shop.user.User;
import com.itwill.shop.user.UserService;

public class UserWriteActionController implements Controller {

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
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			User user = null;
			try{
				user = new User(userId, password, name, email);
				UserService userService = new UserService();
				int result = userService.memberRegist(user);
				if(result == 1){
					forwardPath = "redirect:user_write_form.do";
				}else {
					forwardPath = "redirect:user_login_form.do";	
				}
			}catch (Exception e) {
				e.printStackTrace();
				forwardPath = "forward:/WEB-INF/views/user_error.jsp";
			}
		}
		return forwardPath;
	}

}
