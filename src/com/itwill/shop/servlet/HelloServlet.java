package com.itwill.shop.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/hello.do")
public class HelloServlet extends HttpServlet {
		
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(Thread.currentThread().getName()+" 스레드 ");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<meta charset='UTF-8'>");
		out.println("<title>Insert title here</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h1>안녕하세요~!!!</h1>");
		out.println("<h2>안녕하세요~!!!</h1>");
		out.println("<h3>안녕하세요~!!!</h1>");
		out.println("<p>안녕하세요~!!!</p>");
		int no = (int)(10*Math.random())+1;
		for (int i = 0; i < no; i++) {
			out.println("<h3>Hello[안녕...] Servlet!!![web dynamic resource]"+(i+1)+"</h3>");
			out.println("<hr>");
		}
		out.println("</body>");
		out.println("</html>");		
	}
}
