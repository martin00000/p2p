package com.eloan.business.query;

import com.eloan.base.query.QueryObject;
import com.eloan.base.util.DateUtil;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class BaseAuditQueryObject extends QueryObject {

	private int state = -1;
	private Date beginDate;
	private Date endDate;

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

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public Date getBeginDate() {
		return beginDate;
	}
}
