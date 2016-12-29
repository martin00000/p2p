package com.eloan.business.domain;

import java.util.Date;

public class VerifyCode {

	private String phoneNumber;
	private String randomCode;
	private Date lastSendTime;
	private String content;

	public VerifyCode() {
		super();
	}

	public VerifyCode(String phoneNumber, String randomCode, Date lastSendTime,
			String content) {
		super();
		this.phoneNumber = phoneNumber;
		this.randomCode = randomCode;
		this.lastSendTime = lastSendTime;
		this.content = content;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getRandomCode() {
		return randomCode;
	}

	public void setRandomCode(String randomCode) {
        this.randomCode = randomCode;
	}

	public Date getLastSendTime() {
		return lastSendTime;
	}

	public void setLastSendTime(Date lastSendTime) {
		this.lastSendTime = lastSendTime;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
