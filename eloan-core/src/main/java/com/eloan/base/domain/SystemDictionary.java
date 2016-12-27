package com.eloan.base.domain;

import org.apache.ibatis.type.Alias;

/**
 * 数据字典
 * 
 * @author Stef
 * 
 */
@Alias("SystemDictionary")
public class SystemDictionary extends BaseDomain {
	private static final long serialVersionUID = 3382007784095246946L;
	private String sn; // 编码
	private String title; // 名称
	private String intro; // 简介

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}
}
