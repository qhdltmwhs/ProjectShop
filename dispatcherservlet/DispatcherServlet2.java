package com.itwill.shop.dispatcherservlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwill.shop.controller.CartAddActionController;
import com.itwill.shop.controller.CartDeleteActionController;
import com.itwill.shop.controller.CartDeleteItemActionController;
import com.itwill.shop.controller.CartViewController;
import com.itwill.shop.controller.JumunCreateActionController;
import com.itwill.shop.controller.JumunCreateFormController;
import com.itwill.shop.controller.JumunDeleteActionController;
import com.itwill.shop.controller.JumunDetailController;
import com.itwill.shop.controller.JumunListController;
import com.itwill.shop.controller.ProductDetailController;
import com.itwill.shop.controller.ProductListController;
import com.itwill.shop.controller.ShopMainController;
import com.itwill.shop.controller.UserLoginActionController;
import com.itwill.shop.controller.UserLoginFormController;
import com.itwill.shop.controller.UserLogoutActionController;
import com.itwill.shop.controller.UserModifyActionController;
import com.itwill.shop.controller.UserModifyFormController;
import com.itwill.shop.controller.UserRemoveActionController;
import com.itwill.shop.controller.UserViewController;
import com.itwill.shop.controller.UserWriteActionController;
import com.itwill.shop.controller.UserWriteFormController;

public class DispatcherServlet2 extends HttpServlet {
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.service(request, response);
		
		/*
		 * 1.클라이언트의 요청URI를 사용해서 요청분석
		 */
		String requestURI = request.getRequestURI();
		//System.out.println("requestURI:"+requestURI);
		String contextPath = request.getContextPath();
		//System.out.println("contextPath:"+contextPath);
		String command = requestURI.substring(contextPath.length());
		//System.out.println("command:"+command);
		
		/*
		 * 2-1.클라이언트 요청에따른 업무실행Controller객체생성
		 */
		String forwardPath = "";
		Controller controller = null;
		/*################################################################*/
		if(command.equals("/shop_main.do")) {
			/********************shop_main.do******************/
			controller = new ShopMainController();
		
		}else if (command.equals("/user_view.do")) {
			/********************user_view.do******************/
			controller = new UserViewController();
			
		}else if (command.equals("/user_login_form.do")) {
			/********************user_login_form.do******************/
			controller = new UserLoginFormController();
			
		}else if (command.equals("/user_login_action.do")) {
			/********************user_login_action.do******************/
			controller = new UserLoginActionController();
			
		}else if (command.equals("/user_logout_action.do")) {
			/********************user_logout_action.do******************/	
			controller = new UserLogoutActionController();
			
		}else if (command.equals("/user_write_form.do")) {
			/********************user_write_form.do******************/	
			controller = new UserWriteFormController();
			
		}else if (command.equals("/user_write_action.do")) {
			/********************user_write_action.do******************/	
			controller = new UserWriteActionController();	
			
		}else if (command.equals("/user_modify_form.do")) {
			/********************user_modify_form.do******************/	
			controller = new UserModifyFormController();
			
		}else if (command.equals("/user_modify_action.do")) {
			/********************user_modify_action.do******************/	
			controller = new UserModifyActionController();
			
		}else if (command.equals("/user_remove_action.do")) {
			/********************user_remove_action.do******************/	
			controller = new UserRemoveActionController();
			
		}else if (command.equals("/product_list.do")) {
			/********************product_list.do******************/
			controller = new ProductListController();
			
		}else if (command.equals("/product_detail.do")) {
			/********************product_detail.do******************/
			controller = new ProductDetailController();
			
		}else if (command.equals("/cart_view.do")) {
			/********************cart_view.do******************/
			controller = new CartViewController();
			
		}else if (command.equals("/cart_add_action.do")) {
			/********************cart_add_action.do******************/
			controller = new CartAddActionController();
			
		}else if (command.equals("/cart_delete_action.do")) {
			/********************cart_delete_action.do******************/
			controller = new CartDeleteActionController();
			
		}else if (command.equals("/cart_delete_item_action.do")) {
			/********************cart_delete_item_action.do******************/
			controller = new CartDeleteItemActionController();
			
		}else if (command.equals("/jumun_list.do")) {
			/********************jumun_list.do******************/
			controller = new JumunListController();
			
		}else if (command.equals("/jumun_detail.do")) {
			/********************jumun_detail.do******************/
			controller = new JumunDetailController();
			
		}else if (command.equals("/jumun_create_form.do")) {
			/********************jumun_create_form.do******************/
			controller = new JumunCreateFormController();
			
		}else if (command.equals("/jumun_create_action.do")) {
			/********************jumun_create_action.do******************/
			controller = new JumunCreateActionController();
			
		}else if (command.equals("/jumun_delete_action.do")) {
			/********************jumun_delete_action.do******************/
			controller = new JumunDeleteActionController();
			
		}
		
		/*
		 * 2-2.클라이언트 요청에따른 업무실행Controller객체의 업무실행
		 */
		forwardPath = controller.handleRequest(request, response);
		/*################################################################*/
		
		/*
		 * 3.JSP forward or redirect
		 */
		String[] pathArray = forwardPath.split(":");
		String forwardOrRedirect = pathArray[0];
		String path = pathArray[1];
		if(forwardOrRedirect.equals("redirect")) {
			response.sendRedirect(path);
		}else if(forwardOrRedirect.equals("forward")) {
			RequestDispatcher rd = request.getRequestDispatcher(path);
			rd.forward(request, response);
		}
	}
	
}
