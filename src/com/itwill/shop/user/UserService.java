package com.itwill.shop.user;

public class UserService {
	
	private UserDao userDao;
	
	public UserService() throws Exception {
		userDao = new UserDao();
	}
	
	public User select(String userId) throws Exception {
		return userDao.selectUserById(userId);
	}
	
	public int insert(User user) throws Exception {
		return userDao.insertUser(user);
	}
	
	public int update(User user) throws Exception {
		return userDao.updateUser(user);
	}
	
	public int delete(String userId) throws Exception {
		return userDao.deleteUser(userId);
	}
	
	/*
	 * 회원가입
	 */
	public int memberRegist(User user) throws Exception {
		//아이디 체크후
		int result = 0;
		if (userDao.duplicateIdCheck(user.getUserId())) {
			result = 1; 
		}else {
			userDao.insertUser(user);
			result = 2;
		}
		return result;
	}
	
	/*
	 * 로그인
	 */
	public boolean login(String userId, String password) throws Exception {
		User findUser = userDao.selectUserById(userId);
		if (findUser!=null) {
			if(findUser.isMatchPassword(password)) {
				return true;
			}else {
				return false;
			}
		}else {
			return false;
		}
	}
}
