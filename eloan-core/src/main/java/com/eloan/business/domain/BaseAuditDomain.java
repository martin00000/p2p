package com.eloan.business.domain;

import com.eloan.base.domain.BaseDomain;
import com.eloan.base.domain.Logininfo;

import java.util.Date;

abstract public class BaseAuditDomain extends BaseDomain {

	public static final int STATE_APPLY = 0;//申请状态
	public static final int STATE_PASS = 1;//审核通过
	public static final int STATE_REJECT = 2;//审核拒绝

	private Logininfo applier;//申请人
	private Date applyTime;//申请时间
	private Logininfo auditor;//审核人
	private Date auditTime;//审核时间
	private int state;//状态
	private String remark;//审核备注

	public String getStateDisplay() {
		switch (state) {
		case STATE_APPLY:
			return "申请状态";
		case STATE_PASS:
			return "审核通过";
		case STATE_REJECT:
			return "审核失败";
		default:
			return "异常";
		}
	}

	public Logininfo getApplier() {
		return applier;
	}

	public void setApplier(Logininfo applier) {
		this.applier = applier;
	}

	public Date getApplyTime() {
		return applyTime;
	}

	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
	}

	public Logininfo getAuditor() {
		return auditor;
	}

	public void setAuditor(Logininfo auditor) {
		this.auditor = auditor;
	}

	public Date getAuditTime() {
		return auditTime;
	}

	public void setAuditTime(Date auditTime) {
		this.auditTime = auditTime;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}
