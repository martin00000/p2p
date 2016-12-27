package com.eloan.business.domain;

import com.eloan.base.domain.BaseDomain;
import com.eloan.base.domain.SystemDictionaryItem;
import com.eloan.business.util.BitStatesUtils;
import org.apache.ibatis.type.Alias;
import org.springframework.util.StringUtils;

/**
 * 网站会员相关详细信息（前台用户）
 * 
 * @author Stef
 */
@Alias("UserInfo")
public class Userinfo extends BaseDomain {
	private static final long serialVersionUID = -2194938919842714855L;
	private int version;// 版本号
	private Long bitState = 0L; // 位状态
	private String realName; // 对应实名认证中的真实姓名
	private String idNumber; // 对应实名认证中的身份证号
	private String email; // 用户邮箱
	private String phoneNumber = ""; // 手机号
	private int authScore = 0;//用户当前认证分数
	private Long realauthId;   //用户有效的实名认证对象

	// ====================== 会员基本资料 ===================
	private SystemDictionaryItem incomeGrade; // 月收入
	private SystemDictionaryItem marriage; // 婚姻情况
	private SystemDictionaryItem kidCount; // 子女情况
	private SystemDictionaryItem educationBackground; // 学历
	private SystemDictionaryItem houseCondition; // 住房条件

	public static Userinfo empty(Long id) {
		Userinfo userinfo = new Userinfo();
		userinfo.setId(id);
		userinfo.setBitState(BitStatesUtils.OP_BASIC_INFO);
		return userinfo;
	}

	public void addState(Long state) {
		this.bitState = BitStatesUtils.addState(this.bitState, state);
	}

	public boolean getIsBindPhone() {
		return BitStatesUtils.hasState(bitState, BitStatesUtils.OP_BIND_PHONE);
	}

	public boolean getIsBindEmail() {
		return BitStatesUtils.hasState(bitState, BitStatesUtils.OP_BIND_EMAIL);
	}

	public boolean getBaseInfo() {
		return BitStatesUtils.hasState(bitState, BitStatesUtils.OP_BASE_INFO);
	}

	public boolean getRealAuth() {
		return BitStatesUtils.hasState(bitState, BitStatesUtils.OP_REAL_AUTH);
	}

	public boolean getVedioAuth() {
		return BitStatesUtils.hasState(bitState, BitStatesUtils.OP_VEDIO_AUTH);
	}

	/**
	 * 获取用户真实名字的隐藏字符串，只显示姓氏
	 *
	 * @param realName
	 *            真实名字
	 * @return
	 */
	public String getAnonymousRealName() {
		if (StringUtils.hasLength(realName)) {
			int len = realName.length();
			String replace = "";
			replace += realName.charAt(0);
			for (int i = 1; i < len; i++) {
				replace += "*";
			}
			return replace;
		}
		return realName;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public Long getBitState() {
		return bitState;
	}

	public void setBitState(Long bitState) {
		this.bitState = bitState;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public int getAuthScore() {
		return authScore;
	}

	public void setAuthScore(int authScore) {
		this.authScore = authScore;
	}

	public Long getRealauthId() {
		return realauthId;
	}

	public void setRealauthId(Long realauthId) {
		this.realauthId = realauthId;
	}

	public SystemDictionaryItem getIncomeGrade() {
		return incomeGrade;
	}

	public void setIncomeGrade(SystemDictionaryItem incomeGrade) {
		this.incomeGrade = incomeGrade;
	}

	public SystemDictionaryItem getMarriage() {
		return marriage;
	}

	public void setMarriage(SystemDictionaryItem marriage) {
		this.marriage = marriage;
	}

	public SystemDictionaryItem getKidCount() {
		return kidCount;
	}

	public void setKidCount(SystemDictionaryItem kidCount) {
		this.kidCount = kidCount;
	}

	public SystemDictionaryItem getEducationBackground() {
		return educationBackground;
	}

	public void setEducationBackground(SystemDictionaryItem educationBackground) {
		this.educationBackground = educationBackground;
	}

	public SystemDictionaryItem getHouseCondition() {
		return houseCondition;
	}

	public void setHouseCondition(SystemDictionaryItem houseCondition) {
		this.houseCondition = houseCondition;
	}
}
