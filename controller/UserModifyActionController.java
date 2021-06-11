package com.itwill.shop.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwill.shop.dispatcherservlet.Controller;
import com.itwill.shop.user.User;
import com.itwill.shop.user.UserService;

public class UserModifyActionController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
		
		String forwardPath = "";
		/*****************************************/
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
				//request.setCharacterEncoding("UTF-8");
				String userId =	request.getParameter("userId");
				String password = request.getParameter("password");
				String name = request.getParameter("name");
				String email = request.getParameter("email");
				try {
					UserService userService = new UserService();
					User user = new User(userId, password, name, email);
					int updateRowCount = userService.update(user);
					forwardPath = "redirect:user_view.do";
				} catch (Exception e) {
					e.printStackTrace();
					forwardPath = "forward:/WEB-INF/views/user_error.jsp";
				}
			}
		}
		return forwardPath;
	}

}
