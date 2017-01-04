package com.eloan.business.domain;

import com.eloan.base.domain.SystemDictionaryItem;
import org.apache.ibatis.type.Alias;

/**
 * 风控材料
 */
@Alias("userfile")
public class Userfile extends BaseAuditDomain {

	private String file;
	private SystemDictionaryItem fileType;
	private int score;

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public SystemDictionaryItem getFileType() {
		return fileType;
	}

	public void setFileType(SystemDictionaryItem fileType) {
		this.fileType = fileType;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
}
