package com.itwill.shop.dispatcherservlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwill.shop.cart.Cart;
import com.itwill.shop.cart.CartService;
import com.itwill.shop.jumun.Jumun;
import com.itwill.shop.jumun.JumunDetail;
import com.itwill.shop.jumun.JumunService;
import com.itwill.shop.product.Product;
import com.itwill.shop.product.ProductService;
import com.itwill.shop.user.User;
import com.itwill.shop.user.UserService;

public class DispatcherServlet1 extends HttpServlet {
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.service(request, response);
		
		/*
		 * 1.클라이언트의 요청URI를 사용해서 요청분석
		 */
		String requestURI = request.getRequestURI();
		//System.out.println("requestURI:"+requestURI);
		String contextPath=request.getContextPath();
		//System.out.println("contextPath:"+contextPath);
		String command = requestURI.substring(contextPath.length());
		//System.out.println("command:"+command);
		
		/*
		 * 2.클라이언트 요청에따른 업무실행(Service객체)
		 */
		String forwardPath="";
		/*################################################################*/
		if(command.equals("/shop_main.do")) {
			/********************shop_main.do******************/
			forwardPath = "forward:/WEB-INF/views/shop_main.jsp";
		
		}else if (command.equals("/user_view.do")) {
			/********************user_view.do******************/
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
			
		}else if (command.equals("/user_login_form.do")) {
			/********************user_login_form.do******************/
			forwardPath = "forward:/WEB-INF/views/user_login_form.jsp";
			
		}else if (command.equals("/user_login_action.do")) {
			/********************user_login_action.do******************/
			if(request.getMethod().equalsIgnoreCase("GET")){
				forwardPath = "redirect:shop_main.do";
			}else {
				request.setCharacterEncoding("UTF-8");
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
			
		}else if (command.equals("/user_logout_action.do")) {
			/********************user_logout_action.do******************/	
			String sUserId = (String)request.getSession().getAttribute("sUserId");
			if(sUserId==null || sUserId.equals("")){
				forwardPath = "redirect:shop_main.do";
			}else {
				request.getSession().invalidate();
				forwardPath = "redirect:shop_main.do";
			}
			
		}else if (command.equals("/user_write_form.do")) {
			/********************user_write_form.do******************/	
			forwardPath = "forward:/WEB-INF/views/user_write_form.jsp";
			
		}else if (command.equals("/user_write_action.do")) {
			/********************user_write_action.do******************/	
			if(request.getMethod().equalsIgnoreCase("GET")){
				forwardPath = "redirect:shop_main.do";
			}else {
				request.setCharacterEncoding("UTF-8");
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
			
		}else if (command.equals("/user_modify_form.do")) {
			/********************user_modify_form.do******************/	
			String sUserId = (String) request.getSession().getAttribute("sUserId");
			if(sUserId==null){
				forwardPath = "redirect:shop_main.do";
			}else {
				if(request.getMethod().equalsIgnoreCase("GET")) {
					forwardPath = "redirect:shop_main.do";
				}else {
					UserService userService;
					try {
						userService = new UserService();
						User user = userService.select(sUserId);
						request.setAttribute("user", user);
						forwardPath = "forward:/WEB-INF/views/user_modify_form.jsp";
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			
		}else if (command.equals("/user_modify_action.do")) {
			/********************user_modify_action.do******************/	
			String sUserId = (String) request.getSession().getAttribute("sUserId");
			if(sUserId==null){
				forwardPath = "redirect:shop_main.do";
			}else {
				if(request.getMethod().equalsIgnoreCase("GET")) {
					forwardPath = "redirect:shop_main.do";
				}else {
					request.setCharacterEncoding("UTF-8");
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
			
		}else if (command.equals("/user_remove_action.do")) {
			/********************user_remove_action.do******************/	
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
			
		}else if (command.equals("/product_list.do")) {
			/********************product_list.do******************/
			try {
				ProductService productService;
				productService = new ProductService();
				List<Product> productList = productService.selectList();
				request.setAttribute("productList", productList);
				forwardPath = "forward:/WEB-INF/views/product_list.jsp";
			} catch (Exception e) {
				e.printStackTrace();
				forwardPath = "redirect:shop_main.do";
			}
			
		}else if (command.equals("/product_detail.do")) {
			/********************product_detail.do******************/
			String p_no = request.getParameter("p_no");
			if(p_no==null || p_no.equals("")){
				forwardPath = "redirect:product_list.do";
			}else {
				try {
					ProductService productService = new ProductService();
					Product product = productService.selectByNo(Integer.parseInt(p_no));
					request.setAttribute("product", product);
					forwardPath = "forward:/WEB-INF/views/product_detail.jsp";
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
		}else if (command.equals("/cart_view.do")) {
			/********************cart_view.do******************/
			String sUserId = (String) request.getSession().getAttribute("sUserId"); 
			if(sUserId==null || sUserId.equals("")){
				forwardPath = "redirect:shop_main.do";
			}else {
				try {
					CartService cartService = new CartService();
					List<Cart> cartList = cartService.cartList(sUserId);
					request.setAttribute("cartList", cartList);
					forwardPath = "forward:/WEB-INF/views/cart_view.jsp";
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
		}else if (command.equals("/cart_add_action.do")) {
			/********************cart_add_action.do******************/
			String sUserId = (String) request.getSession().getAttribute("sUserId");
			if(sUserId==null) {
				forwardPath = "redirect:shop_main.do";
			}else {
				if(request.getMethod().equalsIgnoreCase("GET")){
					forwardPath = "redirect:shop_main.do";
				}else {
					int p_no = Integer.parseInt(request.getParameter("p_no"));
					int cart_qty = Integer.parseInt(request.getParameter("cart_qty"));
					try {
						CartService cartService = new CartService();
						cartService.addCart(sUserId, p_no, cart_qty);
						forwardPath = "redirect:cart_view.do";
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			
		}else if (command.equals("/cart_delete_action.do")) {
			/********************cart_delete_action.do******************/
			String sUserId = (String) request.getSession().getAttribute("sUserId");
			if(sUserId==null){
				forwardPath = "redirect:shop_main.do";
			}else {
				if(request.getMethod().equalsIgnoreCase("GET")){
					forwardPath = "redirect:shop_main.do";
				}else {
					try {
						CartService cartService = new CartService();
						int deleteRowCount = cartService.emptyCartByUserId(sUserId);
						forwardPath = "redirect:cart_view.do";
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			
		}else if (command.equals("/cart_delete_item_action.do")) {
			/********************cart_delete_item_action.do******************/
			String sUserId = (String) request.getSession().getAttribute("sUserId");
			if(sUserId==null){
				forwardPath = "redirect:shop_main.do";
			}else {
				if(request.getMethod().equalsIgnoreCase("GET")) {
					forwardPath = "redirect:shop_main.do";
				}else {
					try {
						int cart_no = Integer.parseInt(request.getParameter("cart_no"));
						CartService cartService = new CartService();
						int deleteRowCount = cartService.deleteCartByNo(cart_no);
						forwardPath = "redirect:cart_view.do";
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			
		}else if (command.equals("/jumun_list.do")) {
			/********************jumun_list.do******************/
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
			
		}else if (command.equals("/jumun_detail.do")) {
			/********************jumun_detail.do******************/
			String sUserId = (String) request.getSession().getAttribute("sUserId");
			if(sUserId==null) {	
				forwardPath = "redirect:shop_main.do";
			}else {
				try {
					int j_no = Integer.parseInt(request.getParameter("j_no"));
					JumunService jumunService = new JumunService();
					Jumun jumun = jumunService.jumunDetail(sUserId,j_no);
					List<JumunDetail> jumunDetailList = jumun.getJumunDetailList();
					request.setAttribute("jumun", jumun);
					forwardPath = "forward:/WEB-INF/views/jumun_detail.jsp";
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
		}else if (command.equals("/jumun_create_form.do")) {
			/********************jumun_create_form.do******************/
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
						CartService cartService = new CartService();
						ProductService productService = new ProductService();
						UserService userService = new UserService();
						User user = userService.select(sUserId);
						List<Cart> cartList = new ArrayList<Cart>();
						if(buyType.equals("cart")){
							cartList = cartService.cartList(sUserId);
							
						}else if(buyType.equals("direct")) {
							Product product = productService.selectByNo(Integer.parseInt(p_noStr));
							//int cart_no, int cart_qty, Product product, User user
							cartList.add(new Cart(0, Integer.parseInt(p_qtyStr), product, user));
							
						}
						request.setAttribute("user", user);
						request.setAttribute("cartList", cartList);
						forwardPath = "forward:/WEB-INF/views/jumun_create_form.jsp";
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			
		}else if (command.equals("/jumun_create_action.do")) {
			/********************jumun_create_action.do******************/
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
			
		}else if (command.equals("/jumun_delete_action.do")) {
			/********************jumun_delete_action.do******************/
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
		}
		
		/*################################################################*/
		
		/*
		 * 3.JSP forward or redirect
		 */
		String[] pathArray=forwardPath.split(":");
		String forwardOrRedirect=pathArray[0];
		String path=pathArray[1];
		if(forwardOrRedirect.equals("redirect")) {
			response.sendRedirect(path);
		}else if(forwardOrRedirect.equals("forward")) {
			RequestDispatcher rd=request.getRequestDispatcher(path);
			rd.forward(request, response);
		}
	}
	
}
