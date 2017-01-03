package com.eloan.business.service;

import com.eloan.business.domain.Userinfo;

/**
 * <p>
 * 用户基本信息
 * </p>
 *
 * @author Dingying
 * @version 1.0
 * @create 2016-12-29 上午 9:57
 **/
public interface IUserInfoService {

    /**
     * 根据id查找用户基本信息
     * @return
     */
    Userinfo getUserInfo(long id);

    /**
     * 绑定手机号
     * @param phoneNumber
     * @param verifyCode
     */
    void bindPhone(String phoneNumber, String verifyCode);

    /**
     * 发送邮件
     * @param email
     */
    void sendBindEmail(String email);

    /**
     * 验证邮件
     * @param checkCode
     */
    void bindMailVerify(String checkCode);

    /**
     * 保存用户信息
     * @param userinfo
     */
    void updateBasicInfo(Userinfo userinfo);
}
