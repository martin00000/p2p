package com.eloan.base.domain;

import org.apache.ibatis.type.Alias;


@Alias("logininfo")
public class Logininfo extends BaseDomain {

	public static final int STATE_NORMAL = 0;
	public static final int STATE_LOCK = 1;
	public static final int STATE_DELETE = -1;

	public static final int USERTYPE_NORMAL = 0;//前段用户
	public static final int USERTYPE_SYSTEM = 1;//后台用户

	private String username;
	private String password;
	private int state = STATE_NORMAL;

	private int userType;//用户类型
	private boolean admin = false;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public int getUserType() {
		return userType;
	}

	public void setUserType(int userType) {
		this.userType = userType;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}
}
