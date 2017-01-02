package com.eloan.base.domain;

import com.eloan.base.util.JsonUtils;
import org.apache.ibatis.type.Alias;

import java.util.HashMap;
import java.util.Map;

/**
 * 数据字典明细
 * 
 * @author Stef
 */
@Alias("SystemDictionaryItem")
public class SystemDictionaryItem extends BaseDomain {

	private static final long serialVesionUID = 4520006109163647891L;
    private Long id;
	private Long parentId; // 系统目录
	private String title; // 名称
	private String tvalue; // 值
	private Integer sequence; // 序列
	private String intro; // 说明

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

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

	public String getJsonString() {
		Map<String, Object> m = new HashMap<String, Object>();
        m.put("id", id);
		m.put("parentId", parentId);
		m.put("parentId", parentId);
		m.put("tvalue", tvalue);
		m.put("sequence", sequence);
		m.put("title", title);
		m.put("intro", intro);
		return JsonUtils.objectToJson(m);
	}
}
