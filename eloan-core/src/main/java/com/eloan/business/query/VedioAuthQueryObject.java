package com.eloan.business.query;

import org.springframework.util.StringUtils;

public class VedioAuthQueryObject extends BaseAuditQueryObject {

	private String keyword;

	public String getKeyword() {
		return StringUtils.hasLength(keyword) ? keyword : null;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
}
