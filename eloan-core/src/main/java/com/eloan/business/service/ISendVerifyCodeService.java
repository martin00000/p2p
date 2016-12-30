package com.eloan.business.service;

import com.eloan.base.Exception.LogicException;

/**
 * 短信验证码相关服务
 *
 * @author Administrator
 */
public interface ISendVerifyCodeService {

    /**
     * 发送验证码
     *
     * @param phoneNumber
     * @throws LogicException
     */
    void sendVerifyCode(String phoneNumber) throws LogicException;

    /**
     * 短信验证码校验
     *
     * @param phoneNumber
     * @param verifyCode
     */
    boolean verifyCode(String phoneNumber, String verifyCode);
}
