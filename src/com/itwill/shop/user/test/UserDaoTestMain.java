package com.itwill.shop.user.test;

import com.itwill.shop.user.User;
import com.itwill.shop.user.UserDao;

public class UserDaoTestMain {

	public static void main(String[] args) throws Exception {
		UserDao userDao = new UserDao();
		
		System.out.println("#######[ Select ]#######");
		User findUser1 = userDao.selectUserById("mone");
		User findUser2 = userDao.selectUserById("guard1");
		System.out.println(findUser1);
		System.out.println(findUser2);
		
		System.out.println("#######[ Delete ]#######");
		int deleteRowCount1 = userDao.deleteUser("mone");
		int deleteRowCount2 = userDao.deleteUser("nemo");
		System.out.println("deleteRowCount1 :"+deleteRowCount1);
		System.out.println("deleteRowCount2 :"+deleteRowCount2);
		
		System.out.println("#######[ Insert ]#######");
		int insertRowCount1 = userDao.insertUser("mone", "zzz", "zzz", "zzz");
		int insertRowCount2 = userDao.insertUser(new User("nemo", "xxx", "xxx", "xxx"));
		System.out.println("insertRowCount1 :"+insertRowCount1);
		System.out.println("insertRowCount2 :"+insertRowCount2);
		
		System.out.println("#######[ Update ]#######");
		int updateRowCount1 = userDao.updateUser("mone", "1318", "모네", "mone@mone.com");
		int updateRowCount2 = userDao.updateUser(new User("nemo", "1004", "네모", "nemo@nemo.com"));
		System.out.println("updateRowCount1 :"+updateRowCount1);
		System.out.println("updateRowCount2 :"+updateRowCount2);
	}

}
