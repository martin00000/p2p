package com.eloan.base.query;

import org.springframework.util.StringUtils;


public class SystemDictionaryQueryObject extends QueryObject {
	private String keyword;
	private Long parentId;

	public String getKeyword() {
		return StringUtils.hasLength(keyword) ? keyword : null;
	}

	public void setKeyword(String keyword) {
        this.keyword = keyword;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
}
