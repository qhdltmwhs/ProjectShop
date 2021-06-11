package com.itwill.shop.user.test;

import com.itwill.shop.user.User;
import com.itwill.shop.user.UserService;

public class UserServiceTestMain {

	public static void main(String[] args) throws Exception {
		UserService userService = new UserService();
		System.out.println(userService.memberRegist(new User("ddd", "ddd", "디디디", "d@d.co.kr")));
		System.out.println(userService.login("ddd", "ddd"));

	}

}
