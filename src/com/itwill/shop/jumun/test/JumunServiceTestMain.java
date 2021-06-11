package com.itwill.shop.jumun.test;

import com.itwill.shop.jumun.JumunService;

public class JumunServiceTestMain {

	public static void main(String[] args) throws Exception {
		
		JumunService jumunService = new JumunService();
		System.out.println(jumunService.jumunList("guard1"));

	}

}
