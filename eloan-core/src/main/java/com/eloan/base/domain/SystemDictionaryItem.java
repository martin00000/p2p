package com.eloan.base.domain;

import org.apache.ibatis.type.Alias;

/**
 * 数据字典明细
 * 
 * @author Stef
 */
@Alias("SystemDictionaryItem")
public class SystemDictionaryItem extends BaseDomain {

	private static final long serialVersionUID = 4520006109163647891L;
	private Long parentId; // 系统目录
	private String title; // 名称
	private String tvalue; // 值
	private Integer sequence; // 序列
	private String intro; // 说明

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTvalue() {
		return tvalue;
	}

	public void setTvalue(String tvalue) {
		this.tvalue = tvalue;
	}

	public Integer getSequence() {
		return sequence;
	}

	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}
}
