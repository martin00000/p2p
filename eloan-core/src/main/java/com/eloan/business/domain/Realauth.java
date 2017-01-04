package com.eloan.business.domain;

import com.eloan.base.util.JsonUtils;
import org.apache.ibatis.type.Alias;

import java.util.HashMap;
import java.util.Map;

/**
 * 实名认证
 * @author Administrator
 */
@Alias("realauth")
public class Realauth extends BaseAuditDomain {
	public static final int SEX_MALE = 0;
	public static final int SEX_FEMALE = 1;

	private String realname;//真实姓名
	private int sex = SEX_MALE;//性别
	private String idNumber;//证件号码;
	private String birthDate;//出生日期;
	private String address;//证件地址

	private String image1;//身份证正面照片
	private String image2;//身份证背面照片

	public String getSexDisplay() {
		return sex == SEX_MALE ? "男" : "女";
	}

	public String getJsonString() {
		Map<String, Object> m = new HashMap<>();
		m.put("id", getId());
		m.put("username", this.getApplier().getUsername());
		m.put("realname", realname);
		m.put("idNumber", idNumber);
		m.put("sex", getSexDisplay());
		m.put("birthDate", birthDate);
		m.put("address", address);
		m.put("image1", image1);
		m.put("image2", image2);
		return JsonUtils.objectToJson(m);
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public String getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getImage1() {
		return image1;
	}

	public void setImage1(String image1) {
		this.image1 = image1;
	}

	public String getImage2() {
		return image2;
	}

	public void setImage2(String image2) {
		this.image2 = image2;
	}
}
