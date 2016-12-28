package com.eloan.base.domain;

import org.apache.ibatis.type.Alias;

import java.util.Date;

/**
 * IP 登陆日志，对应于【IP 登陆查询】(分会员用户和后台管理员【客服】)
 *
 * @author Administrator
 */
@Alias("IpLog")
public class IpLog extends BaseDomain {
    public static final int LOGIN_SUCCESS = 1; //登陆成功
    public static final int LOGIN_FAIL = 0; //登陆失败
    private String ip; //ip 地址
    private int loginState = LOGIN_SUCCESS; //登陆情况(成功/失败)
    private String username = ""; //登录用户名
    private Long loginInfoId; //登陆用户id
    private int loginType; //登录用户类型(等同于LoginInfo 中的userType)
    private Date loginTime=new Date();

    public IpLog() {
        super();
    }
    public IpLog(String ip, int loginState, String userName, Long loginInfoId, int loginType) {
        super();
        this.ip = ip;
        this.loginState = loginState;
        this.username = userName;
        this.loginInfoId = loginInfoId;
        this.loginType = loginType;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getLoginState() {
        return loginState;
    }

    public void setLoginState(int loginState) {
        this.loginState = loginState;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getLoginInfoId() {
        return loginInfoId;
    }

    public void setLoginInfoId(Long loginInfoId) {
        this.loginInfoId = loginInfoId;
    }

    public int getLoginType() {
        return loginType;
    }

    public void setLoginType(int loginType) {
        this.loginType = loginType;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }
}
