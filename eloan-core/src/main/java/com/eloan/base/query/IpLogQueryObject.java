package com.eloan.base.query;

import com.eloan.base.util.DateUtil;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * IpLog查询对象
 * @author Administrator
 *
 */
public class IpLogQueryObject extends QueryObject {

	private Date beginDate;
	private Date endDate;
	private String username;
	private int userType=-1;
	private boolean like;
	private int state=-1;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getEndDate() {
		if (endDate != null) {
			return DateUtil.endOfDay(endDate);
		}
		return null;
	}

	public Date getBeginDate() {
		return beginDate;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getUserType() {
		return userType;
	}

	public void setUserType(int userType) {
		this.userType = userType;
	}

	public boolean isLike() {
		return like;
	}

	public void setLike(boolean like) {
		this.like = like;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}
}
