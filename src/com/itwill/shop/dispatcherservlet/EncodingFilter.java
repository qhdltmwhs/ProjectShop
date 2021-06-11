package com.itwill.shop.dispatcherservlet;
import java.io.IOException;

import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class EncodingFilter implements Filter {
	private String encoding = null;
	
	public EncodingFilter() {
		//System.out.println("0.EncodingFilter객체생성:"+this);
	}
	/**
	 * web.xml에서 전달된 인코딩 값을 초기화하는 메써드.
	 * <filter>
		<filter-name>Encoding Filter</filter-name>
		<filter-class>com.itwill.filter.EncodingFilter</filter-class>
		<init-param>
			<param-name>encoding</param-name>
			<param-value>UTF-8</param-value>
		</init-param>
	   </filter>
	 */
	public void init(FilterConfig filterConfig) throws ServletException {
		this.encoding = filterConfig.getInitParameter("encoding");
		//System.out.println("2.생성직후 단한번호출 init(): encoding parameter-->"
		//			+ encoding);
	}
	

	/**
	 * ServletRequest객체에 web.xml에서 전달된 인코딩을 설정하는 메써드.
	 */
	public void doFilter(
		ServletRequest request,
		ServletResponse response,
		FilterChain chain)
		throws IOException, ServletException {
		//System.out.println("3.EncodingFilter 요청시마다 호출 doFilter()");
		
		
		
		
		if (request.getCharacterEncoding() == null) {
			if (encoding != null) {
				request.setCharacterEncoding(encoding);
			}
		}
		chain.doFilter(request, response);
		/*
		response.setContentType("text/plain;charset=UTF-8");
		PrintWriter out=response.getWriter();
		out.print("난 필터!! 넌 누구!!!");
		*/
	}
	
	public void destroy() {
		//3System.out.println("4.필터객체가 메모리에서 해지될때호추울 destroy()");
		this.encoding = null;
	}
	
}
