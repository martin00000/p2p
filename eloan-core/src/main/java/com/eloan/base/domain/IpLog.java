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

    public String getDisplayState() {
        return this.loginState == LOGINSTATE_FAILD ? "登录失败" : "登录成功";
    }

    public IpLog() {
        super();
    }

    public IpLog(String username, Date loginTime, String ip, int loginType,
                 Long loginInfoId) {
        super();
        this.username = username;
        this.loginTime = loginTime;
        this.ip = ip;
        this.loginState = IpLog.LOGINSTATE_FAILD;
        this.loginType = loginType;
        this.loginInfoId = loginInfoId;
    }
}
