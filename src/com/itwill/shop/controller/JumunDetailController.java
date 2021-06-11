package com.itwill.shop.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwill.shop.dispatcherservlet.Controller;
import com.itwill.shop.jumun.Jumun;
import com.itwill.shop.jumun.JumunDetail;
import com.itwill.shop.jumun.JumunService;

public class JumunDetailController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
		
		String forwardPath = "";
		/*
		 * 메인비지니스업무라인
		 */
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
		return forwardPath;
	}

}
