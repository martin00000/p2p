package com.eloan.base.util;

public class ResultJSON {
	private Boolean success = false;
	private String msg;
	private Integer errCode;

	public ResultJSON() {
		super();
	}

	public ResultJSON(Boolean success, String msg, Integer errCode) {
		super();
		this.success = success;
		this.msg = msg;
		this.errCode = errCode;
	}

	public ResultJSON(Boolean success, String msg) {
		this.success = success;
		this.msg = msg;
	}

	public ResultJSON(Boolean success) {
		this.success = success;
	}

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Integer getErrCode() {
		return errCode;
	}

	public void setErrCode(Integer errCode) {
		this.errCode = errCode;
	}

	public String getMsg() {
		return msg;
	}
}
