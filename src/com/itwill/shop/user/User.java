package com.itwill.shop.user;
/*
 이름       널?       유형            
-------- -------- ------------- 
USERID   NOT NULL VARCHAR2(100) 
PASSWORD          VARCHAR2(100) 
NAME              VARCHAR2(100) 
EMAIL             VARCHAR2(100) 
 */
public class User {
	
	private String userId;
	private String password;
	private String name;
	private String email;
	
	public User() {
		// TODO Auto-generated constructor stub
	}

	public User(String userId, String password, String name, String email) {
		super();
		this.userId = userId;
		this.password = password;
		this.name = name;
		this.email = email;
	}

	public String getUserId() {
		return userId;
	}

	public String getPassword() {
		return password;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", password=" + password + ", name=" + name + ", email=" + email + "]";
	}

	public boolean isMatchPassword(String password) {
		if (this.password.equals(password)) {
			return true;
		}else {
			return false;
		}
	}
	
}
