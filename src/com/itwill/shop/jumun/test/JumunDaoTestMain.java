package com.itwill.shop.jumun.test;

import com.itwill.shop.jumun.JumunDao;

public class JumunDaoTestMain {

	public static void main(String[] args) throws Exception {
		
		JumunDao jumunDao = new JumunDao();
		
		//System.out.println(jumunDao.selectAllByUserId("guard1"));
		System.out.println(jumunDao.selectOneByUserIdNj_no("guard1", 1));
		
		
	}

}
