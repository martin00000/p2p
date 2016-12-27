package com.eloan.business.domain;

import com.eloan.base.domain.BaseDomain;
import com.eloan.business.util.BidConst;
import org.apache.ibatis.type.Alias;

import java.math.BigDecimal;

/**
 * 用户的帐户信息账户 一个LoginInfo 对应一个UserInfo对应一个Account
 * 
 * @author stef
 */
@Alias("Account")
public class Account extends BaseDomain {
	private static final long serialVersionUID = 6760287512112252557L;
	private int version;
	private String tradePassword; // 交易密码
	private BigDecimal usableAmount = BidConst.ZERO; // 可用余额
	private BigDecimal freezedAmount = BidConst.ZERO; // 冻结金额
	private BigDecimal unReceiveInterest = BidConst.ZERO; // 账户待收利息
	private BigDecimal unReceivePrincipal = BidConst.ZERO; // 账户待收本金
	private BigDecimal unReturnAmount = BidConst.ZERO; // 账户待还金额
	private BigDecimal remainBorrowLimit = BidConst.ZERO; // 账户剩余授信额度
	private BigDecimal borrowLimitAmount; // 授信额度（当前还可以信用借款额度）

	public BigDecimal getTotalAmount() {
		return usableAmount.add(freezedAmount).add(unReceivePrincipal);
	}

	public static Account empty(Long id) {
		Account account = new Account();
		account.setId(id);
		account.setBorrowLimitAmount(BidConst.DEFALUT_BORROWLIMITAMOUNT);
		account.setRemainBorrowLimit(BidConst.DEFALUT_BORROWLIMITAMOUNT);
		return account;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public String getTradePassword() {
		return tradePassword;
	}

	public void setTradePassword(String tradePassword) {
		this.tradePassword = tradePassword;
	}

	public BigDecimal getUsableAmount() {
		return usableAmount;
	}

	public void setUsableAmount(BigDecimal usableAmount) {
		this.usableAmount = usableAmount;
	}

	public BigDecimal getFreezedAmount() {
		return freezedAmount;
	}

	public void setFreezedAmount(BigDecimal freezedAmount) {
		this.freezedAmount = freezedAmount;
	}

	public BigDecimal getUnReceiveInterest() {
		return unReceiveInterest;
	}

	public void setUnReceiveInterest(BigDecimal unReceiveInterest) {
		this.unReceiveInterest = unReceiveInterest;
	}

	public BigDecimal getUnReceivePrincipal() {
		return unReceivePrincipal;
	}

	public void setUnReceivePrincipal(BigDecimal unReceivePrincipal) {
		this.unReceivePrincipal = unReceivePrincipal;
	}

	public BigDecimal getUnReturnAmount() {
		return unReturnAmount;
	}

	public void setUnReturnAmount(BigDecimal unReturnAmount) {
		this.unReturnAmount = unReturnAmount;
	}

	public BigDecimal getRemainBorrowLimit() {
		return remainBorrowLimit;
	}

	public void setRemainBorrowLimit(BigDecimal remainBorrowLimit) {
		this.remainBorrowLimit = remainBorrowLimit;
	}

	public BigDecimal getBorrowLimitAmount() {
		return borrowLimitAmount;
	}

	public void setBorrowLimitAmount(BigDecimal borrowLimitAmount) {
		this.borrowLimitAmount = borrowLimitAmount;
	}
}
